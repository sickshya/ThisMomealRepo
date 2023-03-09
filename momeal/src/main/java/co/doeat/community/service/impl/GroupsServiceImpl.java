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
	public List<GroupsVO> grpSelect(String postDate, int no) {
		return groupsMapper.grpSelect(postDate, no);
	}

	@Override
	public Map<String, Object> grpMembSelect(String userId) {
		return groupsMapper.grpMembSelect(userId);
	}

	@Override
	public List<GroupsVO> grpAllList(GroupsVO vo) {
		return groupsMapper.grpAllList(vo);
	}

	@Override
	public List<GroupsVO> membList(int no) {
		return groupsMapper.membList(no);
	}

	@Override
	public int grpMembDel(String userId, int grpNo) {
		return groupsMapper.grpMembDel(userId, grpNo);
	}

	@Override
	public int grpDel(int no) {
		return groupsMapper.grpDel(no);
	}

	@Override
	public int grpInsert(GroupsVO vo) {
		groupsMapper.grpInsert(vo);
		return groupsMapper.membInsert(vo);
	}

}
