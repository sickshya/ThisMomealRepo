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
	
	// 그룹 댓글 출력
	@PostMapping("/GroupComment")
	@ResponseBody
	public List<CommentVO> groupCommentsList(CommentVO vo, @RequestParam String groupMember, @RequestParam int postNo) {
		vo.setGroupMember(groupMember);
		vo.setPostNo(postNo);
		vo.setBoardCode("CT05");
		return commentService.groupCommentsList(vo);
	}
	
	// 댓글 등록
	@PostMapping("/insertComment")
	@ResponseBody
	public String commentInsert(CommentVO vo, HttpSession session, @RequestParam int postNo, @RequestParam String subject, @RequestParam String boardCode, @RequestParam String groupMember) {
		
		System.out.println("글 번호 ========== " + postNo);
		vo.setPostNo(postNo);
		vo.setSubject(subject);
		vo.setUserId((String) session.getAttribute("userId"));
		vo.setBoardCode(boardCode);
		vo.setGroupMember(groupMember);
		System.out.println("========== 댓글등록용 VO ▶ " + vo);
		
		commentService.commentInsert(vo);
		
		return "success Comment";
	}
	
	// 대댓글 등록
	@PostMapping("/insertReply")
	@ResponseBody
	public String replyInsert(CommentVO vo, HttpSession session, @RequestParam int postNo, @RequestParam String subject, @RequestParam int cmmtGroup, @RequestParam String boardCode, @RequestParam String groupMember) {
		
		vo.setPostNo(postNo);
		vo.setSubject(subject);
		vo.setCmmtGroup(cmmtGroup);
		vo.setUserId((String) session.getAttribute("userId"));
		vo.setBoardCode(boardCode);
		vo.setGroupMember(groupMember);
		
		System.out.println("========== 댓글등록용 VO ▶ " + vo);
		
		commentService.replyInsert(vo);
		
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
