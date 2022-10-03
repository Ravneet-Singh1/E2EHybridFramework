package dataFormat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonData() throws IOException {

		// read json data  -- Using FileUtils
		String jsonData = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\dataFormat\\loginData.json"),
				StandardCharsets.UTF_8);

		
		// String data to hash map -- For this add "Jackson Databind" in pom.xml
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		
		return data;

	}

}
