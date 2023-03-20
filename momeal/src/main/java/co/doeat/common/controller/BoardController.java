package co.doeat.common.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.service.BoardService;
import co.doeat.common.service.BoardVO;
import co.doeat.community.service.UserService;
import co.doeat.management.service.ChallengeVO;


@Controller
public class BoardController<UserVO> {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Autowired
	BoardService boardService;

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	UserService userService;

	// 자주묻는질문 FAQ

	// Faq user가 보는 전체리스트
	@RequestMapping("/faq")
	public String faq(Model model) {
		model.addAttribute("faqAllList", boardService.faqList());
		return "board/faq";
	}

	// Faq 전체리스트 보기
	@RequestMapping("/admin/adminFaq")
	public String adminFaq(Model model) {
		model.addAttribute("adminFaqList", boardService.faqList());
		return "admin/adminFaq";
	}

	// Faq 추가
	@RequestMapping("/admin/adminFaqInsertForm")
	public String farInsertForm() {
		return "admin/adminFaqInsertForm";
	}

	// Faq 추가
	@RequestMapping("/admin/adminFaqInsert")
	public String adminFaqInsert(BoardVO vo, Model model) {
		vo.setBoardCode("BD02");

		boardService.faqInsert(vo);

		return "redirect:/admin/adminFaq";
	}

	// Faq 수정
	@RequestMapping("/admin/adminFaqUpdateForm/{no}")
	public String adminFaqUpdateForm(@PathVariable int no, Model model, BoardVO vo) {

		vo.setNo(no);
		vo.setBoardCode("BD02");

		model.addAttribute("faqUpdate", boardService.faqSelect(vo));

		return "admin/adminFaqUpdateForm";
	}

	// Faq 수정
	@RequestMapping("/admin/adminFaqUpdate")
	public String adminFaqUpdate(BoardVO vo) {

		vo.setBoardCode("BD02");
		boardService.faqUpdate(vo);

		return "redirect:/admin/adminFaq";
	}

	// Faq 삭제
	@RequestMapping("/admin/adminFaqDelete/{no}")
	public String adminFaqDelete(@PathVariable int no, Model model, BoardVO vo) {
		String boardCode = "BD02";
		no = vo.getNo();

		boardService.faqDelete(vo);
		return "redirect:/admin/adminFaq";
	}

	// 공지사항 NOTICE ===================================================================

	// NOTICE(USER) 전체리스트
	@RequestMapping("/notice")
	public String noticeList(Model model) {
		model.addAttribute("noticeList", boardService.noticeList());
		return "board/notice";
	}
	
	// NOTICE(USER) 글 상세보기
	@GetMapping("/noticeSel/{no}")
	public String noticeSel(@PathVariable int no, BoardVO vo, Model model) {
		model.addAttribute("notice", boardService.noticeSelect(no));
		return "board/noticeSelect";
	}
	
	
	// NOTICE(ADMIN) 글 상세보기
	@GetMapping("/admin/adminNoticeSelect/{no}") 
	public String noticeSelect(@PathVariable int no, BoardVO vo, Model model) {
		//boardService.noticeHitUpdate(vo.getUserId());
		model.addAttribute("notice", boardService.noticeSelect(no));
		String boardCode = "BD01";
		return "admin/adminNoticeSelect";
	}
	
	// NOTICE(ADMIN) 전체리스트
	@RequestMapping("/admin/adminNotice")
	public String adminNotice(Model model) {
		model.addAttribute("noticeList", boardService.noticeList());
		return "admin/adminNotice";
	}
	
	// NOTICE(ADMIN) 등록폼 호출
	@RequestMapping("/admin/adminNoticeInsertForm")
	public String noticeInsertForm() {
		return "admin/adminNoticeInsertForm";
	}

	// NOTICE(ADMIN) 글 등록
	@PostMapping("/admin/adminNoticeInsert")
	public String noticeInsert(BoardVO vo, MultipartFile file) {
		
		vo.setBoardCode("BD01");
		// DB 저장
		boardService.noticeInsert(vo);

		return "redirect:/admin/adminNotice";
	}
	
	
	//  NOTICE(ADMIN) 글 수정폼 호출
	@RequestMapping("/admin/adminNoticeUpdateForm/{no}")
	public String adminNoticeUpdateForm(@PathVariable int no, Model model, BoardVO vo) {

		vo.setNo(no);
		vo.setBoardCode("BD01");

		model.addAttribute("noticeUpdate", boardService.noticeSelect(no));

		return "admin/adminNoticeUpdateForm";
	}

	// NOTICE(ADMIN) 글 수정
	@RequestMapping("/admin/adminNoticeUpdate")
	public String adminNoticeUpdate(BoardVO vo) {

		vo.setBoardCode("BD01");
		boardService.noticeUpdate(vo);

		return "redirect:/admin/adminNotice";
	}
	
	
	// NOTICE(ADMIN) 글 삭제 처리
	@RequestMapping("/admin/adminNoticeDelete/{no}")
	public String noticeDelete(@PathVariable int no, Model model, BoardVO vo) {
		
		// DB에서 삭제
		String boardCode = "BD01";
		no = vo.getNo();
		boardService.noticeDelete(vo);
		
		return "redirect:/admin/adminNotice";
	}
	

}
