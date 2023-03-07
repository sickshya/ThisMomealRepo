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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.Paging;
import co.doeat.common.service.ImageService;
import co.doeat.common.service.ImageVO;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import co.doeat.management.service.GroupPurchaseListVO;
import co.doeat.management.service.GroupPurchaseSearchVO;
import co.doeat.management.service.GroupPurchaseService;
import co.doeat.management.service.GroupPurchaseSettlementVO;

@Controller
public class GroupPurchaseController {

	@Autowired
	ServletContext servletContext;

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private UserService userService;

	@Value("${momeal.saveImg}")
	private String saveImg;

	// 공동구매
	@RequestMapping("/purchaseList")
	public String purchaseList(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("PurList", groupPurchaseService.getPurList()); // 전체
		model.addAttribute("PurchsingList", groupPurchaseService.getPurchasingList()); // 진행
		return "groupPurchase/purchaseList";
	}

	// 공동구매-상세(단건조회)
	@GetMapping("/purchaseDetail/{no}")
	public String purchaseList(Model model, @PathVariable int no, GroupPurchaseSettlementVO pvo) {
		model.addAttribute("PurOne", groupPurchaseService.getPurOne(no));
		return "groupPurchase/purchaseDetail";
	}

	// 공동구매-정보입력form
	@RequestMapping("/purchaseForm/{no}")
	public String purchaseForm(@PathVariable int no, HttpSession session, HttpServletRequest request,
			GroupPurchaseListVO gvo, Model model, GroupPurchaseSettlementVO vo) {
//		session = request.getSession();
//		session.setAttribute("userId", "user1");
//		String orderId = (String) session.getAttribute("userId")
		model.addAttribute("purOne", groupPurchaseService.getPurOne(no));
		model.addAttribute("userInfo", userService.userSelect("user1"));
		// model.addAttribute("kakao", groupPurchaseService.kakaoPay(vo));

		return "groupPurchase/purchaseForm";
	}

	// 공동구매-결제
	@PostMapping("/purchase") // 아작스부를떄 호출
	@ResponseBody
	public String purchase(HttpSession session, HttpServletRequest request, UsersVO vo, GroupPurchaseSettlementVO pvo) {
		session = request.getSession();
		groupPurchaseService.payInsert(pvo);
		return "redirect:/orderList";
	}

	// 공동구매-구매완료리스트
	@GetMapping("/orderList")
	public String orderList(HttpSession session, HttpServletRequest request, Map<String, Object> map) {
		session = request.getSession();
		// 세션에 담겨있는 아이디 값을 vo의 userId에 담기. (String) 처리 해줘야함
		// vo.setUserId((String) session.getAttribute("userId"));
		groupPurchaseService.attendPurchase(map);
		return "groupPurchase/orderList";
	}


	// ++++++++++++++++++++++++++++++++++++++++++++++마이페이지
	// 마이페이지 공동구매내역 리스트
	@RequestMapping("/myPurchaseList")
	public String myPurchaseList(Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");

		String userId = (String) session.getAttribute("userId");
		model.addAttribute("myPrList", groupPurchaseService.getPurchaseList(userId));
		return "myPages/myPurchaseList";
	}

	// 마이페이지 공동구매상세내역
	@RequestMapping("/myPurchaseSelect/{prdtNo}")
	public String myPurchaseSelect(Model model, @PathVariable int prdtNo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		int no = prdtNo;
		model.addAttribute("myPurchase", groupPurchaseService.purchaseSelect(userId, no));
		System.out.println("결과 ==========" + groupPurchaseService.purchaseSelect(userId, no));
		return "myPages/myPurchaseSelect";
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	// 페이징
	@RequestMapping("/admin/adminGroupPurchase")
	public String adminGroupPurchase(Model model, @ModelAttribute("esvo") GroupPurchaseSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(groupPurchaseService.getCountTotal(svo));
		model.addAttribute("getAdminGroupPurchaseList", groupPurchaseService.getAdminGroupPurchaseList(svo));
		return "admin/adminGroupPurchase";
	}

	// 공동구매등록
	@RequestMapping("/admin/adminGPInsertFrom")
	public String adminGPInsertFrom() {
		return "admin/adminGPInsertFrom";
	}

	// 공동구매등록
	@RequestMapping("/adminGPInsert")
	@ResponseBody
	public String adminGPInsert(GroupPurchaseListVO vo, ImageVO evo, List<MultipartFile> files, MultipartFile tfile) {
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

		int no = groupPurchaseService.adminGPInsert(vo);
		String boardCode = "CT03";
		int atchNo = imageService.fileUpload(files, no, boardCode);

		if (atchNo > 0) {
			evo.setAtchNo(atchNo);
		}

		return "true";
	}

	// 관리자 공동구매 select
	@RequestMapping("/admin/adminGPSelect/{no}")
	public String adminGPSelect(@PathVariable int no, Model model, GroupPurchaseListVO vo) {
		model.addAttribute("selects", groupPurchaseService.adminGPSelect(no));
		String boardCode = "CT03";
		int postNo = vo.getNo();
		model.addAttribute("imgselect", imageService.imageList(boardCode, postNo));

		return "admin/adminGPSelect";
	}

	// 관리자 공동구매 delete
	@RequestMapping("/admin/adminGPDelete/{no}")
	public String adminGPDelete(@PathVariable int no, Model model, GroupPurchaseListVO vo, ImageVO evo) {
		String boardCode = "CT03";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		groupPurchaseService.adminGPDelete(no);
		return "redirect:/admin/adminGroupPurchase";
	}
  
	//관리자 공동구매 update
	@RequestMapping("/admin/adminGPUpdateForm/{no}")
	public String adminGPUpdateForm(GroupPurchaseListVO vo, Model model, @PathVariable int no) {
		model.addAttribute("updates", groupPurchaseService.adminGPSelect(no));
		String boardCode = "CT03";
		int postNo = vo.getNo();
		model.addAttribute("iupdates", imageService.imageList(boardCode, postNo));

		return "admin/adminGPUpdateForm";
	}

	@RequestMapping("/adminGPUpdate")
	@ResponseBody
	public String adminGPUpdate(GroupPurchaseListVO vo, ImageVO evo, Model model, List<MultipartFile> files,
			MultipartFile tfile) {
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
		groupPurchaseService.adminGPUpdate(vo);
		String boardCode = "CT03";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		// groupPurchaseService.adminGPUpdate(vo.getNo());
		// groupPurchaseService.adminGPUpdate(vo);
		int atchNo = imageService.fileUpload(files, vo.getNo(), boardCode);

		if (atchNo > 0) {
			evo.setAtchNo(atchNo);
		}

		return "true";

	}

}
