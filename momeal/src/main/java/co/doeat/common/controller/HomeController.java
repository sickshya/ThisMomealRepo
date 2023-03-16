package co.doeat.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.community.service.CommunityService;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.GroupPurchaseService;

@Controller
public class HomeController {

	@Autowired ChallengeService challengeService;
	@Autowired CommunityService communityService;
	@Autowired ExperienceService ExperienceService;
	@Autowired GroupPurchaseService groupPurchaseService;
	
	@RequestMapping(value = {"/", "/home", "/main"})
	public String home(Model model, HttpSession session) {
		model.addAttribute("hmChlgList", challengeService.hmChlgList());
		
		return "base/home";
	}
}
