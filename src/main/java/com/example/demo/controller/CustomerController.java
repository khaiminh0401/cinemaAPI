package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.enums.RequestParameterEnum;
import com.example.demo.common.enums.RequestStatusEnum;
import com.example.demo.entity.Customer;
import com.example.demo.exception.InvalidRequestParameterException;
import com.example.demo.listener.ListenerEvent;
import com.example.demo.model.AccountModel;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	ListenerEvent listenerEvent;

	@GetMapping("/getAll")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(customerService.findAll());
	}

	@GetMapping("/getById")
	public ResponseEntity<?> findById(String id) {
		return ResponseEntity.ok(customerService.findById(Integer.parseInt(id)).get());
	}

	@PostMapping("/save")
	public void save(@RequestBody Customer customer) throws InvalidRequestParameterException {
		customerService.insert(customer);
	}

	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody AccountModel account) throws InvalidRequestParameterException {
		return ResponseEntity.ok(customerService.Authenticator(account.getEmail(), account.getPassword()));
	}

	@PostMapping("/registration")
	public ResponseEntity<?> registration(@RequestBody Customer user) throws InvalidRequestParameterException {
		Optional<Customer> customer = customerService.findByEmail(user.getEmail());
		if (customer.isPresent()) {
			if (customer.get().isActive()) {
				throw new InvalidRequestParameterException(RequestParameterEnum.EXISTS);
			}
			// If customer exists -> Update new Token
			customer.get().setToken(customerService.registration(user));
			if (customerService.updateToken(customer.get()).equals(RequestStatusEnum.SUCCESS)) {
				listenerEvent.checkTokenEvent(user.getEmail()); // Start countdown 5 Minute remove token
				return ResponseEntity.ok(RequestStatusEnum.SUCCESS);
			}
		}
		// If customer not exists -> Create New Token and Customer
		else {
			user.setToken(customerService.registration(user));
			if (customerService.insert(user).equals(RequestStatusEnum.SUCCESS)) {
				listenerEvent.checkTokenEvent(user.getEmail()); // Start countdown 5 Minute remove token
				return ResponseEntity.ok(RequestStatusEnum.SUCCESS);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatusEnum.FAILURE);
	}

	@GetMapping("/active")
	public ResponseEntity<?> registrationConfirm(@RequestParam("userToken") String token)
			throws InvalidRequestParameterException {
		return ResponseEntity.ok(customerService.registrationConfirm(token));
	}

	@PutMapping(value = "/update-information", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateInformation(@ModelAttribute Customer customer,
			@RequestParam Optional<MultipartFile> multipartFile) throws FileNotFoundException, IOException {
		if (multipartFile.isPresent()) {
			try {
				Tika tika = new Tika();
				String mimeType = tika.detect(multipartFile.get().getInputStream());

				if (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg")) {
					return ResponseEntity.ok(customerService.updateInformation(customer, multipartFile));
				} else {
					return ResponseEntity.status(500).body("Chỉ nhận file ảnh png, jpeg và jpg!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(500).body(RequestStatusEnum.FAILURE);
			}
		}

		return ResponseEntity.ok(customerService.updateInformation(customer, multipartFile));
	}

	@PutMapping(value = "/update-avatar", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateAvatar(@RequestParam Integer customerId, @RequestParam MultipartFile multipartFile)
			throws FileNotFoundException, IOException {
		Tika tika = new Tika();
		try {
			String mimeType = tika.detect(multipartFile.getInputStream());
			if (mimeType.equals("image/png") || mimeType.equals("image/jpeg") || mimeType.equals("image/jpg")) {
				return ResponseEntity.ok(customerService.updateAvatar(customerId, multipartFile));
			} else {
				return ResponseEntity.status(500).body("Chỉ nhận file ảnh png, jpeg và jpg!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(RequestStatusEnum.FAILURE);
		}
	}
}
