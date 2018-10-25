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
import hu.wirthandras.shifts.services.interval.CarLockedException;
import hu.wirthandras.shifts.services.interval.ShiftIntervalAlreadyExistException;

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
		model.addAttribute("cars", shiftService.getFreeCars());
		return getTempateFolder() + "shiftplanner";
	}

	@PostMapping("shiftplanner")
	public String shiftPlannerAdd(@ModelAttribute("shiftinterval") ShiftInterval interval, Model model) {
		try {
			shiftService.addInterval(interval);
		} catch (ShiftIntervalAlreadyExistException e) {
			model.addAttribute("cars", shiftService.getFreeCars());
			model.addAttribute("intervals", shiftService.getIntervals());
			model.addAttribute("errorKey", "shiftIsAlreadyExist");
			return getTempateFolder() + "shiftplanner";
		} catch (CarLockedException e) {
			model.addAttribute("cars", shiftService.getFreeCars());
			model.addAttribute("intervals", shiftService.getIntervals());
			model.addAttribute("errorKey", "carIsLocked");
			return getTempateFolder() + "shiftplanner";
		}
		return "redirect:shiftplanner";
	}

	@PostMapping("shiftplannergenerate")
	public String shiftGenerate() {
		shiftService.generate();
		return "redirect:shiftplanner";
	}
	
	@PostMapping("shiftplannerclear")
	public String shiftClear() {
		shiftService.clear();
		return "redirect:shiftplanner";
	}

}
