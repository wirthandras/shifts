package hu.wirthandras.shifts.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends AbstractControllerBase {
	
	@GetMapping("/login")
	public String login() {
		return getTempateFolder() + "login";
	}

	@Override
	protected String getTempateFolder() {
		return "login/";
	}
	
}
