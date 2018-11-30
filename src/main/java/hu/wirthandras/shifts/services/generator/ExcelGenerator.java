package hu.wirthandras.shifts.services.generator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.Job;
import hu.wirthandras.shifts.services.EmployeeService;
import hu.wirthandras.shifts.services.EventService;
import hu.wirthandras.shifts.services.MonthService;
import hu.wirthandras.shifts.services.PlanService;

@Service
public class ExcelGenerator {

	private static final String OSSZ_ORA = "Ossz ora";
	private static final String NAMES = "Nevsor";
	private static final String HOLIDAYS = "Szabi";

	private static final String SICK_SHORT = "B";
	private static final String HOLIDAY_SHORT = "sz";

	@Autowired
	private PlanService plan;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EventService serviceEvent;

	@Autowired
	private MonthService serviceMonth;

	public HSSFWorkbook generate() {
		plan.doPlan();
		HSSFWorkbook workbook = new HSSFWorkbook();

		generateSheet(workbook, Job.MEDIC);
		generateSheet(workbook, Job.DOCTOR);
		generateSheet(workbook, Job.DRIVER);

		return workbook;
	}

	private void generateSheet(HSSFWorkbook workbook, Job job) {

		Set<Employee> filteredEmployees = employeeService.filter(job);
		generateOneSheet(workbook, filteredEmployees, job.toString());
	}

	private void generateOneSheet(HSSFWorkbook workbook, Set<Employee> employees, String sheetName) {

		int days = serviceMonth.getCurrentMonthDays();

		HSSFSheet sheet = workbook.createSheet(sheetName);

		int actRow = 0;

		createHeaderRow(workbook, sheet, actRow, days);

		actRow++;

		createEmployeeTable(workbook, sheet, actRow, employees, days);

		normalizeBorder(sheet, serviceMonth.getCurrentMonthDays());
	}

	private void normalizeBorder(HSSFSheet sheet, int days) {
		for (int i = 0; i <= days; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	private void createEmployeeTable(HSSFWorkbook workbook, HSSFSheet sheet, int actRow, Set<Employee> employees,
			int day) {

		int localRowIndex = actRow;
		List<Employee> employeeList = new ArrayList<Employee>(employees);
		Collections.sort(employeeList);
		for (int i = 0; i < employees.size(); i++) {

			Employee emp = employeeList.get(i);
			HSSFRow rowStart = sheet.createRow(localRowIndex);
			localRowIndex++;

			oneRow(workbook, rowStart, emp, day);
		}
	}

	private void oneRow(HSSFWorkbook workbook, HSSFRow row, Employee emp, int day) {
		for (int j = 0; j <= day + 2; j++) {
			HSSFCell cell = row.createCell(j);

			if (j == 0) {
				cell.setCellValue(emp.getName());
			} else if (j == day + 1) {
				cell.setCellValue(plan.getHolidayHours(emp));
			} else if (j == day + 2) {
				cell.setCellValue(plan.sumWorkingHours(emp));
			} else if (serviceEvent.isSick(emp, j)) {
				cell.setCellValue(SICK_SHORT);
			} else {
				if (serviceEvent.isOnHoliday(emp, j)) {
					cell.setCellValue(HOLIDAY_SHORT);
				} else {
					setCellValue(emp, cell, j);
				}
			}
			setCellStyle(workbook, cell, j);
		}
	}

	private void setCellValue(Employee emp, HSSFCell cell, int actDay) {
		String value = plan.getTime(emp, actDay);
		cell.setCellValue(value);
	}

	private void createHeaderRow(HSSFWorkbook wb, HSSFSheet sheet, int rowNum, int days) {
		HSSFRow row = sheet.createRow(rowNum);
		for (int j = 0; j <= days + 2; j++) {
			HSSFCell cell = row.createCell(j);
			if (j == 0) {
				cell.setCellValue(NAMES);
			} else {
				if (j == days + 1) {
					cell.setCellValue(HOLIDAYS);
				} else {
					if (j == days + 2) {
						cell.setCellValue(OSSZ_ORA);
					} else {
						setCellStyle(wb, cell, j);
						cell.setCellValue(j);
					}
				}
			}
		}
	}

	private CellStyle getSaturdayCellStyle(HSSFWorkbook wb) {
		// Aqua background
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	private CellStyle getSundayCellStyle(HSSFWorkbook wb) {
		// Aqua background
		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.AQUA.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return style;
	}

	private void setCellStyle(HSSFWorkbook wb, HSSFCell cell, int day) {

		Calendar mycal = new GregorianCalendar();
		mycal.set(Calendar.DAY_OF_MONTH, day);
		int dayOfWeek = mycal.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek) {
		case Calendar.SATURDAY:
			cell.setCellStyle(getSaturdayCellStyle(wb));
			break;
		case Calendar.SUNDAY:
			cell.setCellStyle(getSundayCellStyle(wb));
			break;

		default:
			break;
		}
	}

}
