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
	private CommunityMapper map;
	
	@Override
	public List<Map<String, Object>> getCmntList() {
		return map.getCmntList();
	}

	@Override
	public MealVO getCommunity(int no) {
		return map.getCommunity(no);
	}
	
}
