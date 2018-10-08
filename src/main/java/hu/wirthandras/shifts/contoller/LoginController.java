package hu.wirthandras.shifts.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends AbstractControllerBase {
	
	@RequestMapping("/login")
	public String login() {
		return getTempateFolder() + "login";
	}

	@Override
	protected String getTempateFolder() {
		return "login/";
	}
	
}
