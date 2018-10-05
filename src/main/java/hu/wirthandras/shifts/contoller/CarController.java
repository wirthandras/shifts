package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.wirthandras.shifts.domain.Car;
import hu.wirthandras.shifts.services.CarService;

@Controller
public class CarController {
	
	private static final String templateFolder = "car/";
	
	@Autowired
	private CarService service;

	@GetMapping("/car/{id}")
	public String car(@PathVariable String id, Model model) {
		model.addAttribute("car", service.getCar(id));
		return templateFolder + "car";
	}
	
	@GetMapping("/cars")
	public String car(Model model) {
		model.addAttribute("cars", service.getCars());
		return templateFolder + "cars";
	}
	
	@GetMapping("/newcar")
	public String newCar(@ModelAttribute("newcar") Car car) {
		return templateFolder + "newcar";
	}
	
	@PostMapping(value="/newcar")
	public String newCarSave(@ModelAttribute Car car) {
		service.save(car);
		return "redirect:" + templateFolder + "newcar";
	}
	
}
