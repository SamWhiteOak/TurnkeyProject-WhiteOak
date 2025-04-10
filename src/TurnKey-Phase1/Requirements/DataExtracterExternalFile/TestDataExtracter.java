package DataExtracterExternalFile;

import UtilityClasses.ReadWriteProgramDataFile;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class TestDataExtracter {

    public static void main(String[] args) throws IOException {

        TestDataExtracter testDataExtracter = new TestDataExtracter();
        Object[][] testdata = testDataExtracter.extractDataFromSpecificSheet(1, "account");
        int count = 1;
        //Print the testdata seprate each record with a line also print each key in map in new line Also print total number of keys in each map
        for (Object[] record : testdata) {
            Map<String, String> recordMap = (Map<String, String>) record[0];
            System.out.println("Record :- " + count);
            for (String key : recordMap.keySet()) {
                System.out.println(key + ": " + recordMap.get(key));
            }
            count++;
            System.out.println("--------------------------------------");
        }
    }

    /*****************************--Extract Data From Excel Sheet--**************************************/
    public Object[][] extractDataFromAccountContactSheet(int numberOfRecords) {
        String filePath = System.getProperty("user.dir") + "/ExternalTestDataFiles/Sampletestdata.xlsx";
        Object[][] data = new Object[numberOfRecords][1];

        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet accountSheet = workbook.getSheetAt(0);
            Sheet contactSheet = workbook.getSheetAt(1);

            ReadWriteProgramDataFile pf = new ReadWriteProgramDataFile();
            int accountIndex = pf.readVariableFromFile("CurrentAccountRowIndex");
            int contactIndex = pf.readVariableFromFile("CurrentContactRowIndex");

            int totalAccountRows = accountSheet.getLastRowNum() + 1; // Including header row
            int totalContactRows = contactSheet.getLastRowNum() + 1; // Including header row

            for (int rowIndex = 0; rowIndex < numberOfRecords; rowIndex++) {
                if (accountIndex >= totalAccountRows) {
                    accountIndex = 1; // Reset to 1 if all rows are used
                }
                if (contactIndex >= totalContactRows) {
                    contactIndex = 1; // Reset to 1 if all rows are used
                }

                Row accountRow = accountSheet.getRow(accountIndex);
                Row contactRow = contactSheet.getRow(contactIndex);

                Map<String, String> mergedRecordMap = new HashMap<>();

                if (accountRow != null) {
                    for (int cellIndex = 0; cellIndex < accountRow.getLastCellNum(); cellIndex++) {
                        Cell cell = accountRow.getCell(cellIndex);
                        String columnName = accountSheet.getRow(0).getCell(cellIndex).getStringCellValue();
                        String cellValue = getCellValue(cell);
                        mergedRecordMap.put(columnName, cellValue);
                    }
                }

                if (contactRow != null) {
                    for (int cellIndex = 0; cellIndex < contactRow.getLastCellNum(); cellIndex++) {
                        Cell cell = contactRow.getCell(cellIndex);
                        String columnName = contactSheet.getRow(0).getCell(cellIndex).getStringCellValue();
                        String cellValue = getCellValue(cell);
                        mergedRecordMap.put(columnName, cellValue);
                    }
                }

                data[rowIndex][0] = mergedRecordMap;

                accountIndex++;
                contactIndex++;
                pf.writeVariableToFile("CurrentAccountRowIndex", accountIndex);
                pf.writeVariableToFile("CurrentContactRowIndex", contactIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public Object[][] extractDataFromSpecificSheet(int numberOfRecords, String sheetName) {
        String filePath = System.getProperty("user.dir") + "/ExternalTestDataFiles/Sampletestdata.xlsx";
        Object[][] data = new Object[numberOfRecords][1];
        Map<String, Sheet> sheetMap = new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            //Printing the sheet names
//        int numberOfSheets = workbook.getNumberOfSheets();
//        System.out.println("Sheets in workbook:");
//        for (int i = 0; i < numberOfSheets; i++) {
//            System.out.println(workbook.getSheetName(i)); // Log all sheet names
//        }
            sheetMap.put("account", workbook.getSheetAt(0));
            sheetMap.put("contact", workbook.getSheetAt(1));
            sheetMap.put("loan quote", workbook.getSheetAt(2));
            sheetMap.put("lease quote", workbook.getSheetAt(3));
            sheetMap.put("hire purchase quote", workbook.getSheetAt(4));
            Sheet sheet = sheetMap.get(sheetName.toLowerCase());
            if (sheet == null) {
                System.out.println("Sheet does not exist to read data from.");
                System.exit(0);
            }
            ReadWriteProgramDataFile pf = new ReadWriteProgramDataFile();
            int rowIndex = pf.readVariableFromFile("Current" + sheetName.substring(0, 1).toUpperCase() + sheetName.substring(1) + "RowIndex");
            int totalRows = sheet.getLastRowNum() + 1;
            for (int i = 0; i < numberOfRecords; i++) {
                if (rowIndex >= totalRows) {
                    System.out.println("All the data of " + sheetName + " sheet is used once, Now reusing same data again from 1st record.");
                    rowIndex = 1;
                    pf.writeVariableToFile("Current" + sheetName.substring(0, 1).toUpperCase() + sheetName.substring(1) + "RowIndex", rowIndex);
                }
                Row headerRow = sheet.getRow(0);
                Row currentRow = sheet.getRow(rowIndex);
                Map<String, String> recordMap = new HashMap<>();
                for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
                    String columnName = headerRow.getCell(cellIndex).getStringCellValue();
                    String cellValue = getCellValue(currentRow.getCell(cellIndex));
                    recordMap.put(columnName, cellValue);
                }
                data[i][0] = recordMap;
                rowIndex++;
                pf.writeVariableToFile("Current" + sheetName.substring(0, 1).toUpperCase() + sheetName.substring(1) + "RowIndex", rowIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    // Utility method to convert cell value to string
    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Check if the cell contains a date
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Format the date using a SimpleDateFormat
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    if (cell.getNumericCellValue() == (long) cell.getNumericCellValue()) {
                        return String.valueOf((long) cell.getNumericCellValue());
                    } else {
                        return String.valueOf(cell.getNumericCellValue());
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
