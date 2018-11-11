package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.wirthandras.shifts.domain.day.EmployeeEventResponse;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.services.EmployeeService;
import hu.wirthandras.shifts.services.EventService;
import hu.wirthandras.shifts.services.MonthService;

@Controller
public class EmployeeController extends AbstractControllerBase {

	@Autowired
	private EventService eventService;

	@Autowired
	private EmployeeService service;

	@Autowired
	private MonthService serviceMonth;

	@GetMapping("employee/{id}")
	public String employee(@PathVariable String id, Model model) {
		model.addAttribute("employee", service.getEmployee(id));
		model.addAttribute("month", serviceMonth.getMonthName());
		model.addAttribute("days", serviceMonth.getDaysInCurrentMonth());
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
	public String newEmployeeSave(@ModelAttribute Employee e) {
		service.save(e);
		return "redirect:newemployee";
	}

	@PostMapping(value = "/api/employees")
	public ResponseEntity<?> api(@RequestParam("dayId") String dayId, @RequestParam("employee") String employee) {
		EmployeeEventResponse result = new EmployeeEventResponse(eventService.getEmployeeEvents(dayId, employee));
		return ResponseEntity.ok(result);
	}

	@Override
	protected String getTempateFolder() {
		return "employee/";
	}

}
