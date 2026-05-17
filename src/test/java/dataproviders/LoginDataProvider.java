package dataproviders;

import org.testng.annotations.DataProvider;
import utils.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {

        List<Map<String, String>> data =
                JsonUtils.readJsonData(
                        "src/test/resources/testdata/loginData.json"
                );

        Object[][] testData = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {

            testData[i][0] = data.get(i).get("username");

            testData[i][1] = data.get(i).get("password");
        }

        return testData;
    }
}