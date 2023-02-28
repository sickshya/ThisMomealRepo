package co.doeat.record.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

	@GetMapping("/adminReportManagement")
	public String adminChart() {
		return "admin/adminReportManagement";
	}
}
