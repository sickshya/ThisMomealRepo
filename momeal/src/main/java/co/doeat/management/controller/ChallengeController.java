package co.doeat.management.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
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
import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeSearchVO;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ChallengeVO;
import co.doeat.management.service.ChallengeValidationVO;
import co.doeat.management.service.GroupPurchaseListVO;

@Controller
public class ChallengeController {
	@Autowired
	private ChallengeService challengeService;
	@Autowired
	ServletContext servletContext;
	@Autowired
	private ImageService imageService;
	@Value("${momeal.saveImg}")
	private String saveImg;

	// ▶ 챌린지 ◀
	// 전체조회
	// 세션에 아이디 값도 담아두기(임시)
	@RequestMapping("/challenge")
	public String challengeMain(Model model, HttpSession session) {
		// 임시로 세션에 ID 값 담기
		String id = (String) session.getAttribute("userId");
		
		// 전체조회
		model.addAttribute("challList", challengeService.getChallList(id));
		// 인기순(좋아요 많은 순) 조회
		model.addAttribute("challRec", challengeService.likeRankChallList(id));

		return "challenge/challenge";
	}

	// 단건조회
	@GetMapping("/challenge/{no}")
	public String challenge(Model model, Map<String, Object> map, HttpSession session, @PathVariable int no) {
		map.put("userId", session.getAttribute("userId"));
		map.put("no", no);
		model.addAttribute("chall", challengeService.getChallenge(map));
		return "challenge/challengeDetail";
	}

	// 챌린지 참여하기
	@PostMapping("/attendChallenge")
	public String attendChallenge(ChallengeParticipationVO vo, HttpSession session) {
		// 세션에 담겨있는 아이디 값을 vo의 userId에 담기. (String) 처리 해줘야함
		vo.setUserId((String) session.getAttribute("userId"));
		challengeService.attendChall(vo);
		return "redirect:/myChallengeList";
	}

	// ▶ 나의 챌린지 ◀
	// 진행중 - 전체조회(챌린지 참여신청 후 이동)
	@RequestMapping("/myChallenge")
	public String myChallengeList(Model model, HttpSession session) {
		// 세션의 로그인 정보에 담겨있는 유저 본인의 아이디를 {key:"userId" value:세션 아이디} 이렇게 담아서 보내야함!!!
		// 그럼 그 값을 읽어서 해당 유저의 정보만 보여줌
		String userId = (String) session.getAttribute("userId");
		model.addAttribute("myChall", challengeService.getMyChallList(userId));
		return "challenge/myChallenge";
	}

	// 진행중 - 단건조회
	@GetMapping("/myChallenge/{no}")
	public String myChallengOne(Model model, @PathVariable int no, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		
		// 챌린지 정보 select
		model.addAttribute("chall", challengeService.getMyChall(userId, no));
		
		// 챌린지 인증 이미지 select
		model.addAttribute("valImg", challengeService.getMyChallImg(userId, no));
		
		System.out.println("이미지 ▶ ====== " + challengeService.getMyChallImg(userId, no));
		
		return "challenge/myChallengeDetail";
	}
	
	// 진행중 - 인증 사진 등록
	@RequestMapping("/insertMyChallImg")
	@ResponseBody
	public String insertMyChallImg(ChallengeValidationVO vo, MultipartFile file, HttpSession session) {
		System.out.println("왔어???????? =================");
		if (!file.isEmpty()) { // 첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + file.getOriginalFilename();
			File uploadFile = new File(saveImg, fileName);
			try {
				file.transferTo(uploadFile); // 파일 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setChalImg(file.getOriginalFilename()); // 원본 파일명
			vo.setFileDir("/mm_images/" + fileName); // 디렉토리 포함 원본 파일
		}
//		vo.setUserId((String) session.getAttribute("userId"));
		challengeService.insertMyChallImg(vo);
		
		return "success";
	}

	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	// 챌린지 관리자
	// 페이징
	@RequestMapping("/admin/adminChallenge")
	public String adminChallenge(Model model, @ModelAttribute("esvo") ChallengeSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(challengeService.getCountTotal(svo));
		model.addAttribute("getAdminCHList", challengeService.adminChalList(svo));
		return "admin/adminChallenge";
	}

	// 관리자 챌린지 등록 폼
	@RequestMapping("/adminCHInsertForm")
	public String adminCHInsertForm() {
		return "admin/adminCHInsertForm";
	}

	// 관리자 챌린지등록
	@RequestMapping("/adminCHInsert")
	@ResponseBody
	public String adminCHInsert(ChallengeVO vo, ImageVO evo, List<MultipartFile> files, MultipartFile tfile) {
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
		int no = challengeService.adminCHInsert(vo);
		String boardCode = "CT01";
		int atchNo = imageService.fileUpload(files, no, boardCode);

		if (atchNo > 0) {
			evo.setAtchNo(atchNo);
		}
		return "true";

	}

	// 관리자 챌린지 단건조회
	@RequestMapping("/adminCHSelect/{no}")
	public String adminCHInsert(@PathVariable int no, Model model) {
		model.addAttribute("selects", challengeService.adminCHSelect(no));
		return "admin/adminCHSelect";
	}

	// 관리자 챌린지 삭제
	@RequestMapping("/adminCHDelete/{no}")
	public String adminCHDelete(@PathVariable int no, Model model, ChallengeVO vo, ImageVO evo) {
		String boardCode = "CT01";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		challengeService.adminCHDelete(no);
		return "redirect:/adminCHDelete";
	}
}
