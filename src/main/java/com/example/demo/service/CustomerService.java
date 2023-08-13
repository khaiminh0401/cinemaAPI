package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.entity.Customer;

@Service
public class CustomerService implements BaseService<Customer, Integer> {
    @Autowired
    CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerDao.findById(id);
    }

    public void insert(Customer customer) {
        customerDao.insert(customer);
    }

	public Customer findByKey(String email, String password) {
		// TODO Auto-generated method stub
		return customerDao.findByKey(email, password);
	}

}