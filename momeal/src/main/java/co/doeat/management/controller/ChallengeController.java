package co.doeat.management.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.Paging;
import co.doeat.common.service.ImageService;
import co.doeat.common.service.ImageVO;
import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeReviewVO;
import co.doeat.management.service.ChallengeSearchVO;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ChallengeVO;
import co.doeat.management.service.ChallengeValidationVO;

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
	public String challengeMain(Model model, HttpSession session, ChallengeVO vo) {
		// 임시로 세션에 ID 값 담기
		String id = (String) session.getAttribute("userId");
		vo.setUserId(id);
		vo.setNo(10);

		// 전체조회
//		model.addAttribute("challList", challengeService.getChallList(vo));
		
		// 게시물 max 번호
		model.addAttribute("maxNo", challengeService.getMaxChallNo());
		
		// 인기순(좋아요 많은 순) 조회
		model.addAttribute("challRec", challengeService.likeRankChallList(id));

		return "challenge/challenge";
	}
	
	// 전체조회 + 페이징
	@RequestMapping("/challengeList")
	@ResponseBody
	public List<Map<String, Object>> challengeList(ChallengeVO vo, HttpSession session) {
		System.out.println("도착??????");
		String userId = (String) session.getAttribute("userId");
		vo.setUserId(userId);
		return challengeService.getChallList(vo);
	}
	
	// 검색
