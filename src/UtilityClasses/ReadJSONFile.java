package UtilityClasses;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;

public  class ReadJSONFile {
    public JSONObject read() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/APIResponse/Response.json");
        String jsonString = FileUtils.readFileToString(file, "UTF-8");
        return new JSONObject(jsonString);
    }
    public JSONObject read(String fileName) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/APIResponse/"+fileName+".json");
        String jsonString = FileUtils.readFileToString(file, "UTF-8");
        return new JSONObject(jsonString);
    }
}
