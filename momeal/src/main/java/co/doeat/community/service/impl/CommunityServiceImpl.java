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
	public List<Map<String, Object>> getCmntList(String id) {
		return communityMapper.getCmntList(id);
	}

	// 커뮤니티 단건조회
	@Override
	public MealVO getCommunity(Map<String, Object> map) {
		return communityMapper.getCommunity(map);
	}

}