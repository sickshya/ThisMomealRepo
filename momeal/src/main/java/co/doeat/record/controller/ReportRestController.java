package co.doeat.record.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ReportRestController {

	@RequestMapping("/admin/rprtResolvCd1.do")
	public String reprtResolvCd1(Model model, HttpServletRequest request) {
		log.info("++++++++request++++++++++++++++++++++++++++++++++"+request.getParameter("usrRprtNo"));
		log.info("+++++++++request+++++++++++++++++++++++++++++++++++++"+request.getParameter("reportee"));
		
		return "true";
	}
}
