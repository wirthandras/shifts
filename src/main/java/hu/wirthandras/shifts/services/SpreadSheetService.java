package hu.wirthandras.shifts.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.services.generator.ExcelGenerator;

@Service
public class SpreadSheetService {

	private static String EXCEL_FILE_NAME = "output.xlsx";

	private static Logger LOGGER = Logger.getLogger(SpreadSheetService.class.getName());

	@Autowired
	private ExcelGenerator generator;

	private void createExcelFile(HSSFWorkbook wb) {
		removePreviousIfExist();
		save(wb);
	}

	private void removePreviousIfExist() {
		File f = new File(EXCEL_FILE_NAME);
		if (f.exists()) {
			f.delete();
		}
	}

	private void save(HSSFWorkbook workbook) {
		try {
			FileOutputStream outputStream = new FileOutputStream(EXCEL_FILE_NAME);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		LOGGER.log(Level.INFO, "Spreadsheet is created");
	}

	public File downloadPlan() {
		HSSFWorkbook wb = generator.generate();
		createExcelFile(wb);
		File f = new File(EXCEL_FILE_NAME);
		return f;
	}

}
