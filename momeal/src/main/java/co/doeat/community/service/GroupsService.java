package co.doeat.community.service;

import java.util.List;
import java.util.Map;

public interface GroupsService {

	public List<GroupsVO> groupsList(String postDate); // 그룹 전체 리스트

	public Map<String, Object> groupsSelect(String userId); // 그룹 단건조회

	int insertGroups(GroupsVO vo); // 그룹 즐겨찾기 추가
}
