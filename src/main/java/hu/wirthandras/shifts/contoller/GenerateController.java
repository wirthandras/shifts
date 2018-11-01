package hu.wirthandras.shifts.contoller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
public class GenerateController extends AbstractControllerBase {

	private static Logger LOGGER = Logger.getLogger(GenerateController.class.getName());

	@Autowired
	private SpreadSheetService excelService;

	@GetMapping("/generate")
	public String generateScreen() {
		return getTempateFolder() + "generate";
	}

	@Override
	protected String getTempateFolder() {
		return "generate/";
	}

	@GetMapping(value = "/spreadsheet", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource downloadPlan(HttpServletResponse response) {
		FileSystemResource resource = new FileSystemResource(excelService.downloadPlan());

		try {
			String fileName = URLEncoder.encode(resource.getFilename(), "UTF-8");
			fileName = URLDecoder.decode(fileName, "ISO8859_1");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		} catch (UnsupportedEncodingException e) {
			LOGGER.severe(e.getMessage());
		}
		return resource;
	}
}
