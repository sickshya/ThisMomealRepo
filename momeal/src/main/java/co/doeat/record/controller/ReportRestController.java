package co.doeat.record.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.record.service.ReportService;
import co.doeat.record.service.ReportVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ReportRestController {

	@Autowired ReportService reportService;
	
	@RequestMapping("/admin/rprtResolvCd1.do")
	public String reprtResolvCd1(ReportVO vo) {
		log.info("++++++++request++++++++++++++++++++++++++++++++++"+vo.getUsrRprtNo());
		log.info("+++++++++request+++++++++++++++++++++++++++++++++++++"+vo.getReportee());
		reportService.rprtProcess();
		return "true";
	}
}
