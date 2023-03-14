package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;

public interface CommunityMapper {
	
	public List<Map<String, Object>> getCmntList(MealVO vo); // 전체조회
	
	public List<Map<String, Object>> likeRankCmntList(String id); // 인기순(좋아요 많은 순) 조회
	
	public MealVO getCommunity(Map<String, Object>map); // 단건조회
	
	public int getMaxFeedNo(); // 게시물 max 번호
	
}
