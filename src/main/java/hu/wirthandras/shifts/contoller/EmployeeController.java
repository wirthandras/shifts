package hu.wirthandras.shifts.contoller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.wirthandras.shifts.domain.day.EventResponse;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;
import hu.wirthandras.shifts.domain.employee.EmployeeEventInput;
import hu.wirthandras.shifts.domain.job.Job;
import hu.wirthandras.shifts.exception.EmployeeNotFoundException;
import hu.wirthandras.shifts.services.EmployeeService;
import hu.wirthandras.shifts.services.EventService;
import hu.wirthandras.shifts.services.LocalizationService;
import hu.wirthandras.shifts.services.MonthService;

@Controller
public class EmployeeController extends AbstractControllerBase {

	private static final int DEFAULT_FULL_TIME_PERCENT = 100;

	@Autowired
	private EventService eventService;

	@Autowired
	private EmployeeService service;

	@Autowired
	private MonthService serviceMonth;

	@Autowired
	private LocalizationService serviceLocalization;

	@GetMapping("employee/{id}")
	public String employee(@PathVariable String id, Model model) throws EmployeeNotFoundException {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("month", serviceMonth.getMonthName());
		model.addAttribute("days", serviceMonth.getDaysInCurrentMonth());
		model.addAttribute("eventDays", eventService.getEmployeeEventsDays(id, serviceMonth.getCurrentMonth()));
		model.addAttribute("method", "UpdateStatus(\"#container\", this.id, " + id + ")");

		return getTempateFolder() + "employee";
	}

	@GetMapping("employees")
	public String employee(Model model) {
		model.addAttribute("employees", service.getEmployees());
		return getTempateFolder() + "employees";
	}

	@GetMapping("/newemployee")
	public String newEmployee(@ModelAttribute("newemployee") Employee e) {
		return getTempateFolder() + "newemployee";
	}

	@PostMapping(value = "/newemployee")
	public String newEmployeeSave(@Validated @ModelAttribute("newemployee") Employee e, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return getTempateFolder() + "newemployee";
		}

		service.save(e);
		return "redirect:newemployee";
	}

	@PostMapping(value = "/employeeevent", params = "actionAdd")
	public String newEmployeeEvent(@ModelAttribute("newevent") EmployeeEventInput input, HttpServletRequest request)
			throws EmployeeNotFoundException {
		eventService.addEmployeEvent(input);
		return "redirect:" + request.getHeader(REFERER);
	}

	@PostMapping(value = "/employeeevent", params = "actionRemove")
	public String removeEmployeeEvent(@ModelAttribute("newevent") EmployeeEventInput input, HttpServletRequest request)
			throws EmployeeNotFoundException {
		eventService.removeEmployeEvent(input);
		return "redirect:" + request.getHeader(REFERER);
	}

	@PostMapping(value = "/api/employees")
	public ResponseEntity<?> api(@RequestParam("dayId") String dayId, @RequestParam("employee") String employee)
			throws EmployeeNotFoundException {
		List<EmployeeEvent> employeeEvents = eventService.getEmployeeEvents(dayId, employee);
		EventResponse result = new EventResponse(serviceLocalization.localizeEmployeeEvents(employeeEvents));
		return ResponseEntity.ok(result);
	}

	@ModelAttribute("defaultFullTimePercent")
	public int getDefaultFullTimePercent() {
		return DEFAULT_FULL_TIME_PERCENT;
	}

	@ModelAttribute("jobs")
	public List<Job> getJobs() {
		return service.getJobs();
	}

	@Override
	protected String getTempateFolder() {
		return "employee/";
	}

}
