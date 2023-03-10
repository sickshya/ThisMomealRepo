package co.doeat.record.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import co.doeat.community.mapper.UserMapper;
import co.doeat.community.service.UsersVO;
import co.doeat.record.mapper.AttendanceMapper;
import co.doeat.record.mapper.PointLogMapper;
import co.doeat.record.service.AttendanceService;
import co.doeat.record.service.AttendanceVO;
import co.doeat.record.service.PointLogVO;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceMapper attendanceMapper;
	@Autowired
	private PointLogMapper pointLogMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public int atInsert(AttendanceVO vo) {
		return attendanceMapper.atInsert(vo);
	}

//	@Override
//	public List<Map<String, Object>> getAttendanceList(String id) {
//		return attendanceMapper.getAttendanceList(id);
//	}

	@Override
	public Boolean ajaxAtCheck(AttendanceVO vo, PointLogVO pvo, UsersVO uvo) {
		System.out.println("잘돌아감???");
		Boolean b = attendanceMapper.ajaxAtCheck(vo);
		if (b == null) {
			// 포인트로그테이블에 저장
			pointLogMapper.atPointadd(pvo);
			// 회원테이블에 누적포인트 업데이트
			userMapper.updateATPoint(uvo);
			// 출석테이블에 저장
			attendanceMapper.atInsert(vo);
		} else {

		}
		return b;

	}

}
