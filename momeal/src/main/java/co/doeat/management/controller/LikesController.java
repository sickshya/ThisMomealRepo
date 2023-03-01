package co.doeat.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.management.service.LikesService;
import co.doeat.management.service.LikesVO;

@Controller
public class LikesController {

	@Autowired
	private LikesService likesService;

	// 마이페이지 - 식단 좋아요 리스트 출력
	@RequestMapping("/myLikeList")
	public String myLikeList(Model model) {
		model.addAttribute("myPageLike", likesService.myLikeList());
		System.out.println("=================================" + likesService.myLikeList());
		return "myPages/myLikeList";
	}

	// 마이페이지 - 식단 좋아요 상세
	@GetMapping("/myLikeList/{postNo}")
	@ResponseBody
	public Map<String, Object> likesSelect(@PathVariable int postNo) {
		System.out.println(likesService.mylikeSelect(postNo));
		return likesService.mylikeSelect(postNo);
	}

	// 마이페이지 - 챌린지 좋아요 리스트 출력
	@RequestMapping("/myChallList")
	public String myChallList(Model model) {
		model.addAttribute("challLike", likesService.myChallList());
		return "myPages/myChallList";
	}

	// 마이페이지 - 좋아요 삭제
	@GetMapping("/myLikeDel")
	public int myLikeDel(Model model, LikesVO vo) {
		return likesService.myLikeDel(vo);
	}

}
