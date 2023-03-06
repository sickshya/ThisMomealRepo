package co.doeat.community.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.community.service.CommentService;
import co.doeat.community.service.CommentVO;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	// 댓글 출력
	@PostMapping("/comment/{postNo}")
	@ResponseBody
	public List<CommentVO> commentList(CommentVO vo, @PathVariable int postNo) {
		
		System.out.println("번호 가져왔어?? =========== " + postNo);
		vo.setPostNo(postNo);
		vo.setBoardCode("CT04");
		
		System.out.println("========== 댓글 ▶ " + commentService.commentsList(vo));
		
		return commentService.commentsList(vo);
	}
	
	// 댓글 등록
	@PostMapping("/insertComment/{postNo}")
	@ResponseBody
	public String commentInsert(CommentVO vo, HttpSession session, @PathVariable int postNo, @RequestParam String subject) {
		
		System.out.println("글 번호 ========== " + postNo);
		vo.setPostNo(postNo);
		vo.setSubject(subject);
		vo.setUserId((String) session.getAttribute("userId"));
		vo.setBoardCode("CT04");
		
		System.out.println("========== 댓글등록용 VO ▶ " + vo);
		
		commentService.commentInsert(vo);
		
		return "success";
	}

}
