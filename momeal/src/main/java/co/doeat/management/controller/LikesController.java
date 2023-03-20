package co.doeat.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LikesController {


	// 마이페이지 - 식단 좋아요 리스트 출력
	@RequestMapping("/usr/myLikeList")
	public String myLikeList() {
		return "myPages/myLikeList";
	}

}
