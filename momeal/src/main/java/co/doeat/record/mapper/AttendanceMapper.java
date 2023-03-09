package co.doeat.record.mapper;

import co.doeat.record.service.AttendanceVO;

public interface AttendanceMapper {
	// 출석체크 insert
	int atInsert(AttendanceVO vo);

	// 출석정보 가져오기
	//List<Map<String, Object>> getAttendanceList(String id);
	
	// 출석 중복체크
	Boolean ajaxAtCheck(AttendanceVO vo);
}
