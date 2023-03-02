package co.doeat.management.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.Paging;
import co.doeat.community.service.UsersVO;
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


	// 공동구매
	@RequestMapping("/groupBuying")
	public String groupBuying(Model model, HttpServletRequest request, UsersVO userVO) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user1");
		model.addAttribute("PurList", groupPurchaseService.getPurList()); // 전체
		model.addAttribute("PurchsingList", groupPurchaseService.getPurchasingList()); // 진행
		System.out.println("결과===========" + groupPurchaseService.getPurList());
		return "groupPurchase/groupBuying";
	}

	// 공동구매단건조회
	@GetMapping("/groupBuying/{no}")
	public String groupBuying(Model model, @PathVariable int no) {
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

	// 공동구매(구매하기 창 list)
	@RequestMapping("/PurchaseForm")
	public String PurchaseForm(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String user1 = (String) session.getAttribute("userId");
		return "groupPurchase/PurchaseForm";
	}
	
//	// 공동구매(구매하기 신청 form)
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

	// 공동구매(배송지목록)
	@GetMapping("/groupPurchaseList.do")
	public String groupPurchaseList() {
		return "groupPurchase/groupPurchaseList";
	}


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
	public String myPurchaseSelect(Model model, @PathVariable int prdtNo, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");
		
		model.addAttribute("myPurchase", groupPurchaseService.purchaseSelect(prdtNo));
		System.out.println("결과 ==========" + groupPurchaseService.purchaseSelect(prdtNo));
		return "myPages/myPurchaseSelect";  
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	//페이징
	@RequestMapping("/adminGroupPurchase")
	public String adminGroupPurchase(Model model, @ModelAttribute("esvo") GroupPurchaseSearchVO svo,Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(groupPurchaseService.getCountTotal(svo));
		model.addAttribute("getAdminGroupPurchaseList", groupPurchaseService.getAdminGroupPurchaseList(svo));
		return "admin/adminGroupPurchase";
	}
	
	//공동구매등록
	@RequestMapping("/adminGPInsert.do")
	public String adminGPInsert() {
		return "admin/adminGPInsert";
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
	public String adminGPInsert(GroupPurchaseListVO vo, List<MultipartFile> files, MultipartFile file) {
		if(!file.isEmpty()) {//첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + file.getOriginalFilename();
			File uploadFile = new File(saveImg,fileName);
		try {
			file.transferTo(uploadFile); //파일저장하긴
		}catch(Exception e) {
			e.printStackTrace();
		}
		vo.setThumbnailImg(file.getOriginalFilename());//원본파일명
		vo.setThumbnailImgPath(saveImg +fileName);//디렉토리 포함 원본파일
		}
	
		groupPurchaseService.adminGPInsert(vo);//db 저장 루틴

		int atchNo = imageService.fileUpload(files);
		
		if(atchNo > 0) {
			vo.setAtchNo(atchNo);
		}
		
		return "true";
	
	}

}
