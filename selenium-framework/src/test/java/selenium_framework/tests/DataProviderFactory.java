package selenium_framework.tests;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import selenium_framework.utils.DataReader;

public class DataProviderFactory {
	@DataProvider
	public static Object[][] getDataForSubmitOrder() {
		List<HashMap<String, String>> data = DataReader.getJsonDataToMap("PurchaseOrder.json");
		return new Object[][] {
			{data.get(0)},
			{data.get(1)}
			};
	}
}
