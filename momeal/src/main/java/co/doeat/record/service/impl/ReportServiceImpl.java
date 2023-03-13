package co.doeat.record.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.record.mapper.ReportMapper;
import co.doeat.record.service.ReportService;
import co.doeat.record.service.ReportVO;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportMapper reportMapper;

	@Override
	public List<ReportVO> usrRprtAllList() {
		return reportMapper.usrRprtAllList();
	}
	
	@Override
	public int rprtProcess() {
		return reportMapper.rprtProcess();
	}
}
