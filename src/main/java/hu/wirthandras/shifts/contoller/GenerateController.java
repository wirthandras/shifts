package hu.wirthandras.shifts.contoller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.wirthandras.shifts.services.SpreadSheetService;

@Controller
public class GenerateController {

	private static Logger LOGGER = Logger.getLogger(GenerateController.class.getName());

	@Autowired
	private SpreadSheetService excelService;

	@GetMapping("/generate")
	public String generateScreen() {
		return "generate/generate";
	}

	@GetMapping(value = "/spreadsheet", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource downloadPlan(HttpServletResponse response) {

		FileSystemResource resource = new FileSystemResource(excelService.downloadPlan());

		response.setHeader("Content-disposition", "attachment; filename=" + resource.getFilename());
		LOGGER.log(Level.INFO, "File Download: " + resource.getFilename());
		return resource;
	}

}
