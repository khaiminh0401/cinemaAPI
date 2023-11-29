package com.example.demo.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Service
@Data
public class GsonService {
	private Gson gson;

	public String getValueInput(String className, String methodName) {
		FileReader fileReader;
		String fileJson = "src/test/resources/" +
				className.replace("class ", "").replace(".", "/") +
				"_" + methodName + "_input.json";
		try {
			fileReader = new FileReader(fileJson);
			this.gson = new Gson();
			return gson.fromJson(fileReader, JsonElement.class).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getValueExpect(String className, String methodName) {
		FileReader fileReader;
		String fileJson = "src/test/resources/" +
				className.replace("class ", "").replace(".", "/") +
				"_" + methodName + "_expect.json";
		try {
			fileReader = new FileReader(fileJson);
			this.gson = new Gson();
			return gson.fromJson(fileReader, JsonElement.class).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object getValueExpect(String className, String methodName, Object name) {
		FileReader fileReader;
		String fileJson = "src/test/resources/" +
				className.replace("class ", "").replace(".", "/") +
				"_" + methodName + "_expect.json";
		try {
			fileReader = new FileReader(fileJson);
			this.gson = new Gson();
			return gson.fromJson(fileReader, name.getClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, Class<T> type) {
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}

	public String exportAndGetActual(String className, String methodName, Object obj) {
        // Tạo đối tượng ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
		String fileJson = "src/test/resources/" +
				className.replace("class ", "").replace(".", "/") +
				"_" + methodName + "_actual.json";
        try {
            // Chuyển đối tượng thành chuỗi JSON
            String json = objectMapper.writeValueAsString(obj);
            // Ghi chuỗi JSON vào một tệp
            objectMapper.writeValue(new File(fileJson), json);
			return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
}
