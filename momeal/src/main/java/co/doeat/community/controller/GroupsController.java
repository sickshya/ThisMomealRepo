package co.doeat.community.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;
import co.doeat.community.service.GroupsService;
import co.doeat.community.service.GroupsVO;
import co.doeat.community.service.UserService;

@Controller
public class GroupsController {
	@Autowired
	GroupsService groupsService;

	@Autowired
	UserService userService;

	@Autowired
	MealService mealService;
	
	// 그룹 리스트 페이지로 이동
	@RequestMapping("/groupsList")
	public String groupsList(Model model, GroupsVO vo, HttpSession session) {
		vo.setUserId((String)session.getAttribute("userId"));
		model.addAttribute("grList", groupsService.grpList(vo));
		return "groups/groupsList";
	}
	
	@RequestMapping("/groupsList/{no}")
	@ResponseBody
	public List<GroupsVO> memList(@PathVariable int no) {
		return groupsService.membList(no);
	}

	// 그룹 피드 페이지로 이동
	@RequestMapping("/groupsFeed/{no}")
	public String groupsFeed(@PathVariable int no, Model model) {
		model.addAttribute("no", no);
		
		return "groups/groupsFeed";
	}

	// 그룹 멤버 상세페이지
	@RequestMapping("/groupsSelect/{userId}/{postDate}")
	public String GroupsSelect(@PathVariable String userId, @PathVariable String postDate, Model model,
			HttpSession session) throws ParseException {
		MealVO vo = new MealVO();
		vo.setUserId(userId);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// 문자열 -> Date
		Date date = formatter.parse(postDate);
		vo.setPostDate(date);

		model.addAttribute("selects", userService.grpSelect(userId));
		model.addAttribute("mealImg", mealService.mealList(vo));

		return "groups/groupsSelect";
	}

}