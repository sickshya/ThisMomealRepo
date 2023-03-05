package co.doeat.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.activity.mapper.FollowMapper;
import co.doeat.activity.service.FollowService;
import co.doeat.activity.service.FollowVO;

@Service
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	FollowMapper followMapper;

	// 팔로우 하기
	@Override
	public void follow(FollowVO vo) {
	}

	// 언팔로우 하기
	@Override
	public void unfollow(FollowVO vo) {
	}

	// 팔로우 유무
	@Override
	public int isFollow(FollowVO vo) {
		return followMapper.isFollow(vo);
	}

	// 팔로우 리스트 조회
	@Override
	public List<FollowVO> selectFolloweeList(String userId) {
		return followMapper.selectFolloweeList(userId);
	}

	// 팔로워 리스트 조회
	@Override
	public List<FollowVO> selectFollowerList(String userId) {
		return followMapper.selectFollowerList(userId);
	}

	// 탈퇴시 팔로우 전체 삭제
	@Override
	public void deleteAllFollow(String userId) {
	}
	
}
