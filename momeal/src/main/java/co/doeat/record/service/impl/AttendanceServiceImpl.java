package co.doeat.record.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.record.mapper.AttendanceMapper;
import co.doeat.record.service.AttendanceService;
import co.doeat.record.service.AttendanceVO;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	@Override
	public int atInsert(AttendanceVO vo) {
		return attendanceMapper.atInsert(vo);
	}

	@Override
	public List<Map<String, Object>> getAttendanceList(String id) {
		return attendanceMapper.getAttendanceList(id);
	}

}
