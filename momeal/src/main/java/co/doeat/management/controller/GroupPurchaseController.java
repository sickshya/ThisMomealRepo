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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	// 공동구매(전체조회) 리스트 폼 호출
	@RequestMapping("/pch/pchMain")
	public String pchMain(Model model, HttpSession session, UsersVO userVO) {
		model.addAttribute("pchAllList", groupPurchaseService.pchAllList()); // 전체 공동구매 리스트 조회
		model.addAttribute("pchIngList", groupPurchaseService.pchIngList()); // 진행 중인 공동구매 리스트 조회
		return "purchase/pchMain";
	}

	// 공동구매 리스트에서 단건조회를 하면 그 상품에 대한 상세페이지
	@GetMapping("/pch/pchDetail/{no}")
	public String pchDetail(Model model, @PathVariable int no, HttpSession session, GroupPurchaseSettlementVO pvo) {
		String userId = (String) session.getAttribute("userId");
		System.out.println(session + "=============================================");
		String boardCode = "CT03";
		model.addAttribute("pchDetail", groupPurchaseService.pchDetail(no));
		model.addAttribute("detailImg", imageService.imageList(boardCode, no));
		model.addAttribute("no", no);
		return "purchase/pchDetail";
	}

	// 공동구매를 신청하기 위한 서류 작성 form 호출 ( 신청form )
	@RequestMapping("/pch/pchOrderFrm/{totalGd}")
	public String pchOrderFrm(HttpServletRequest request, Model model, GroupPurchaseSettlementVO vo,
			@RequestParam int totalGd) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		List<GroupPurchaseListVO> gvo = groupPurchaseService.pchDetail(vo.getNo());
		model.addAttribute("purOne", gvo);
		System.out.println(gvo + " 무엇이들어있을까");
		model.addAttribute("userInfo", userService.userSelect(userId));
		model.addAttribute("totalPrice", totalGd * gvo.get(0).getPrice()); // totalPrice 구하기
		return "purchase/pchOrderFrm";
	}

	// 공동구매(결제)
	@PostMapping("/pch/purchase.do") // 아작스부를떄 호출
	@ResponseBody // 무조건 데이터로 넘어감.
	public String purchase(HttpSession session, HttpServletRequest request, UsersVO vo,
			@RequestBody GroupPurchaseSettlementVO pvo) {
		session = request.getSession();
		groupPurchaseService.payInsert(pvo);
		return "ok";
	}

	// 공동구매(구매내역조회)
	@RequestMapping("/pch/pchOrderList/{no}")
	public String orderList(Model model, @PathVariable int no, HttpSession session, GroupPurchaseSettlementVO pvo) {
		String userId = (String) session.getAttribute("userId");
		String boardCode = "CT03";
		model.addAttribute("pchDetail", groupPurchaseService.pchOrderList(no));
		model.addAttribute("detailImg", imageService.imageList(boardCode, no));
		System.out
				.println(groupPurchaseService.pchOrderList(no) + "뭘까" + imageService.imageList(boardCode, no) + "이미지?");
		model.addAttribute("no", no);
		return "purchase/pchOrderList";
	}

	// 마이페이지 공동구매내역 리스트
	@RequestMapping("/usr/myPurchaseList")
	public String myPurchaseList(Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();

		String userId = (String) session.getAttribute("userId");
		model.addAttribute("myPrList", groupPurchaseService.getPurchaseList(userId));
		
		model.addAttribute("pchInfo", groupPurchaseService.pchAllList());
		
		System.out.println(groupPurchaseService.pchAllList() + "여희주");

		return "myPages/myPurchaseList";
	}

	// 마이페이지 공동구매상세내역
	@RequestMapping("/usr/myPurchaseSelect/{prdtNo}")
	public String myPurchaseSelect(Model model, @PathVariable int prdtNo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		int no = prdtNo;
		model.addAttribute("myPurchase", groupPurchaseService.purchaseSelect(userId, no));
		// model.addAttribute("pchInfo",groupPurchaseService.pchAllList());
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
	public String adminGPInsert(GroupPurchaseListVO vo, ImageVO ivo, List<MultipartFile> files, MultipartFile tfile) {
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
			vo.setThumbnailImgPath("/home/mm_images/" + fileName);// 디렉토리 포함 원본파일
		}

		int no = groupPurchaseService.adminGPInsert(vo);
		String boardCode = "CT03";
		int atchNo = imageService.fileUpload(files, no, boardCode);

		if (atchNo > 0) {
			ivo.setAtchNo(atchNo);
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
	public String adminGPDelete(@PathVariable int no, RedirectAttributes redirectAttributes, GroupPurchaseListVO vo,
			ImageVO ivo) {
		String boardCode = "CT03";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		int delCnt = groupPurchaseService.adminGPDelete(no);
		if (delCnt == 0) {
			redirectAttributes.addFlashAttribute("msg", "신청자가있는 공동구매는 삭제할 수 없습니다");
		}
		return "redirect:/admin/adminGroupPurchase";
	}

	// 관리자 공동구매 update
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
	public String adminGPUpdate(GroupPurchaseListVO vo, ImageVO ivo, Model model, List<MultipartFile> files,
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
			vo.setThumbnailImgPath("/home/mm_images/" + fileName);// 디렉토리 포함 원본파일
		}
		groupPurchaseService.adminGPUpdate(vo);
		String boardCode = "CT03";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		// groupPurchaseService.adminGPUpdate(vo.getNo());
		// groupPurchaseService.adminGPUpdate(vo);
		int atchNo = imageService.fileUpload(files, vo.getNo(), boardCode);

		if (atchNo > 0) {
			ivo.setAtchNo(atchNo);
		}

		return "true";

	}

}
