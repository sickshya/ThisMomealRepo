package co.doeat.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.common.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	
	//전체조회
	@RequestMapping("/noticeList.do")
	public String noticeList(Model model) {
		model.addAttribute("notices", boardService.noticeList());
		return "notice/noticeList";
	}
	
	
	
	
}
