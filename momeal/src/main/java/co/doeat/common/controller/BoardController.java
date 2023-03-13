package co.doeat.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.Paging;
import co.doeat.common.service.BoardService;
import co.doeat.common.service.BoardVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

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

}
