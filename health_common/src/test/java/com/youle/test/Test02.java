package com.youle.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test02 {

    @Test
    public void test01() throws IOException {
        XSSFWorkbook xeb = new XSSFWorkbook(new FileInputStream("D:\\工作簿.xlsx"));
        XSSFSheet sheet = xeb.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    cell.setCellType(cell.CELL_TYPE_STRING);
                }
                System.out.println(cell.getStringCellValue());
            }
        }
        xeb.close();
    }

    @Test
    public void test02() throws IOException{
        XSSFWorkbook xeb = new XSSFWorkbook(new FileInputStream("D:\\工作簿.xlsx"));
        XSSFSheet sheet = xeb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (short j = 0; j < lastCellNum;j++) {
                XSSFCell cell = row.getCell(j);
                if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                    cell.setCellType(cell.CELL_TYPE_STRING);
                }
                System.out.println(cell.getStringCellValue());
            }
        }
        xeb.close();
    }

    @Test
    public void test03() throws IOException {
        XSSFWorkbook xwb = new XSSFWorkbook();
        XSSFSheet sheet = xwb.createSheet("qwert");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("性别");
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("zs");
        row1.createCell(1,0).setCellValue(20);
        row1.createCell(2).setCellValue("男");
        FileOutputStream fos = new FileOutputStream("D:\\工作簿1.xlsx");
        xwb.write(fos);
        fos.close();
        xwb.close();
    }
}
