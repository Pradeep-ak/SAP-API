package com.api.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLS2HTMLParser {

	private static final String NEW_LINE = "\n";
	private static final String HTML_FILE_EXTENSION = ".html";
	private static final String TEMP_FILE_EXTENSION = ".tmp";
	private static final String HTML_SNNIPET_1 = "<!DOCTYPE html><html><head><title>";
	private static final String HTML_SNNIPET_2 = "</title></head><body><table border=1>";
	private static final String HTML_SNNIPET_3 = "</table></body></html>";
	private static final String HTML_TR_S = "<tr>";
	private static final String HTML_TR_B = "<tr style='background-color:lightblue'>";
	private static final String HTML_TR_E = "</tr>";
	private static final String HTML_TD_S = "<td>";
	private static final String HTML_TD_E = "</td>";

	private File file;


	public String parse(File file) throws FileNotFoundException, IOException {
		BufferedWriter writer;
		Workbook workbook;
		String fileName = file.getName();
		String folderName = file.getParent();
		String htmlFileInAString;
		if (fileName.toLowerCase().endsWith("xls")) {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} else {
			workbook = new XSSFWorkbook(new FileInputStream(file));
		}

		File tempFile = File.createTempFile(fileName + '-', HTML_FILE_EXTENSION, new File(folderName));
		writer = new BufferedWriter(new FileWriter(tempFile));
		writer.write(HTML_SNNIPET_1);
		writer.write(fileName);
		writer.write(HTML_SNNIPET_2);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Iterator<Cell> cells = null;
		while (rows.hasNext()) {
			Row row = rows.next();
			cells = row.cellIterator();
			
			writer.write(NEW_LINE);
			if(row.getRowNum()<2)
			writer.write(HTML_TR_B);
			else
			writer.write(HTML_TR_S);
			while (cells.hasNext() && row.getRowNum() <8) {
				Cell cell = cells.next();
				writer.write(HTML_TD_S);
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					writer.write(cell.toString());
					break;
					case HSSFCell.CELL_TYPE_NUMERIC:
					if(HSSFDateUtil.isCellDateFormatted(cell)) {
						 Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						    String dateFmt = cell.getCellStyle().getDataFormatString();
						    String strValue = new CellDateFormatter(dateFmt).format(date); 
							writer.write(strValue);
					}
					else  {
						if (cell.getColumnIndex()<3) {
					    writer.write(new CellNumberFormatter("#").format(cell.getNumericCellValue()));
						} else {
							writer.write(new CellNumberFormatter("$#.##").format(cell.getNumericCellValue()));
						}
					}
				    break;
				default:
				}
				writer.write(HTML_TD_E);
			}
			writer.write(HTML_TR_E);
		}
		writer.write(NEW_LINE);
		writer.write(HTML_SNNIPET_3);
		writer.close();
		
		    BufferedReader br = new BufferedReader(new FileReader(tempFile));
		    String line;
		    StringBuilder sb = new StringBuilder();

		    while((line=br.readLine())!= null){
		        sb.append(line);
		    }
		    htmlFileInAString  = sb.toString();
		    br.close();
		    tempFile.delete();
		    tempFile.deleteOnExit();
		    workbook.close();
		    return htmlFileInAString;
	}

}