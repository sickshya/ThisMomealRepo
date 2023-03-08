package co.doeat.management.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.community.service.UsersVO;
import co.doeat.management.service.ExperienceService;

@Controller
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	// 체험단 메인
	@RequestMapping("/experienceList")
	public String experienceList(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("ExpList", experienceService.getExperList()); // 전체
		model.addAttribute("ExpingList", experienceService.getExperiensing()); // 진행
		return "experience/experienceList";
	}

	@Autowired ExperienceService experienceService;
	@Autowired ServletContext servletContext;
	@Autowired private ImageService imageService;
	@Value("${momeal.saveImg}")
	private String saveImg;
	
	// 관리자 ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	@RequestMapping("adminExperienceGroup")
	public String adminExperienceGroup(Model model) {
		model.addAttribute("exprList", experienceService.adminExperienceGroup());
		return "admin/adminExperienceGroup";
	}
}
