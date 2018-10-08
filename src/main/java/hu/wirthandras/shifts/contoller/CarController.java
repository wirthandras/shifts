package hu.wirthandras.shifts.contoller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.day.AjaxResponseBody;
import hu.wirthandras.shifts.services.CarService;
import hu.wirthandras.shifts.services.MonthService;

@Controller
public class CarController extends AbstractControllerBase {
		
	@Autowired
	private CarService service;
	@Autowired
	private MonthService serviceMonth;

	@GetMapping("/car/{id}")
	public String car(@PathVariable String id, Model model) {
		model.addAttribute("car", service.getCar(id));
		model.addAttribute("month", serviceMonth.getMonthName());
		model.addAttribute("days", serviceMonth.getDaysInCurrentMonth());
		model.addAttribute("method", "UpdateCar(\"#container\", this.id)");
		return getTempateFolder() + "car";
	}
	
	@GetMapping("/cars")
	public String car(Model model) {
		model.addAttribute("cars", service.getCars());
		return getTempateFolder() + "cars";
	}
	
	@GetMapping("/newcar")
	public String newCar(@ModelAttribute("newcar") Car car) {
		return getTempateFolder() + "newcar";
	}
	
	@PostMapping("/newcar")
	public String newCarSave(@Valid @ModelAttribute Car car, BindingResult br) {
		if(br.hasErrors()) {
			return getTempateFolder() + "newcar";
		}
		service.save(car);
		return "redirect:newcar";
	}
	
	@PostMapping("/api/cars")
	public ResponseEntity<AjaxResponseBody> api(@RequestParam("dayId") String dayId) {
		List<String> events = service.getEvents(dayId);
		AjaxResponseBody result = new AjaxResponseBody(events);
		return ResponseEntity.ok(result);
	}

	@Override
	protected String getTempateFolder() {
		return "car/";
	}
	
}
