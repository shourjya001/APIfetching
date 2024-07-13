package com.example.view;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileView {
    public void saveToExcel(List<Map<String, String>> data, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("API Data");

            // Create header row
            Row headerRow = sheet.createRow(0);
            if (!data.isEmpty()) {
                int cellNum = 0;
                for (String key : data.get(0).keySet()) {
                    Cell cell = headerRow.createCell(cellNum++);
                    cell.setCellValue(key);
                }
            }

            // Create data rows
            int rowNum = 1;
            for (Map<String, String> rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int cellNum = 0;
                for (String value : rowData.values()) {
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(value);
                }
            }

            // Auto-size columns
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to Excel file: " + e.getMessage());
        }
    }
}