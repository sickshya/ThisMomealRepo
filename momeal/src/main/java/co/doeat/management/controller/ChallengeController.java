package co.doeat.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeService;

@Controller
public class ChallengeController {
	@Autowired
	private ChallengeService challengeService;

	// ▶ 챌린지 ◀
	// 전체조회
	// 세션에 아이디 값도 담아두기(임시)
	@RequestMapping("/challenge")
	public String challengeMain(Model model, HttpServletRequest request) { 
//		// 임시로 세션에 ID 값 담기
		HttpSession session = request.getSession();
		session.setAttribute("userId", "user3");
		
		String id = (String) session.getAttribute("userId");
		
		// 전체조회
		model.addAttribute("challList", challengeService.getChallList(id));

		// 인기순(좋아요 많은 순) 조회
		model.addAttribute("challRec", challengeService.likeRankChallList(id));

		System.out.println("====== 결과 ▶ " + session.getAttribute("userId"));
		System.out.println("====== 인기순 조회 ▶ " + challengeService.likeRankChallList(id));

		return "challenge/challenge";
	}

	// 단건조회
	@GetMapping("/challenge/{no}")
	public String challenge(Model model, Map<String, Object>map, HttpServletRequest request, @PathVariable int no) {
		HttpSession session = request.getSession();
		map.put("userId", session.getAttribute("userId"));
		map.put("no", no);
		System.out.println("값 잘 들어갓나요...... " + map);
		
		model.addAttribute("chall", challengeService.getChallenge(map));
		System.out.println("====== 단건조회 ▶ " + challengeService.getChallenge(map));
		return "challenge/challengeDetail";
	}

	
	// 챌린지 참여하기
	@PostMapping("/attendChallenge")
	public String attendChallenge(ChallengeParticipationVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		// 세션에 담겨있는 아이디 값을 vo의 userId에 담기. (String) 처리 해줘야함
		vo.setUserId((String) session.getAttribute("userId"));
		challengeService.attendChall(vo);
		
		return "redirect:/myChallengeList";
	}

//	// 챌린지 검색결과창
//	@RequestMapping("/challengeSearch")
//	public String challengeSearch() {
//		return "challenge/challengeSearch";
//	}
	
	// ▶ 나의 챌린지 ◀
	// 진행중 - 전체조회
	@RequestMapping("/myChallenge")
	public String myChallenge(Model model, Map<String, Object>map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		map.put("userId", session.getAttribute("userId"));
//		map.put("userId", "user3");
		
		model.addAttribute("myChall", challengeService.getMyChallList(map));
		
		System.out.println("====== 결과 ▶ " + challengeService.getMyChallList(map));
		
		return "challenge/myChallenge";
	}
	
	// 진행중 - 전체조회(챌린지 참여신청 후 이동)
	@RequestMapping("/myChallengeList")
	public String myChallengeList(Model model, Map<String, Object>map, HttpServletRequest request) {
		
		// 세션의 로그인 정보에 담겨있는 유저 본인의 아이디를 {key:"userId" value:세션 아이디} 이렇게 담아서 보내야함!!!
		// 그럼 그 값을 읽어서 해당 유저의 정보만 보여줌
		// 지금은 임시로 user3이라고 보내는중...
		HttpSession session = request.getSession();
		map.put("userId", session.getAttribute("userId"));
		
//		map.put("userId", "user3");
		model.addAttribute("myChall", challengeService.getMyChallList(map));
		
		System.out.println("====== 결과 ▶ " + challengeService.getMyChallList(map));
		
		return "challenge/myChallenge";
	}
	
	// 진행중 - 단건조회
	@GetMapping("/myChallenge/{no}")
	public String myChallengOne(Model model, @PathVariable int no) {

		model.addAttribute("chall", challengeService.getMyChall(no));
		System.out.println("====== 단건조회 ▶ " + challengeService.getMyChall(no));

		return "challenge/myChallengeDetail";
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	// 챌린지 관리자
	@RequestMapping("/adminChallenge")
	public String adminChallenge() {
		return "admin/adminChallenge";
	}

	@RequestMapping("/adminChalInsert.do")
	public String adminChalInsert() {
		return "admin/adminChalInsert";
	}
}
