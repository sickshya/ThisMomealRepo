package co.doeat.community.service;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;

public interface CommunityService {
	// 메인
	public List<MealVO> hmCtyList(); // 메인에 뿌려줄 식단리 전체조회 리스트

	public List<Map<String, Object>> getCmntList(MealVO vo); // 전체조회

	public List<Map<String, Object>> likeRankCmntList(String id); // 인기순(좋아요 많은 순) 조회

	public MealVO getCommunity(Map<String, Object> map); // 단건조회

	public int getMaxFeedNo(); // 게시물 max 번호

}
