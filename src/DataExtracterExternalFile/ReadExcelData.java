package DataExtracterExternalFile;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;
import java.util.Random;

public class ReadExcelData {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\samiksha.bhatnagar\\IdeaProjects\\TurnkeyProject-WhiteOak\\ExternalTestDataFile\\TestDataTurnKeyPhase1.xlsx");

        // Read from Sheet 0 - Valid Data
        PoijiOptions optionsValid = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(0).build();
        List<ExcelLeadData> validDataList = Poiji.fromExcel(file, ExcelLeadData.class, optionsValid);

        if (!validDataList.isEmpty()) {
            ExcelLeadData randomValidLead = getRandomLead(validDataList);
            System.out.println("Random Valid Data Picked From Sheet Details-");
            System.out.println("First Name: " + randomValidLead.getFirstName() + ", Email: " + randomValidLead.getEmail());
            // TODO: Pass this lead to CreateLeadAPI
        }

        // Read from Sheet 1 - Invalid Data
        PoijiOptions optionsInvalid = PoijiOptions.PoijiOptionsBuilder.settings().sheetIndex(1).build();
        List<ExcelLeadData> invalidDataList = Poiji.fromExcel(file, ExcelLeadData.class, optionsInvalid);

        if (!invalidDataList.isEmpty()) {
            ExcelLeadData randomInvalidLead = getRandomLead(invalidDataList);
            System.out.println("Random Invalid Data Picked From Sheet Details");
            System.out.println("First Name: " + randomInvalidLead.getFirstName() + ", Email: " + randomInvalidLead.getEmail());
            // TODO: Pass this lead to CreateLeadAPI (or negative test logic)
        }
    }

    private static ExcelLeadData getRandomLead(List<ExcelLeadData> leadDataList) {
        Random random = new Random();
        return leadDataList.get(random.nextInt(leadDataList.size()));
    }
}
