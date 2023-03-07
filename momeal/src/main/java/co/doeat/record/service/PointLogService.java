package co.doeat.record.service;

import java.util.List;
import java.util.Map;

public interface PointLogService {
	// 포인트 전체 리스트
	List<Map<String, Object>> getPointList(String id);

	// 출석체크 포인트 적립
	int atPointadd(PointLogVO pvo);

}
