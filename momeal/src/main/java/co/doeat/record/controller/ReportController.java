package co.doeat.record.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.record.service.ReportService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ReportController {

	@Autowired
	ReportService reportService;

	@GetMapping("/admin/reportManagement")
	public String reportManagement(Model model) {
		model.addAttribute("rprtList", reportService.usrRprtAllList());
		return "admin/reportManagement";
	}

	
}
