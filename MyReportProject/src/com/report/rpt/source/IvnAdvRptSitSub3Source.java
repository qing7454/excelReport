package com.report.rpt.source;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import com.report.dao.InvAdvRptSitDAO;
import com.report.model.ChannelModel;
import com.report.rpt.criteria.ReportCriteria;
import com.report.util.ExcelUtils;
import com.report.util.StyleUtils;
import com.report.util.Utils;

public class IvnAdvRptSitSub3Source extends IvnAdvRptSitCommonSource implements ReportSource {
	
	@Override
	public InvAdvRptSitDAO getDAOobject() {
		return (InvAdvRptSitDAO)Utils.APP_CONTEXT.getBean("invAdvRptSitDAO");
	}
	
	@Override
	public void createHeader(Sheet sheet, String startExcelColumn,int startExcelRow, ReportCriteria criteria){
		CellStyle allBorder = StyleUtils.allBorder(sheet.getWorkbook().createCellStyle());
    Cell headerColA = ExcelUtils.getCell(startExcelColumn, startExcelRow, sheet);
    headerColA.setCellStyle(allBorder);
    headerColA.setCellValue("Zone ID");
    
    Cell headerColB = ExcelUtils.getNextColumn(headerColA);
    headerColB.setCellStyle(allBorder);
    headerColB.setCellValue("Area ID");
    
    Cell headerColC = ExcelUtils.getNextColumn(headerColB);
    headerColC.setCellStyle(allBorder);
    headerColC.setCellValue("Code");
    
    Cell headerColD = ExcelUtils.getNextColumn(headerColC);
    headerColD.setCellStyle(allBorder);
    headerColD.setCellValue("Name");
        
	}
	
	@Override
	public void createBody(Sheet sheet, String startExcelColumn,int startExcelRow, ReportCriteria criteria){
    List<ChannelModel> channelModels = getDAOobject().getSubReport3Data();
    CellStyle allBorder = StyleUtils.allBorder(sheet.getWorkbook().createCellStyle());
    
    Cell bodyColA = ExcelUtils.getCell(startExcelColumn, startExcelRow, sheet);
    Cell bodyColB = ExcelUtils.getNextColumn(bodyColA);
    Cell bodyColC = ExcelUtils.getNextColumn(bodyColB);
    Cell bodyColD = ExcelUtils.getNextColumn(bodyColC);
    for(ChannelModel channelModel:channelModels){
    	
    	bodyColA.setCellStyle(allBorder);
  		bodyColA.setCellValue(channelModel.getZoneId());
  		
  		bodyColB.setCellStyle(allBorder);
  		bodyColB.setCellValue(channelModel.getAreaId());
  		
  		bodyColC.setCellStyle(allBorder);
  		bodyColC.setCellValue(channelModel.getChannelCode());
  		
  		bodyColD.setCellStyle(allBorder);
  		bodyColD.setCellValue(channelModel.getChShortName());
    	
    	ExcelUtils.createDummyColumn(bodyColD, allBorder, 36);
    	if(channelModel != channelModels.get(channelModels.size()-1)){
	    	bodyColA = ExcelUtils.getNextRow(bodyColA);
	  		bodyColB = ExcelUtils.getNextRow(bodyColB);
	  		bodyColC = ExcelUtils.getNextRow(bodyColC);
	  		bodyColD = ExcelUtils.getNextRow(bodyColD);
    	}
    }
    
	}
	
	@Override
	public void createFooter(Sheet sheet, String startExcelColumn,int startExcelRow, ReportCriteria criteria, int... params){
		CellStyle allBorderYellow = StyleUtils.allBorderYellow(sheet.getWorkbook().createCellStyle());
		CellStyle allBorder = StyleUtils.allBorder(sheet.getWorkbook().createCellStyle());
		
		Cell footerColA = ExcelUtils.getCell(startExcelColumn, startExcelRow, sheet);
		footerColA.setCellStyle(allBorder);
		
		Cell footerColB = ExcelUtils.getNextColumn(footerColA);
		footerColB.setCellStyle(allBorder);
		 
		Cell footerColC = ExcelUtils.getNextColumn(footerColB);
		footerColC.setCellStyle(allBorderYellow);
		footerColC.setCellValue("Total");
		ExcelUtils.createDummyColumn(footerColC, allBorderYellow, 36);
	}
	
}
