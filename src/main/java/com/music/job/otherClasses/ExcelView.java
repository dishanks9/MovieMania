package com.music.job.otherClasses;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.music.job.pojo.Applications;


public class ExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment:filename=\"applications.xls\"");
		Set<Applications> list=(Set<Applications>)model.get("list");
		
		// Create a sheet to be added to the workbook
		HSSFSheet sheet = workbook.createSheet("Application list");
		sheet.setDefaultColumnWidth(12);
		
		HSSFRow header=sheet.createRow(0);
		header.createCell(0).setCellValue("Application ID");
		header.createCell(1).setCellValue("Company Name");
		header.createCell(2).setCellValue("Position");
		header.createCell(3).setCellValue("Job Requirements");
		header.createCell(4).setCellValue("Skills required");
		header.createCell(5).setCellValue("Location");
		header.createCell(6).setCellValue("Hourly salary");
		header.createCell(7).setCellValue("Duration");
		header.createCell(8).setCellValue("Application status");
		header.createCell(9).setCellValue("Date applied");

		// Write a range of numbers.
		int rowNum=1;
		for (Applications application:list) {
			HSSFRow sheetRow = sheet.createRow(rowNum++);
			sheetRow.createCell(0).setCellValue(application.getId());
			sheetRow.createCell(1).setCellValue(application.getJob().getCompanyName());
			sheetRow.createCell(2).setCellValue(application.getJob().getJobTitle());
			sheetRow.createCell(3).setCellValue(application.getJob().getJobRequirements());
			sheetRow.createCell(4).setCellValue(application.getJob().getSkillsRequired());
			sheetRow.createCell(5).setCellValue(application.getJob().getLocation());
			sheetRow.createCell(6).setCellValue(application.getJob().getHourlySalary());
			sheetRow.createCell(7).setCellValue(application.getJob().getDuration());
			sheetRow.createCell(8).setCellValue(application.getJobStatus());
			sheetRow.createCell(9).setCellValue(application.getDateApplied());
		}

	} 

}
