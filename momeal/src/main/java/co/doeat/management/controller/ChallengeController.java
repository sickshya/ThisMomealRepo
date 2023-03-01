package co.doeat.management.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ChallengeVO;

@Controller
public class ChallengeController {
	@Autowired
	private ChallengeService challengeService;

	// ▶ 챌린지
	@RequestMapping("/challenge")
	public String challengeMain(Model model) { 
		// 전체조회
		model.addAttribute("challList", challengeService.getChallList());

		// 추천챌린지
		model.addAttribute("challRec", challengeService.getChallList());

		System.out.println("====== 결과 ▶ " + challengeService.getChallList());

		return "challenge/challenge";
	}

	// 단건조회
	@GetMapping("/challenge/{no}")
	public String challenge(Model model, @PathVariable int no) {

		model.addAttribute("chall", challengeService.getChallenge(no));
		System.out.println("====== 단건조회 ▶ " + challengeService.getChallenge(no));

		return "challenge/challengeDetail";

	}
	
	// 챌린지 참여하기
	@PostMapping("/attendChallenge")
	public String attendChallenge(ChallengeParticipationVO vo) {
		
		challengeService.attendChall(vo);
		
		return "redirect:/myChallengeList";
	}

//	// 챌린지 검색결과창
//	@RequestMapping("/challengeSearch")
//	public String challengeSearch() {
//		return "challenge/challengeSearch";
//	}
	
	// ▶ 나의 챌린지
	// 진행중 - 전체조회
	@RequestMapping("/myChallenge")
	public String myChallenge(Model model) {
		
		model.addAttribute("myChall", challengeService.getMyChallList());
		
		System.out.println("====== 결과 ▶ " + challengeService.getMyChallList());
		
		return "challenge/myChallenge";
	}
	
	// 진행중 - 전체조회(챌린지 참여신청 후 이동)
	@RequestMapping("/myChallengeList")
	public String myChallengeList(Model model, HashMap<String, Object>map) {
		
		System.out.println("유저 아이디 담겼나요..........? " + map);
		
		model.addAttribute("myChall", challengeService.getMyChallList());
		
		System.out.println("====== 결과 ▶ " + challengeService.getMyChallList());
		
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
