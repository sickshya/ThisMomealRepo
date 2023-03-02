package co.doeat.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.LikesService;


@Controller
public class LikesController {

	@Autowired
	private LikesService likesService;

	// 마이페이지 - 식단 좋아요 리스트 출력
	@RequestMapping("/myLikeList")
	public String myLikeList(Model model, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");
		
		model.addAttribute("myPageLike", likesService.myLikeList());
		System.out.println("=================================" + likesService.myLikeList());
		return "myPages/myLikeList";
	}

//	// 마이페이지 - 챌린지 좋아요 리스트 출력
//	@RequestMapping("/myChallList")
//	public String myChallList(Model model) {
//		model.addAttribute("challLike", likesService.myChallList());
//		return "myPages/myChallList";
//	}


}
