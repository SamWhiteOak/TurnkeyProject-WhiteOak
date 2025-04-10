package APIPackages;

import DataExtracterExternalFile.ExcelLeadData;
import UtilityClasses.ReadConfigFileData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateLeadAPI {

    ReadConfigFileData readConfigFileData = new ReadConfigFileData();
    String accessToken = readConfigFileData.getAccessToken();
    String baseURL = readConfigFileData.getBaseURL();

    public static void main(String[] args) throws IOException {
        CreateLeadAPI createLeadAPI = new CreateLeadAPI();
        createLeadAPI.createLeadFromValidExcelRow();
        createLeadAPI.createLeadFromInvalidExcelRow();
    }

    public void createLeadFromValidExcelRow() {
        createLeadFromExcelSheet(
                "C:\\Users\\samiksha.bhatnagar\\IdeaProjects\\TurnkeyProject-WhiteOak\\ExternalTestDataFile\\TestDataTurnKeyPhase1.xlsx",
                0,
                "/APIResponse/ValidTestDataSave.json"
        );
    }

    public void createLeadFromInvalidExcelRow() {
        createLeadFromExcelSheet(
                "C:\\Users\\samiksha.bhatnagar\\IdeaProjects\\TurnkeyProject-WhiteOak\\ExternalTestDataFile\\TestDataTurnKeyPhase1.xlsx",
                1,
                "/APIResponse/InvalidTestDataSave.json"
        );
//        System.out.println("Picking Invalid Data -:");
    }

    private void createLeadFromExcelSheet(String excelFilePath, int sheetIndex, String outputJsonPath) {
        try {
            File file = new File(excelFilePath);
            PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(sheetIndex).build();
            List<ExcelLeadData> leadDataList = Poiji.fromExcel(file, ExcelLeadData.class, options);

            if (leadDataList.isEmpty()) {
                System.out.println("No data found in the Excel sheet at index " + sheetIndex);
                return;
            }
            // Pick a random row
            Random random = new Random();
            ExcelLeadData randomData = leadDataList.get(random.nextInt(leadDataList.size()));

            // 🔁 Format birthDate for that ONE random row
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = inputFormat.parse(randomData.getBirthDate());
                randomData.setBirthDate(outputFormat.format(parsed));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Convert to JSON using Gson
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(randomData);

            // Save to file
            String fullPath = System.getProperty("user.dir") + outputJsonPath;
            File jsonFile = new File(fullPath);
            jsonFile.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(jsonFile)) {
                writer.write(json);
                System.out.println("Saved JSON to file: " + fullPath);
            }

            // Call CreateLead API with the same data
            JSONObject jsonObject = new JSONObject(json);
            String dataLabel = (sheetIndex == 0) ? "Valid Data" : "Invalid Data";
            CreateLead(jsonObject, dataLabel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateLead(JSONObject validData, String label) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = constructRequestBody(validData);

        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url(baseURL + "/services/apexrest/LeadService")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("Request Body: " + requestBody);

        String responseBody = response.body().string();
        if (response.isSuccessful()) {
            System.out.println("✅ " + label + " lead is created successfully");
            saveResponseToFile("CreateLead_" + label + "_SuccessResponse.json", responseBody);
        } else {
            System.out.println("❌ " + label + " lead creation failed");
            saveResponseToFile("CreateLead_" + label + "_ErrorResponse.json", responseBody);
        }
    }

    public static void saveResponseToFile(String fileName, String responseBody) {
        try {
            File file = new File(System.getProperty("user.dir") + "/APIResponse/" + fileName);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write(responseBody);
            writer.write(System.lineSeparator());
            writer.close();
            System.out.println("📁 Response saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error saving response to file");
        }
    }

    public static String constructRequestBody(JSONObject validData) {
        return validData.toString();
    }

    public String readJsonFile(String filePath) throws IOException {
        File file = new File(System.getProperty("user.dir") + filePath);
        return FileUtils.readFileToString(file, "UTF-8");
    }

    public static void writeJsonToFile(String filePath, String jsonString) throws IOException {
        FileWriter writer = new FileWriter(System.getProperty("user.dir") + filePath);
        writer.write(jsonString);
        writer.close();
        System.out.println("JSON updated and saved to file: " + filePath);
    }
}
