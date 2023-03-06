package co.doeat.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.community.service.CommentService;
import co.doeat.community.service.CommentVO;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/comment/{postNo}")
	@ResponseBody
	public List<CommentVO> commentList(Model model, CommentVO vo, @PathVariable int postNo) {
		
		System.out.println("번호 가져왔어?? =========== " + postNo);
		vo.setPostNo(postNo);
		vo.setBoardCode("CT04");
		
		System.out.println("========== 댓글 ▶ " + commentService.commentsList(vo));
		
		return commentService.commentsList(vo);
		
	}

}
