package co.doeat.community.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;
import co.doeat.community.service.GroupsService;
import co.doeat.community.service.UserService;

@Controller
public class GroupsController {
	@Autowired
	GroupsService groupsService;

	@Autowired
	UserService userService;

	@Autowired
	MealService mealService;

	@RequestMapping("/groupsFeed")
	public String groupsFeed() {
		return "groups/groupsFeed";
	}

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