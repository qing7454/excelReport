package com.report.util;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtils {

	public static Cell getCell(String cellReference, Sheet sheet){
		CellReference cellRef = new CellReference(cellReference);
		Row row = sheet.getRow(cellRef.getRow());
		if(sheet.getRow(cellRef.getRow()) == null){
			row = sheet.createRow(cellRef.getRow()); 
		}
    return row.createCell(cellRef.getCol());
	}
	
	public static Cell getCell(int rowIndex,int columnIndex, Sheet sheet){
		Row row = sheet.getRow(rowIndex);
		if(sheet.getRow(rowIndex) == null){
			row = sheet.createRow(rowIndex); 
		}
    return row.createCell(columnIndex);
	}
	
	public static Cell getNextColumn(Cell cell, Sheet sheet){
		return getCell(cell.getRowIndex(), cell.getColumnIndex()+1, sheet);
	}
	
	public static Cell getNextRow(Cell cell, Sheet sheet){
		return getCell(cell.getRowIndex()+1, cell.getColumnIndex(), sheet);
	}
	
	public static void mergeCell(Sheet sheet, int rowIndex, int columnIndexForm, int columnIndexTo){
		sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,columnIndexForm,columnIndexTo));
	}
	
	public static int getLastRow(Sheet sheet){
		return sheet.getLastRowNum()+1;
	}
	
}