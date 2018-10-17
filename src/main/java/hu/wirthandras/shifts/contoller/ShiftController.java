package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.services.ShiftService;

@Controller
public class ShiftController extends AbstractControllerBase {
	
	private ShiftService shiftService;

	@Autowired
	public void setShiftService(ShiftService shiftService) {
		this.shiftService = shiftService;
	}

	@RequestMapping("shifts")
	public String shifts(Model model) {
		model.addAttribute("shifts", shiftService.getAll());
		return getTempateFolder() + "shifts";
	}
	
	@RequestMapping("shift/{id}")
	public String shift(@PathVariable String id, Model model) {
		model.addAttribute("shift", shiftService.getShift(id));
		return getTempateFolder() + "shift";
	}

	@Override
	protected String getTempateFolder() {
		return "shift/";
	}
	
	@GetMapping("shiftplanner")
	public String shiftPlanner(@ModelAttribute("shiftinterval") ShiftInterval interval, Model model) {
		model.addAttribute("intervals", shiftService.getIntervals());
		return getTempateFolder() + "shiftplanner";
	}
	
	@PostMapping("shiftplanner")
	public String shiftPlannerAdd(@ModelAttribute("shiftinterval") ShiftInterval interval) {
		shiftService.addInterval(interval);
		return "redirect:shiftplanner";
	}
	
}
