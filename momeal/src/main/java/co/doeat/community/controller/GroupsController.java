package co.doeat.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.community.service.GroupsService;

@Controller
public class GroupsController {
	@Autowired
	GroupsService groupService;

	@RequestMapping("/groupsFeed")
	public String groupsFeed() {
		return "groups/groupsFeed";
	}
}