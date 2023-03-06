package co.doeat.community.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.community.service.GroupsService;

@Controller
public class GroupsController {
	@Autowired
	GroupsService groupsService;

	@RequestMapping("/groupsFeed")
	public String groupsFeed() {
		return "groups/groupsFeed";
	}

	@RequestMapping("/GroupsSelect")
	public String GroupsSelect() {
		return "groups/groupsSelect";
	}

//	@RequestMapping("/GroupsSelect/{userId}")
//	public String GroupsSelect(Model model, @PathVariable String userId, HttpSession session) {
//		
//		model.addAttribute("grpSelect", groupsService.groupsSelect(userId));
//		return "groups/groupsSelect";
//	}
}