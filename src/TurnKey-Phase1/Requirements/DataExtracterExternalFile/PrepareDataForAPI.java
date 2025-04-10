package DataExtracterExternalFile;

import UtilityClasses.WriteJSONFile;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class PrepareDataForAPI {
    TestDataExtracter dataExtracterObj = new TestDataExtracter();
    String selectedContractType; // Store selected contract type

    public void prepareDataAccount(int NoofAccounts) throws IOException { //Whenever we need to create account it will just extract the data
        Object[][] testdata = dataExtracterObj.extractDataFromSpecificSheet(NoofAccounts, "account"); //2D array return of object type. Object type can we of any type
        for (int i = 0; i < testdata.length; i++) {
            Map<String, String> d = (Map<String, String>) testdata[i][0]; //access ke time pe indexing
//            d = dataExtracterObj.formatDataForAccountContact(d);
            //Writing data to Account Json
            WriteJSONFile WriteJSON = new WriteJSONFile();
            JSONObject js = new JSONObject(d);
            WriteJSON.write(js.toString(), "AccountTestDataAPI");
        }
    }
}
