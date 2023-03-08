package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;
import co.doeat.community.service.GroupsVO;

public interface GroupsMapper {

	public List<GroupsVO> groupsList(String postDate, int no); // 그룹 전체 리스트

	public Map<String, Object> groupsSelect(String userId); // 그룹 단건조회
	
	public List<GroupsVO> grpList(GroupsVO vo); // 참여중인 그룹

	public List<GroupsVO> membList(int no); // 해당그룹 멤버리스트 조회

}