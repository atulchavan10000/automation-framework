package selenium_framework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public static List<HashMap<String, String>> getJsonDataToMap(String inputJsonFile) {
		try {
			// convert the json file to String
			String stringContent = FileUtils.readFileToString(
					new File(System.getProperty("user.dir")+"/src/test/java/selenium_framework/data/" + inputJsonFile),  
					Charset.defaultCharset());
		
			// convert the string to map
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(stringContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
