package co.doeat.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.community.service.CommunityService;
import co.doeat.community.service.UserService;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.GroupPurchaseService;

@Controller
public class HomeController {

	@Autowired
	ChallengeService challengeService;
	@Autowired
	CommunityService communityService;
	@Autowired
	ExperienceService ExperienceService;
	@Autowired
	GroupPurchaseService groupPurchaseService;
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/home", "/main"})
	public String home(Model model, HttpSession session) {
		model.addAttribute("hmChlgList", challengeService.hmChlgList()); // 메인화면 내 컨텐츠 조회 1 : 챌린지
		model.addAttribute("hmCtyList", communityService.hmCtyList()); // 메인화면 내 컨텐츠 조회 2 : 식단
		model.addAttribute("hmExprList", ExperienceService.hmExprList()); // 메인화면 내 컨텐츠 조회 3 : 체험단

		String userId = (String) session.getAttribute("userId");
		model.addAttribute("loginInfo", userService.userSelect(userId)); // 로그인 한 회원 정보 등록
		model.addAttribute("hmUserInfo", userService.hmUserInfo(userId)); // 로그인 한 유저의 식단게시글, 팔로잉, 팔로워 수
		return "base/home";
	}
}
