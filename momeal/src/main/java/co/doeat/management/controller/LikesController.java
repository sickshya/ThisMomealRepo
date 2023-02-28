package co.doeat.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.LikesService;

public class LikesController {
	
	@Autowired LikesService likesService;
	
	// 마이페이지 - 좋아요 리스트 출력
	@RequestMapping("/myLikeList")
	public String myLikeList() {
		return "myPages/myLikeList";
	}

}
