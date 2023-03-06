package co.doeat.activity.service;

import java.util.List;

public interface FollowService {
	FollowVO follow(FollowVO vo); // 팔로우 하기
	FollowVO unfollow(FollowVO vo); // 언팔로우 하기
	int isFollow(FollowVO vo); // 팔로우 유무
	List<FollowVO> selectFolloweeList(String userId); // 팔로우 리스트 조회
	List<FollowVO> selectFollowerList(String userId); // 팔로워 리스트 조회
	void deleteAllFollow(String userId); // 탈퇴시 팔로우 전체 삭제
}
