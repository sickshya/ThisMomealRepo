package co.doeat.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.community.service.CommentService;
import co.doeat.community.service.CommentVO;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comment/{no}")
	@ResponseBody
	public CommentVO commentList(CommentVO vo, @PathVariable int no) {
		vo.setPostNo(no);
		vo.setBoardCode("CT04");
		
		System.out.println(commentService.commentsList(vo));
		
		return commentService.commentsList(vo);
		
	}

}
