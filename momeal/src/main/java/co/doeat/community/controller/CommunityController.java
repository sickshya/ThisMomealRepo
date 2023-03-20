package co.doeat.community.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.activity.service.MealVO;
import co.doeat.community.service.CommunityService;

@Controller
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;


	// 전체조회
	@RequestMapping("/community")
	public String community(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("userId");
		
		// 게시물 max 번호
		model.addAttribute("maxNo", communityService.getMaxFeedNo());
		
		// 인기순 조회
		model.addAttribute("cmntRec", communityService.likeRankCmntList(id));
		
		return "community/community";
	}
	
	// 전체조회 + 페이징
	@RequestMapping("/communityList")
	@ResponseBody
	public List<Map<String, Object>> communityList(MealVO vo, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		vo.setUserId(userId);
		return communityService.getCmntList(vo);
	}
	
	// 단건조회(ajax)
	@GetMapping("/community/{no}")
	@ResponseBody
	public MealVO challengeOne(Model model, Map<String, Object>map, HttpSession session, @PathVariable int no) {
		
		map.put("userId", session.getAttribute("userId"));
		map.put("no", no);
		
		return communityService.getCommunity(map);
	}
	
//	// 커뮤니티 검색결과창
//	@RequestMapping("/communitySearch")
//	public String communitySearch() {
//		return "content/community_search";
//	}
}
