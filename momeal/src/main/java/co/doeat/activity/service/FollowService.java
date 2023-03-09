package co.doeat.activity.service;

import java.util.List;
import java.util.Map;

public interface FollowService {
	
	FollowVO follow(FollowVO vo); // 팔로우 하기
	
	FollowVO unfollow(FollowVO vo); // 언팔로우 하기
	
	int isFollow(FollowVO vo); // 팔로우 유무
	
	List<FollowVO> selectFollowerList(String userId); // 팔로워 리스트 조회
	
	List<FollowVO> selectFollowingList(String userId); // 팔로잉 리스트 조회
	
	Map<String, Object> followCount(String userId); // 팔로워, 팔로잉 회원 수 카운트
	
	void deleteAllFollow(String userId); // 탈퇴시 팔로우 전체 삭제
	
}
