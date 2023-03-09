package co.doeat.activity.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.activity.service.FollowService;
import co.doeat.activity.service.FollowVO;

@RestController
public class FollowRestController {
	
	@Autowired
	private FollowService followService;
	
	
	// 팔로우 하기(ajax)
	@PostMapping("/follow/{userId}")
	public FollowVO follow(FollowVO vo, HttpSession session, @PathVariable String userId) { // userId = 피드 작성자 id를 받아와야함
		vo.setFollowerId((String)session.getAttribute("userId"));
		vo.setFolloweeId(userId);
		
		// 혹시나 이게 동작하지 않는다면 serviceImpl을 확인해보기... 아무 동작도 안 하고 있을 수 있음
		followService.follow(vo);
		
		return vo;
	}
	
	// 언팔로우 하기(ajax)
	@PostMapping("/unfollow/{userId}")
	public FollowVO unfollow(FollowVO vo, HttpSession session, @PathVariable String userId) {
		vo.setFollowerId((String)session.getAttribute("userId"));
		vo.setFolloweeId(userId);
		
		followService.unfollow(vo);
		
		return vo;
	}
	
	// 팔로워 리스트 출력 (ajax)
	@PostMapping("/follower/{userId}")
	public List<FollowVO> selectFollowerList(@PathVariable String userId) {
		return followService.selectFollowerList(userId);
	}
	
	// 팔로잉 리스트 출력 (ajax)
	@PostMapping("/following/{userId}")
	public List<FollowVO> selectFollowingList(@PathVariable String userId) {
		return followService.selectFollowingList(userId);
	}
	
//	// 팔로워, 팔로잉 회원 수 카운트 (ajax 아님)
//	@RequestMapping("/myFeed")
//	public Map<String, Object> followCount(String userId) {
//		return followService.followCount(userId);
//	}

}
