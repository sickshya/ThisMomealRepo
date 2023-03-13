package co.doeat.record.mapper;

import java.util.List;

import co.doeat.record.service.ReportVO;

public interface ReportMapper {

	public List<ReportVO> usrRprtAllList(); // 신고 내역 전체 리스트
	
	public int rprtProcess();
}