//	@PostMapping("/getSearchList")
//	@ResponseBody
//	private List<ChallengeSearchVO> getSearchList(ChallengeSearchVO vo, @RequestParam("type") String type, @RequestParam("keyword") String keyword) {
//		return challengeService.getSearchList(vo);
//	}

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
		return "redirect:/myChallenge";
	}

	// ▶ 나의 챌린지 - 진행중 ◀
	// 진행중, 종료 - 전체조회(챌린지 참여신청 후 이동)
	@RequestMapping("/myChallenge")
	public String myChallengeList(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		// 진행중
		model.addAttribute("myChall", challengeService.getMyChallList(userId));
		// 종료
		model.addAttribute("endChall", challengeService.getMyEndChallList(userId));
		return "challenge/myChallenge";
	}

	// 진행중 - 단건조회
	@GetMapping("/myChallenge/{chalNo}")
	public String myChallengOne(Model model, @PathVariable int chalNo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		// 챌린지 정보 select
		model.addAttribute("chall", challengeService.getMyChall(userId, chalNo));

		// 챌린지 나의 인증 이미지 select
		model.addAttribute("valImg", challengeService.getMyChallImg(userId, chalNo));
		
		// 챌린지 참여자 인증 이미지 select
		model.addAttribute("userValImg", challengeService.getChallImgList(userId, chalNo));

		return "challenge/myChallengeDetail";
	}

	// 진행중 - 인증 사진 등록
	@RequestMapping("/insertMyChallImg")
	@ResponseBody
	public ChallengeValidationVO insertMyChallImg(ChallengeValidationVO vo, MultipartFile file, HttpSession session) {
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

		return challengeService.insertMyChallImg(vo);
	}
	
	// 진행중 - 인증 사진 단건 조회
	@RequestMapping("/myChallImg/{no}")
	@ResponseBody
	public ChallengeValidationVO getMyChallImgOne(@PathVariable int no) {
		System.out.println("이미지 상세 ▶ ===== " + challengeService.getMyChallImgOne(no));
		return challengeService.getMyChallImgOne(no);
	}

	// ▶ 나의 챌린지 - 종료 ◀
	// 종료 - 후기 전체조회
	@PostMapping("/challengeReview/{no}")
	@ResponseBody
	public List<ChallengeReviewVO> getReviewList(@PathVariable int no) {
		return challengeService.getReviewList(no);
	}
	
	// 종료 - 후기 단건조회
	@PostMapping("/challOneReview/{no}")
	@ResponseBody
	public ChallengeReviewVO getReviewOne(@PathVariable int no, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return challengeService.getReviewOne(userId, no);
	}
	
	// 종료 - 후기 등록
	@PostMapping("/insertReview")
	@ResponseBody
	public int insertReview(ChallengeReviewVO vo) {
		return challengeService.insertReview(vo);
	}
	
	// 종료 - 후기 수정
	@PostMapping("/updateReview")
	@ResponseBody
	public int updateReview(ChallengeReviewVO vo) {
		return challengeService.updateReview(vo);
	}
	
	// 종료 - 후기 삭제
	@PostMapping("/deleteReview")
	@ResponseBody
	public int deleteReview(ChallengeReviewVO vo) {
		return challengeService.deleteReview(vo);
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
	@RequestMapping("/admin/adminCHInsertForm")
	public String adminCHInsertForm() {
		return "admin/adminCHInsertForm";
	}

	// 관리자 챌린지등록
	@RequestMapping("/adminCHInsert")
	@ResponseBody
	public String adminCHInsert(ChallengeVO vo, ImageVO ivo, List<MultipartFile> files, MultipartFile tfile) {
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
			ivo.setAtchNo(atchNo);
		}
		return "true";

	}

	// 관리자 챌린지 단건조회
	@RequestMapping("/admin/adminCHSelect/{no}")
	public String adminCHInsert(@PathVariable int no, Model model, ChallengeVO vo) {
		model.addAttribute("selects", challengeService.adminCHSelect(no));
		String boardCode = "CT01";
		int postNo = vo.getNo();
		model.addAttribute("imgselect", imageService.imageList(boardCode, postNo));
		return "admin/adminCHSelect";
	}

	// 관리자 챌린지 삭제
	@RequestMapping("/admin/adminCHDelete/{no}")
	public String adminCHDelete(@PathVariable int no, Model model, ChallengeVO vo, ImageVO evo) {
		String boardCode = "CT01";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		challengeService.adminCHDelete(no);
		return "redirect:/admin/adminChallenge";
	}
	
	//관리자 챌린지 update폼
	@RequestMapping("/admin/adminCHUpdateForm/{no}")
	public String adminCHUpdateForm(ChallengeVO vo, Model model, @PathVariable int no) {
		model.addAttribute("updates", challengeService.adminCHSelect(no));
		String boardCode="CT01";
		int postNo = vo.getNo();
		model.addAttribute("iupdates", imageService.imageList(boardCode, postNo));
		
		return "admin/adminCHUpdateForm";
	}
	
	//관리자 챌린지 update
	@RequestMapping("/adminCHUpdate")
	@ResponseBody
	public String adminCHUpdate(ChallengeVO vo, ImageVO ivo, Model model, List<MultipartFile> files, MultipartFile tfile) {
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
	challengeService.adminCHUpdate(vo);
	String boardCode = "CT01";
	int postNo = vo.getNo();
	imageService.adminCHIDelete(postNo, boardCode);
	int atchNo = imageService.fileUpload(files, vo.getNo(), boardCode);
	if (atchNo > 0) {
		ivo.setAtchNo(atchNo);
	}
	return "true";
	}	
	
	//관리자 챌린지 재등록폼
		@RequestMapping("/admin/adminCHReInsertForm/{no}")
		public String adminCHReInsert(ChallengeVO vo, Model model, @PathVariable int no) {
			model.addAttribute("reupdates", challengeService.adminCHSelect(no));
			String boardCode="CT01";
			int postNo = vo.getNo();
			model.addAttribute("iupdates", imageService.imageList(boardCode, postNo));
			
			return "admin/adminCHReInsertForm";
		}

	// 관리자 챌린지재등록
		@RequestMapping("/adminCHReInsert")
		@ResponseBody
		public String adminCHReInsert(ChallengeVO vo, ImageVO ivo, List<MultipartFile> files, MultipartFile tfile) {
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
			int no = challengeService.adminCHReInsert(vo);
			String boardCode = "CT01";
			int atchNo = imageService.fileUpload(files, no, boardCode);

			if (atchNo > 0) {
				ivo.setAtchNo(atchNo);
			}
			return "true";

		}

	
	
}
