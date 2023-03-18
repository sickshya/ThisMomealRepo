package co.doeat.activity.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.activity.service.FollowVO;

public interface FollowMapper {
	
	FollowVO follow(FollowVO vo); // 팔로우 하기
	
	FollowVO unfollow(FollowVO vo); // 언팔로우 하기
	
	int isFollow(FollowVO vo); // 팔로우 유무
	
	List<FollowVO> selectFollowerList(String userId); // 팔로워 리스트 조회
	
	List<FollowVO> selectFollowingList(String userId); // 팔로잉 리스트 조회
	
	FollowVO followCount(String userId); // 팔로워, 팔로잉 회원 수 카운트
	
	void deleteAllFollow(String userId); // 탈퇴시 팔로우 전체 삭제
}
