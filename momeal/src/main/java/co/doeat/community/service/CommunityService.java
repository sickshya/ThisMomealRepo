package co.doeat.community.service;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;

public interface CommunityService {
	
	public List<Map<String, Object>> getCmntList(); // 전체조회
	public MealVO getCommunity(Map<String, Object>map); // 단건조회
	
}
