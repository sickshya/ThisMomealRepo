package co.doeat.community.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.community.mapper.GroupsMapper;
import co.doeat.community.service.GroupsService;
import co.doeat.community.service.GroupsVO;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	GroupsMapper groupsMapper;

	@Override
	public List<GroupsVO> groupsList(String postDate, int no) {
		return groupsMapper.groupsList(postDate, no);
	}

	@Override
	public Map<String, Object> groupsSelect(String userId) {
		return groupsMapper.groupsSelect(userId);
	}

	@Override
	public List<GroupsVO> grpList(GroupsVO vo) {
		return groupsMapper.grpList(vo);
	}

	@Override
	public List<GroupsVO> membList(int no) {
		return groupsMapper.membList(no);
	}

}
