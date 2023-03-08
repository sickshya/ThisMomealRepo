package co.doeat.record.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.record.service.AttendanceVO;

public interface AttendanceMapper {
	// 출석체크 insert
	int atInsert(AttendanceVO vo);

	// 출석정보 가져오기
	List<Map<String, Object>> getAttendanceList(String id);
	
	//출석중복체크
	boolean ajaxAtCheck(AttendanceVO vo);
}
