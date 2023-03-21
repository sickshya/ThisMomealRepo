package co.doeat.config.security;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

//	@RequestMapping("/error")
//	public String error(HttpRequest request) {
//		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//		return "error";
//	}
	
	@RequestMapping("/error")
	public String error() {
		return "error/error";
	}
}
