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

	@Value("${momeal.saveImg}")
	private String saveImg;

	// 공동구매 전체리스트
	@RequestMapping("/purchaseList")
	public String purchaseList(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("PurList", groupPurchaseService.getPurList()); // 전체
		model.addAttribute("PurchsingList", groupPurchaseService.getPurchasingList()); // 진행
		return "groupPurchase/purchaseList";
	}

	// 공동구매 단건조회
	@GetMapping("/purchaseList/{no}")
	public String purchaseList(Model model, @PathVariable int no) {
		model.addAttribute("PurOne", groupPurchaseService.getPurOne(no));
		return "groupPurchase/groupDetail";
	}

	// 공동구매(상세)
	@GetMapping("/groupDetail.do")
	public String groupDetail(Model model) {
//		model.addAttribute("getDetail",groupPurchaseService.getDetailList());
		return "groupPurchase/groupDetail";
	}

	// 공동구매 ( 구매하기 신청 form 호출 )
	@GetMapping("/purchaseForm")
	public String purchaseForm() {
//		HttpSession session = request.getSession();
//		String user1 = (String) session.getAttribute("userId");
		return "groupPurchase/PurchaseForm";
	}

//	// 공동구매 ( 구매하기 신청 form 호출)
//	@RequestMapping("/PurchaseForm.do")
//	@ResponseBody
//	public String PurchasePayment(GroupPurchaseSettlementVO vo) {
//		groupPurchaseService.payInsert(vo); //db저장루틴
//			return "groupPurchase/PurchasePayment";
//		}

	// 공동구매 배송지
	@GetMapping("/groupPurchase.do")
	public String groupPurchase() {
		return "groupPurchase/groupPurchase";
	}

	// 공동구매
	@PostMapping("/purchaseForm.do")
	public String purchasePayment(GroupPurchaseSettlementVO vo) {
		groupPurchaseService.payInsert(vo);
		return "redirect:/orderList";
	}

//	// 공동구매(배송지목록)
//	@GetMapping("/PurchaseList.do")
//	public String groupPurchaseList() {
//		return "groupPurchase/groupPurchaseList";
//	}

//	//공동구매 찜하기 버튼
//	@Transactional(rollbackFor = Exception.class)
//	@PostMapping("/like/{post_no}")
//	public ModelAndView 
//	

//	// groupDetail.do 요청시 주문자 수 카운트
//	@GetMapping("/groupDetail.do")
//	public String groupDetail(String sttlSt, Model model) {
//		System.out.println("주문자수 실행");
//		GroupPurchaseMapper.count(sttlSt);
//		GroupPurchaseListVO vo = groupPurchaseMapper.groupDetail(sttlSt);
//		
//		//vo를 모델에 담아 객체 바인딩 
//		model.addAttribute("vo",vo);
//		
//		return "groupDetail.do";
//		
//	}

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
	@RequestMapping("/adminGroupPurchase")
	public String adminGroupPurchase(Model model, @ModelAttribute("esvo") GroupPurchaseSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(groupPurchaseService.getCountTotal(svo));
		model.addAttribute("getAdminGroupPurchaseList", groupPurchaseService.getAdminGroupPurchaseList(svo));
		return "admin/adminGroupPurchase";
	}

	// 공동구매등록
	@RequestMapping("/adminGPInsertFrom")
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
			vo.setThumbnailImgPath(saveImg + fileName);// 디렉토리 포함 원본파일
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
	@RequestMapping("/adminGPSelect/{no}")
	public String adminGPSelect(@PathVariable int no, Model model) {
		model.addAttribute("selects", groupPurchaseService.adminGPSelect(no));
		return "admin/adminGPSelect";
	}

	// 관리자 공동구매 delete
	@RequestMapping("/adminGPDelete/{no}")
	public String adminGPDelete(@PathVariable int no, Model model, GroupPurchaseListVO vo, ImageVO evo) {
		String boardCode = "CT03";
		int postNo = vo.getNo();
		imageService.adminGPIDelete(postNo, boardCode);
		groupPurchaseService.adminGPDelete(no);
//		if(n != 0) {
//			model.addAttribute("message", "정상적으로 삭제 되었습니다.");
//		}else {
//			model.addAttribute("message", "삭제 실패");
//		}
		return "redirect:/adminGroupPurchase";
	}

}
