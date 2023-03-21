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
	@PostMapping("/contentsDetail/comment/{postNo}")
	@ResponseBody
	public List<CommentVO> commentList(CommentVO vo, @PathVariable int postNo) {
		
		vo.setPostNo(postNo);
		vo.setBoardCode("CT04");
		
		return commentService.commentsList(vo);
	}
	
	// 그룹 댓글 출력
	@PostMapping("/GroupComment")
	@ResponseBody
	public List<CommentVO> groupCommentsList(CommentVO vo) {
		vo.setBoardCode("CT05");
		return commentService.groupCommentsList(vo);
	}
	
	// 댓글 등록
	@PostMapping("/insertComment")
	@ResponseBody 
	public String commentInsert(CommentVO vo, HttpSession session) {
		// String 대신 responseEntity
		vo.setUserId((String) session.getAttribute("userId"));
		
		commentService.commentInsert(vo);
		
		return "success Comment";
	}
	
	// 대댓글 등록
	@PostMapping("/insertReply")
	@ResponseBody
	public String replyInsert(CommentVO vo, HttpSession session) {
		
		vo.setUserId((String) session.getAttribute("userId"));		
		commentService.replyInsert(vo);
		// return 타입을 vo로 넘겨주는걸 많이 함(등록, 수정시) -> append하는것(리스트 재 조회가 아니라)
		return "success Reply";
	}
	
	// 댓글 수정
	@PostMapping("/updateComment/{no}")
	@ResponseBody
	public String commentUpdate(CommentVO vo, @PathVariable int no, @RequestParam String subject) {
		vo.setNo(no);
		vo.setSubject(subject);
		
		commentService.commentUpdate(vo);
		
		return "success update";
	}
	
	// 댓글 삭제
	@PostMapping("/deleteComment/{no}")
	@ResponseBody
	public String commentDelete(@PathVariable int no) {
		
		commentService.commentDelete(no);
		
		return "success delete";
	}

}
