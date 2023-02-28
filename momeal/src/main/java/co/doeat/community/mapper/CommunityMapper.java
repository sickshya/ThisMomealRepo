package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;

public interface CommunityMapper {
	
	public List<Map<String, Object>> getCmntList(); // 전체조회
	public MealVO getCommunity(int no); // 단건조회
	// mealVO로 받으먼 안될거같기도 하고..syso 찍어서 필요한 컬럼 다 들어오는지 확인해보기! 안 되면 resultType="map"으로 바꿔야할지도..ㅎ
	
}
