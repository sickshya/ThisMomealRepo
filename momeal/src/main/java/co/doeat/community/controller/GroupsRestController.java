package co.doeat.community.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.community.service.GroupsService;
import co.doeat.community.service.GroupsVO;

@RestController
public class GroupsRestController {
	@Autowired
	GroupsService groupsService;

	// 지정한 그룹 날짜에 따른 피드 불러오기
	@RequestMapping("/groupsFeed/{postDate}/{no}")
	public List<GroupsVO> myFeedList(Model model, @PathVariable String postDate, @PathVariable int no,
			HttpSession session) {

//		session.setAttribute("userId", "user1");
//		String userId = (String) session.getAttribute("userId");

		return groupsService.grpSelect(postDate, no);
	}

	// 그룹리스트에서 모달로 멤버리스트 보여줌
	@RequestMapping("/groupsList/{no}")
	public List<GroupsVO> memList(@PathVariable int no) {
		return groupsService.membList(no);
	}

	// 참여중인 그룹의 멤버탈퇴
	@PostMapping("/grpMembDel/{userId}/{grpNo}")
	public int grpMembDel(@PathVariable String userId, @PathVariable int grpNo) {
		return groupsService.grpMembDel(userId, grpNo);
	}

	// 내가 만든 그룹 삭제
	@PostMapping("/grpDel/{no}")
	public int grpDel(@PathVariable int no) {
		return groupsService.grpDel(no);
	}

	// 그룹 만들기
	@PostMapping("/grpInsert")
	public GroupsVO grpInsert(GroupsVO vo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		vo.setUserId(userId);

		groupsService.grpInsert(vo);
		return vo;
	}
}
