package co.doeat.community.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.activity.service.MealVO;
import co.doeat.community.mapper.CommunityMapper;
import co.doeat.community.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{
	
	@Autowired
	private CommunityMapper communityMapper;
	
	// 커뮤니티 전체조회
	@Override
	public List<Map<String, Object>> getCmntList(MealVO vo) {
		return communityMapper.getCmntList(vo);
	}
	
	// 커뮤니티 인기순(좋아요 많은 순) 조회
	@Override
	public List<Map<String, Object>> likeRankCmntList(String id) {
		return communityMapper.likeRankCmntList(id);
	}

	// 커뮤니티 단건조회
	@Override
	public MealVO getCommunity(Map<String, Object> map) {
		return communityMapper.getCommunity(map);
	}

	// 게시물 max 번호
	@Override
	public int getMaxFeedNo() {
		return communityMapper.getMaxFeedNo();
	}

}