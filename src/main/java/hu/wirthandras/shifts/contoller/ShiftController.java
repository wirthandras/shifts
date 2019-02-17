package hu.wirthandras.shifts.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.wirthandras.shifts.domain.car.type.CarType;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.exception.ShiftNotFoundException;
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

	@RequestMapping("shift/{id}/action/remove")
	public String removeSpecifiedShift(@PathVariable("id") String id) {
		shiftService.remove(id);
		return "redirect:/shifts";
	}

	@RequestMapping("shift/{id}/action/wish")
	public String addShiftWish(@PathVariable("id") String id) {
		shiftService.addWish(id);
		return "redirect:/shifts";
	}

	@RequestMapping("shift/{id}/action/wishclear")
	public String removeShiftWish(@PathVariable("id") String id) {
		shiftService.removeWish(id);
		return "redirect:/shifts";
	}

	@RequestMapping("shiftplanner/{id}/action/remove")
	public String removeSpecifiedShiftInterval(@PathVariable("id") String id) {
		shiftService.removeInterval(id);
		return "redirect:/shiftplanner";
	}

	@RequestMapping("shift/{id}")
	public String shift(@PathVariable String id, Model model) throws ShiftNotFoundException {
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

	@ModelAttribute("carTypes")
	public List<CarType> getCarTypes() {
		return shiftService.getCarTypes();
	}

	@PostMapping("shiftplanner")
	public String shiftPlannerAdd(@ModelAttribute("shiftinterval") ShiftInterval interval, Model model) {
		shiftService.addInterval(interval);
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
