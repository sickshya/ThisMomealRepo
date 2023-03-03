package co.doeat.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.management.service.LikesService;
import co.doeat.management.service.LikesVO;

@RestController
public class LikesRestController {

	@Autowired
	LikesService likesService;

	// 마이페이지 - 식단 좋아요 상세
	@GetMapping("/myLikeList/{postNo}")
	public Map<String, Object> likesSelect(@PathVariable int postNo, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");
		
		System.out.println(likesService.mylikeSelect(postNo));
		return likesService.mylikeSelect(postNo);
	}

	// 마이페이지 - 좋아요 삭제
	@PostMapping("/myLikeDel/{no}")
	public int myLikeDel(LikesVO vo, @PathVariable int no, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");
		
		vo.setNo(no);
		vo.setBoardCode("CT04");
		return likesService.myLikeDel(vo);
	}

}
