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
	public Map<String, Object> likesSelect(LikesVO vo, @PathVariable int postNo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		vo.setUserId((String) session.getAttribute("userId"));
		vo.setPostNo(postNo);

		return likesService.mylikeSelect(vo);
	}

	// 마이페이지 - 좋아요 삭제
	@PostMapping("/myLikeDel/{no}")
	public int myLikeDel(LikesVO vo, @PathVariable int no, HttpSession session) {
	//	session = request.getSession();

		vo.setNo(no);
		return likesService.myLikeDel(vo);
	}

	// 마이페이지 - 챌린지 좋아요 추가
	@PostMapping("/insertLikeChall/{postNo}")
	public LikesVO insertLikeChall(LikesVO vo, @PathVariable int postNo, HttpSession session,
			HttpServletRequest request) {
		session = request.getSession();

		vo.setUserId((String) session.getAttribute("userId"));
		vo.setPostNo(postNo);
		vo.setBoardCode("CT01");
		likesService.insertLikeChall(vo);
		return vo;
	}
	
	// 마이페이지 - 식단 좋아요 추가
		@PostMapping("/insertLikeMeal/{postNo}")
		public LikesVO insertLikeMeal(LikesVO vo, @PathVariable int postNo, HttpSession session, HttpServletRequest request) {
			session = request.getSession();

			vo.setUserId((String) session.getAttribute("userId"));
			vo.setPostNo(postNo);
			vo.setBoardCode("CT04");
			likesService.insertLikeMeal(vo);
			return vo;
		}

}
