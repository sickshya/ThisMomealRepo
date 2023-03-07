package co.doeat.record.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.record.mapper.PointLogMapper;
import co.doeat.record.service.PointLogService;
import co.doeat.record.service.PointLogVO;

@Service
public class PointLogServiceImpl implements PointLogService {
	@Autowired
	private PointLogMapper pointLogMapper;

	@Override
	public List<Map<String, Object>> getPointList(String id) {
		return pointLogMapper.getPointList(id);
	}

	@Override
	public int atPointadd(PointLogVO pvo) {
		return pointLogMapper.atPointadd(pvo);
	}

	
	
	
}
