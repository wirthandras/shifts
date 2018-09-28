package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.wirthandras.shifts.domain.Employee;
import hu.wirthandras.shifts.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping("employee/{id}")
	public String employee(@PathVariable String id, Model model) {
		model.addAttribute("employee", service.getEmployee(id));
		return "employee";
	}
	
	@RequestMapping("employees")
	public String employee(Model model) {
		model.addAttribute("employees", service.getEmployees());
		return "employees";
	}
	
	@RequestMapping("newemployee")
	public String newEmployee(Model model) {
		model.addAttribute("newemployee", new Employee());
		return "newemployee";
	}
	
	@RequestMapping(value="/newemployeesave", method=RequestMethod.POST)
	public String newEmployeeSave(@ModelAttribute Employee newEmployee) {
		service.save(newEmployee);
		return "redirect:newemployee";
	}

}
