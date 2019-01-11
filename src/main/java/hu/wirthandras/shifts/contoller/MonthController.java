package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hu.wirthandras.shifts.services.MonthService;

@Controller
public class MonthController {
	
	@Autowired
	private MonthService service;

	@GetMapping("/monthly")
	public String monthlyWorkingHours(Model model) {
		model.addAttribute("hours", service.getAll());
		return "monthly";
	}
}
