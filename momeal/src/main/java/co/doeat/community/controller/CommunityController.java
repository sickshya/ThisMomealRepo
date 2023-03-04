package co.doeat.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.activity.service.MealVO;
import co.doeat.community.service.CommunityService;

@Controller
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;

	
	// 전체조회
	@RequestMapping("/community")
	public String community(Model model) { // getCmniList(여기 받는 값이 없는데 = 리스트 만들수가 없는데) -> community의 매개변수로 modelList<Map<String, Object>> list를 넘기려고 하니까 No primary or single unique constructor found~ 에러가 뜨는거임 ㅠ
		
		model.addAttribute("cmntList", communityService.getCmntList());
		
		System.out.println("====== 결과 ▶ " + communityService.getCmntList());
		
		return "community/community";
	}
	
	
	// 단건조회(ajax)
	@GetMapping("/community/{no}")
	@ResponseBody // ajax 처리할때 필요
	public MealVO challengeOne(@PathVariable int no) {
		
		System.out.println(communityService.getCommunity(no));
		
		return communityService.getCommunity(no);
	}


	
	// 커뮤니티 검색결과창
	@RequestMapping("/communitySearch")
	public String communitySearch() {
		return "content/community_search";
	}
}
