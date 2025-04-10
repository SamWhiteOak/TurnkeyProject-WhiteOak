package UtilityClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class WriteJSONFile {
    public void write(String json) {
        try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+"/APIResponse/Response.json")) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String json, String fileName) {
        String fileNames = Paths.get(System.getProperty("user.dir"),"APIResponse",fileName+".json").toString();
        try (FileWriter fileWriter = new FileWriter(fileNames)) {
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
