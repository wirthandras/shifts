package hu.wirthandras.shifts.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.employee.Employee;

@Service
public class SpreadSheetService {

	private static String EXCEL_FILE_NAME = "output.xlsx";

	private static Logger LOGGER = Logger.getLogger(SpreadSheetService.class.getName());

	@Autowired
	private PlanService planService;
	@Autowired
	private EmployeeService emloyeeService;
	@Autowired
	private ShiftService shiftService;

	public void generate() {
		planService.doPlan(emloyeeService.getEmployees(), shiftService.getPersistedShifts());

		Map<Shift, Employee> plan = planService.getPlan();

		createExcelFile(reverse(plan));
	}

	private Map<Employee, List<Shift>> reverse(Map<Shift, Employee> plan) {
		Map<Employee, List<Shift>> reversed = new HashMap<>();
		for (Shift s : plan.keySet()) {
			if (reversed.containsKey(plan.get(s))) {
				reversed.get(plan.get(s)).add(s);
			} else {
				List<Shift> shifts = new ArrayList<Shift>();
				shifts.add(s);
				reversed.put(plan.get(s), shifts);
			}
		}
		return reversed;
	}

	private void createExcelFile(Map<Employee, List<Shift>> plan) {

		removePreviousIfExist();

		XSSFWorkbook workbook = new XSSFWorkbook();
		for (Employee e : plan.keySet()) {
			createSheet(workbook, e, plan.get(e));
		}

		save(workbook);
	}

	private void removePreviousIfExist() {
		File f = new File(EXCEL_FILE_NAME);
		if (f.exists()) {
			f.delete();
		}
	}

	private void save(XSSFWorkbook workbook) {
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

	private void createSheet(XSSFWorkbook workbook, Employee e, List<Shift> list) {
		LOGGER.log(Level.INFO, "Creating excel sheet with name: " + e.getName());

		// To avoid the create duplicated sheet name (exception occurred)
		XSSFSheet sheet = workbook.getSheet(e.getName());
		if (sheet == null) {
			sheet = workbook.createSheet(e.getName());
		}

		XSSFFont font = workbook.createFont();
		font.setFontName("Calibri");
		XSSFCellStyle style = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		style.setFont(font);
		style.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));

		drawTable(list, sheet, style);

		alignColumns(sheet);
	}

	private void alignColumns(XSSFSheet sheet) {
		for (int i = 0; i < 3; i++) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(0, 3000);
		}
	}

	private void drawTable(List<Shift> list, XSSFSheet sheet, XSSFCellStyle style) {
		int rowNum = 0;
		for (Shift datatype : list) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : datatype.toObjectArray()) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Date) {
					cell.setCellStyle(style);
					cell.setCellValue((Date) field);
				}
			}
		}
	}

	public File downloadPlan() {
		generate();
		File f = new File(EXCEL_FILE_NAME);
		return f;
	}

}
