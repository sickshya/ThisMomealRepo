package co.doeat.record.service;

import java.util.List;
import java.util.Map;

import co.doeat.community.service.UsersVO;

public interface AttendanceService {
	// 출석체크 insert
	int atInsert(AttendanceVO vo);

	// 출석정보 가져오기
	List<Map<String, Object>> getAttendanceList(String id);

	// 출석중복체크
	public Boolean ajaxAtCheck(AttendanceVO vo, PointLogVO pvo, UsersVO uvo);
}
