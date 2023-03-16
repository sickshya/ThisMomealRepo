package co.doeat.management.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.Paging;
import co.doeat.common.service.ImageService;
import co.doeat.common.service.ImageVO;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import co.doeat.management.service.ChallengeReviewVO;
import co.doeat.management.service.ExpReviewVO;
import co.doeat.management.service.ExperienceSearchVO;
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
	
	@Autowired
	private ImageService imageService;

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
	public String experienceDetail(Model model, @PathVariable int no, HttpSession session) {
		String id = (String) session.getAttribute("userId");
		model.addAttribute("ExpOne", experienceService.ExperOne(no));
		System.out.println(id+"유저아아디" + no +"번호");
		model.addAttribute("no", no);
		return "experience/experienceDetail";
	}

	// 체험단(호출) // select 하는곳 조회먼저.
	@RequestMapping("/expr/exprFrm/{no}")
	public String experience(HttpSession session, @PathVariable int no, Model model, ExprParticipantsVO vo) {
		String userId = (String) session.getAttribute("userId");
		List<ExperienceVO> evo = experienceService.ExperOne(vo.getNo());
		model.addAttribute("expOne",evo);
		model.addAttribute("expSelect", experienceService.ExperOne(no));
		model.addAttribute("userInfo", userService.userSelect(userId));
		System.out.println(no + "체험단정보" + userId + "유저정보");
		return "experience/experienceForm";
	}

	// 체험단(신청) // insert 하는곳 값 불러와야함.
	@RequestMapping("/expr/exprAply.do")
	@ResponseBody
	public String exprAply(HttpSession session,
						   ExprParticipantsVO pvo,
						   ExperienceVO vo,
						   int no) {
		experienceService.expInsert(pvo);
		experienceService.expUpdate(vo);
		return "신청이 완료되었습니다.";
	}

	// 체험단(신청내역조회)
	@RequestMapping("/expr/expOrderList/{no}")
	public String expOrderList(Model model, HttpSession session, ExprParticipantsVO vo, @PathVariable int no) {
		String userId = (String) session.getAttribute("userId");
		List<ExperienceVO> evo = experienceService.ExperOne(vo.getNo());
		model.addAttribute("expOrder",evo);
		model.addAttribute("expAplyList", experienceService.expOrderList(userId, no));
		System.out.println(evo + "체험단배송정보"+userId+no);
		
		return "experience/expOrderList";
	}
	
	
	// 체험단(버튼수체크)
//	@RequestMapping("/ajaxExpBtn")
//	@ResponseBody
//	public String ajaxExpBtn(ExperienceVO vo, HttpSession session){
//		String userId = (String) session.getAttribute("userId");
//		Boolean b = experienceService.ajaxExpBtn(vo);
//		String str = (b!=null)? "true" : "false";
//	
//		return str;
//	}
//	
//	// 체험단 리뷰
//	
//	//전체조회
//	@PostMapping("/expReviewList/{no}")
//	@ResponseBody
//	public List<ExpReviewVO> expReviewList(@PathVariable int no) {
//		return experienceService.ExpReviewList(no);
//	}
	
	

	// 관리자 ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//페이징
	@RequestMapping("/admin/adminExperience")
	public String adminExperience(Model model, @ModelAttribute("esvo") ExperienceSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(experienceService.getCountTotal(svo));
		model.addAttribute("expList", experienceService.adminExperienceGroupList(svo));
		return "admin/adminExperience";
	}
	//체험단 등록 폼
	@RequestMapping("/admin/adminEXInsertForm")
	public String adminEXInsertForm() {
		return "admin/adminEXInsertForm";
	}
	//체험단등록
	@RequestMapping("/adminEXInsert")
	@ResponseBody
	public String adminEXInsert(ExperienceVO vo, ImageVO ivo, List<MultipartFile> files, MultipartFile tfile) {
		if (!tfile.isEmpty()) {// 첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + tfile.getOriginalFilename();
			File uploadFile = new File(saveImg, fileName);
			try {
				tfile.transferTo(uploadFile); // 파일저장하기
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setThumbnailImg(tfile.getOriginalFilename());// 원본파일명
			vo.setThumbnailImgPath("/mm_images/" + fileName);// 디렉토리 포함 원본파일
		}

		int no = experienceService.adminEXInsert(vo);
		String boardCode = "CT02";
		int atchNo = imageService.fileUpload(files, no, boardCode);

		if (atchNo > 0) {
			ivo.setAtchNo(atchNo);
		}

		return "true";
	}
	
	@RequestMapping("/admin/adminEXSelect/{no}")
	public String adminEXSelect(@PathVariable int no, Model model, ExperienceVO vo) {
		model.addAttribute("selects", experienceService.adminEXSelect(no));
		String boardCode = "CT02";
		int postNo = vo.getNo();
		model.addAttribute("imgselect", imageService.imageList(boardCode, postNo));
		
		return "admin/adminEXSelect";
	}
	
	//관리자 체험단 삭제
	@RequestMapping("/admin/adminEXDelete/{no}")
	public String adminEXDelete(@PathVariable int no, Model model, ExperienceVO vo, ImageVO ivo) {
		String boardCode = "CT02";
		int postNo = vo.getNo();
		imageService.adminEXIDelete(postNo, boardCode);
		experienceService.adminEXDelete(no);
		return "redirect:/admin/adminExperience";
	}
	
	//관리자 체험단 수정
	@RequestMapping("/admin/adminEXUpdateForm/{no}")
	public String adminEXUPdateForm(ExperienceVO vo, Model model, @PathVariable int no) {
		model.addAttribute("updates", experienceService.adminEXSelect(no));
		String boardCode = "CT02";
		int postNo = vo.getNo();
		model.addAttribute("iupdates", imageService.imageList(boardCode, postNo));
		
		return "admin/adminEXUpdateForm";
	}
	
	
	@RequestMapping("/adminEXUpdate")
	@ResponseBody
	public String adminEXUpdate(ExperienceVO vo, ImageVO ivo, Model model, List<MultipartFile> files, MultipartFile tfile) {
		if (!tfile.isEmpty()) {// 첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + tfile.getOriginalFilename();
			File uploadFile = new File(saveImg, fileName);
			try {
				tfile.transferTo(uploadFile); // 파일저장하긴
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setThumbnailImg(tfile.getOriginalFilename());// 원본파일명
			vo.setThumbnailImgPath("/mm_images/" + fileName);// 디렉토리 포함 원본파일
		}
		experienceService.adminEXUpdate(vo);
		String boardCode = "CT02";
		int postNo = vo.getNo();
		imageService.adminEXIDelete(postNo, boardCode);
		int atchNo = imageService.fileUpload(files, vo.getNo(), boardCode);
		
		if (atchNo > 0) {
			ivo.setAtchNo(atchNo);
		}

		return "true";
		
	}
	
}
