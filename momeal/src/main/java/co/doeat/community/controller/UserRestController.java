package co.doeat.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	// 회원가입시 ID 중복체크(duplicate check)
	@PostMapping("/signup/checkId.do")
	public String idDuplicateCheck(@RequestParam("userId") String triedId) {
		log.info(triedId + "이 아이디를 받아왔음");
		boolean b = userService.isIdCheck(triedId);
		String result = "0"; // when the id doesn't exist
		if (!b) {
			result = "1"; // it exists
		}
		return result;
	}

	// 폼에 입력된 값들로 회원가입
	@PostMapping("/signup/register.do")
	public String userJoin(UsersVO uvo, Model model) {
		uvo.setPassword(bcryptEncoder.encode(uvo.getPassword()));
		userService.insertUserInfo(uvo);
		return "redirect:/login"; // 임의값
	}

	// 비밀번호 일치하는지 확인
	// 수정 예정
	@GetMapping("/chkPwdFrm")
	public boolean userPwdForm(UsersVO vo, Model model, HttpSession session) {
		boolean a = false;

		String id = (String) session.getAttribute("userId");

		UsersVO uvo = userService.userSelect(id);

		if (uvo != null) {
			if (!uvo.getPassword().equals(vo.getPassword())) {
				a = true;
			}
		}
		return a;
	}

}
