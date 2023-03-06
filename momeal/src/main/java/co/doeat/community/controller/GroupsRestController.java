package co.doeat.community.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.community.service.GroupsService;
import co.doeat.community.service.GroupsVO;

@RestController
public class GroupsRestController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	GroupsService groupsService;

	// 날짜에 따른 피드 불러오기
	@RequestMapping("/groupsFeed/{postDate}")
	public List<GroupsVO> myFeedList(Model model, @PathVariable String postDate, HttpSession session,
			HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");

		String userId = (String) session.getAttribute("userId");
		System.out.println("===========================" + postDate);

		return groupsService.groupsList(postDate);
	}

}
