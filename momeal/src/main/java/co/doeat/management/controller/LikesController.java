package co.doeat.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.LikesService;

@Controller
public class LikesController {

	@Autowired
	private LikesService likesService;

	// 마이페이지 - 좋아요 리스트 출력
	@RequestMapping("/myLikeList")
	public String myLikeList(Model model) {
		model.addAttribute("myPageLike", likesService.myLikeList());
		System.out.println("=================================" + likesService.myLikeList());
		return "myPages/myLikeList";
	}

	// 마이페이지 - 좋아요 상세
	// @RequestMapping()

}
