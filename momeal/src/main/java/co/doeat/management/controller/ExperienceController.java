package co.doeat.management.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;

@Controller
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@Autowired
	ServletContext servletContext;

	@Autowired
	private UserService userService;

	@Value("${momeal.saveImg}")
	private String saveImg;

	// 체험단(전체조회)
	@RequestMapping("/experienceList")
	public String experienceList(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("ExpList", experienceService.getExperList()); // 전체
		model.addAttribute("ExpingList", experienceService.getExperiencing()); // 진행
		return "experience/experienceList";
	}

	// 체험단(단건조회)
	@GetMapping("/experienceDetail/{no}")
	public String experienceDetail(Model model, @PathVariable int no, HttpSession session, ExperienceVO evo) {
		String id = (String) session.getAttribute("userId");
		model.addAttribute("ExpOne", experienceService.ExperOne(no));
		model.addAttribute("no", no);
		return "experience/experienceDetail";
	}

	// 체험단(호출) // select 하는곳 조회먼저.
	@RequestMapping("/expr/exprFrm/{no}")
	public String experience(HttpSession session, @PathVariable int no, Model model) {
		String userId = (String) session.getAttribute("userId");
		model.addAttribute("expSelect", experienceService.ExperOne(no));
		model.addAttribute("userInfo", userService.userSelect(userId));
		System.out.println(no + "체험단정보" + userId + "유저정보");
		return "experience/experienceForm";
	}

	// 체험단(신청) // insert 하는곳 값 불러와야함.
	@RequestMapping("/expr/exprAply.do")
	@ResponseBody
	public String exprAply(HttpSession session, Model model, ExprParticipantsVO pvo, ExperienceVO evo) {
		String userId = (String) session.getAttribute("userId");
		experienceService.expInsert(pvo);
		return "신청이 완료되었습니다.";
	}

	// 체험단(신청내역조회)
	@RequestMapping("/expr/expOrderList")
	public String expOrderList(HttpServletRequest request, ExprParticipantsVO vo, Model model, ExperienceVO evo) {
		HttpSession session = request.getSession();
		session.getAttribute("userId");
		model.addAttribute("expAplyList", experienceService.expOrderList());
		System.out.println(experienceService.expOrderList());
		return "experience/expOrderList";
	}

	// 관리자 ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@RequestMapping("adminExperienceGroup")
	public String adminExperienceGroup(Model model) {
		model.addAttribute("exprList", experienceService.adminExperienceGroup());
		return "admin/adminExperienceGroup";
	}
}
