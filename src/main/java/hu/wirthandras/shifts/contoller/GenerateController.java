package hu.wirthandras.shifts.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerateController extends AbstractControllerBase {

	@GetMapping("/generate")
	public String generateScreen() {
		return getTempateFolder() + "generate";
	}

	@Override
	protected String getTempateFolder() {
		return "generate/";
	}
}
