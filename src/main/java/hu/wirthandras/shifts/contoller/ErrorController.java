package hu.wirthandras.shifts.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("*")
	public String error() {
		//TODO improve to handle any type of possible errors 
		return "404";
	}
	
}
