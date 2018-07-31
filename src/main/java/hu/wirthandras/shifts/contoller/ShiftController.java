package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.wirthandras.shifts.services.ShiftService;

@Controller
public class ShiftController {

	private ShiftService shiftService;

	@Autowired
	public void setShiftService(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

	@RequestMapping("shifts")
	public String shifts(Model model) {
		model.addAttribute("shifts", shiftService.getAll());
		return "shifts";
	}
}
