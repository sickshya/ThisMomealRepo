package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.MealVO;
import co.doeat.community.service.GroupsVO;

public interface GroupsMapper {

	public List<GroupsVO> groupsList(String postDate); // 그룹 전체 리스트

	public Map<String, Object> groupsSelect(int no); // 그룹 단건조회

	int insertGroups(GroupsVO vo); // 그룹 즐겨찾기 추가

}
