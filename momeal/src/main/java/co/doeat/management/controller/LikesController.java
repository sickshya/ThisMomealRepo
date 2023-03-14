package co.doeat.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.LikesService;

@Controller
public class LikesController {

	@Autowired
	private LikesService likesService;

	// 마이페이지 - 식단 좋아요 리스트 출력
//	@RequestMapping("/myLikeList")
//	public String myLikeList(Model model, HttpSession session, HttpServletRequest request) {
//		session = request.getSession();
//		String userId = (String) session.getAttribute("userId");
//
//		model.addAttribute("myPageLike", likesService.myLikeList(userId));
//
//		model.addAttribute("challLike", likesService.myChallList(userId));
//		return "myPages/myLikeList";
//	}

	// 마이페이지 - 식단 좋아요 리스트 출력
	@RequestMapping("/myLikeList")
	public String myLikeList() {
		return "myPages/myLikeList";
	}

}
