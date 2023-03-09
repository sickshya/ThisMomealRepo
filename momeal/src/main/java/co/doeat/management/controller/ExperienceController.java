package co.doeat.management.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 체험단(호출)
	@RequestMapping("/expr/exprFrm")
	public String experience(UsersVO vo) {
		userService.ExpSelectUser(vo);
		return "experience/experienceForm";
	}
	
	// 체험단(신청)
	@GetMapping("/expr/exprAply.do")
	public String exprAply(HttpServletRequest request,
								 Model model,
								 ExperienceVO vo,
								 UsersVO uvo
								 ) {
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("userId");
		model.addAttribute("userInfo",userService.ExpSelectUser(uvo));
		model.addAttribute("expInfo", experienceService.ExpInsert(vo));
		return "redirect:/expOrderList";
	}
	
	
	// 관리자 ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@RequestMapping("adminExperienceGroup")
	public String adminExperienceGroup(Model model) {
		model.addAttribute("exprList", experienceService.adminExperienceGroup());
		return "admin/adminExperienceGroup";
	}
}
