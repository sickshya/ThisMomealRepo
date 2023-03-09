package co.doeat.community.service;

import java.util.List;
import java.util.Map;

public interface GroupsService {

	public List<GroupsVO> grpSelect(String postDate, int no); // 그룹 단건조회

	public Map<String, Object> grpMembSelect(String userId); // 그룹 멤버 단건조회

	public List<GroupsVO> grpAllList(GroupsVO vo); // 참여중인 그룹 전체리스트

	public List<GroupsVO> membList(int no); // 해당그룹 멤버리스트 조회

	public int grpMembDel(String userId, int grpNo); // 참여중인 그룹의 멤버탈퇴

	public int grpDel(int no); // 내가 만든 그룹 삭제

	public int grpInsert(GroupsVO vo); // 그룹 만들기

}