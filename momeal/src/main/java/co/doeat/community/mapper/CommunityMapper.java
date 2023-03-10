package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;

public interface CommunityMapper {
	
	public List<Map<String, Object>> getCmntList(String id); // 전체조회
	
	public MealVO getCommunity(Map<String, Object>map); // 단건조회
	
}
