package co.doeat.activity.service.impl;

import java.util.List;
import java.util.Map;

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
   public FollowVO follow(FollowVO vo) {
      return followMapper.follow(vo);
   }

   // 언팔로우 하기
   @Override
   public FollowVO unfollow(FollowVO vo) {
      return followMapper.unfollow(vo);
   }

   // 팔로우 유무
   @Override
   public int isFollow(FollowVO vo) {
      return followMapper.isFollow(vo);
   }

   // 팔로워 리스트 조회
   @Override
   public List<FollowVO> selectFollowerList(String userId) {
      return followMapper.selectFollowerList(userId);
   }

   // 팔로잉 리스트 조회
   @Override
   public List<FollowVO> selectFollowingList(String userId) {
      return followMapper.selectFollowingList(userId);
   }
   
   // 팔로워, 팔로잉 리스트 조회
   @Override
   public Map<String, Object> followCount(String userId) {
      return followMapper.followCount(userId);
   }

   // 탈퇴시 팔로우 전체 삭제
   @Override
   public void deleteAllFollow(String userId) {
   }
   
}
