package com.yourcompany.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private static final String FILE_PATH = "src/test/resources/testdata.xlsx";

    public static Object[][] readTestData(String sheetName) {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                            data[i - 1][j] = null;
                    }
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}