package co.doeat.community.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.community.mapper.GroupsMapper;
import co.doeat.community.service.GroupsService;
import co.doeat.community.service.GroupsVO;

@Service
public class GroupsServiceImpl implements GroupsService{
	
	@Autowired GroupsMapper groupsMapper;

	@Override
	public List<Map<String, Object>> groupsList() {
		return groupsMapper.groupsList();
	}

	@Override
	public Map<String, Object> groupsSelect(int no) {
		return groupsMapper.groupsSelect(no);
	}

	@Override
	public int insertGroups(GroupsVO vo) {
		return groupsMapper.insertGroups(vo);
	}

}
