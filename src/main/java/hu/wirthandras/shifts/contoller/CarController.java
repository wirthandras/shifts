package hu.wirthandras.shifts.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.wirthandras.shifts.domain.Car;
import hu.wirthandras.shifts.services.CarService;

@Controller
public class CarController {
	
	@Autowired
	private CarService service;

	@RequestMapping("/car/{id}")
	public String car(@PathVariable String id, Model model) {
		model.addAttribute("car", service.getCar(id));
		return "car";
	}
	
	@RequestMapping("/cars")
	public String car(Model model) {
		model.addAttribute("cars", service.getCars());
		return "cars";
	}
	
	@RequestMapping("/newcar")
	public String newCar(Model model) {
		model.addAttribute("newcar", new Car());
		return "newcar";
	}
}
