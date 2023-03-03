package co.doeat.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.Paging;
import co.doeat.community.service.UsersVO;
import co.doeat.management.service.GroupPurchaseSearchVO;
import co.doeat.management.service.GroupPurchaseService;
import co.doeat.management.service.GroupPurchaseSettlementVO;

@Controller
public class GroupPurchaseController {

	@Autowired
	private GroupPurchaseService groupPurchaseService;

	// 공동구매
	@RequestMapping("/purchaseList")
	public String purchaseList(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("PurList", groupPurchaseService.getPurList()); // 전체
		model.addAttribute("PurchsingList", groupPurchaseService.getPurchasingList()); // 진행
		System.out.println("결과===========" + groupPurchaseService.getPurList());
		return "groupPurchase/purchaseList";
	}

	// 공동구매단건조회
	@GetMapping("/purchaseList/{no}")
	public String purchaseList(Model model, @PathVariable int no) {
		model.addAttribute("PurOne", groupPurchaseService.getPurOne(no));
		System.out.println("결과===========" + groupPurchaseService.getPurOne(no));
		return "groupPurchase/groupDetail";
	}

	// 공동구매(상세)
	@GetMapping("/groupDetail.do")
	public String groupDetail(Model model) {
//		model.addAttribute("getDetail",groupPurchaseService.getDetailList());
		return "groupPurchase/groupDetail";
	}

	// 공동구매(구매하기 신청 form)
	@GetMapping("/purchaseForm")
	public String purchaseForm() {
//		HttpSession session = request.getSession();
//		String user1 = (String) session.getAttribute("userId");
		return "groupPurchase/purchaseForm";
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

//	//groupDetail.do 요청시 주문자 수 카운트
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

		model.addAttribute("myPrList", groupPurchaseService.getPurchaseList());
		return "myPages/myPurchaseList";
	}

	// 마이페이지 공동구매상세내역
	@RequestMapping("/myPurchaseSelect/{prdtNo}")
	public String myPurchaseSelect(Model model, @PathVariable int prdtNo, HttpSession session,
			HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");

		model.addAttribute("myPurchase", groupPurchaseService.purchaseSelect(prdtNo));
		System.out.println("결과 ==========" + groupPurchaseService.purchaseSelect(prdtNo));
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
	@RequestMapping("/adminGPInsert.do")
	public String adminGPInsert() {
		return "admin/adminGPInsert";
	}

}
