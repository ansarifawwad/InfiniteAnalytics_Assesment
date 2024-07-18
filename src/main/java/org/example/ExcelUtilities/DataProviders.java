package org.example.ExcelUtilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    public Object[][] getExcelData(String fileName, String sheetName) {
        List<Object[]> dataList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = sheet.getRow(0).getLastCellNum();

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < noOfRows; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    List<Object> rowData = new ArrayList<>();
                    boolean hasNonNull = false;

                    for (int j = 0; j < noOfCols; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            Object cellValue;
                            switch (cell.getCellType()) {
                                case STRING:
                                    cellValue = formatter.formatCellValue(cell);
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        cellValue = cell.getDateCellValue();
                                    } else {
                                        cellValue = cell.getNumericCellValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    cellValue = cell.getBooleanCellValue();
                                    break;
                                default:
                                    cellValue = null;
                            }
                            if (cellValue != null) {
                                hasNonNull = true;
                            }
                            rowData.add(cellValue);
                        } else {
                            rowData.add(null);
                        }
                    }
                    if (hasNonNull) {
                        dataList.add(rowData.toArray());
                    } else {
                        System.out.println("Row " + i + " is empty or contains only null values.");
                    }
                } else {
                    System.out.println("Row " + i + " is null.");
                }
            }

            workbook.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
        Object[][] data = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return data;
    }

    @DataProvider(name = "DownloadRecords")
    public Object[][] input() {
        String projectPath = System.getProperty("user.dir");
        String fileLocation = "src/main/java/org/example/ExcelUtilities/InputData.xlsx";
        String downloadPath = Paths.get(projectPath, fileLocation).toString();
        return getExcelData(downloadPath, "Data");
    }
}