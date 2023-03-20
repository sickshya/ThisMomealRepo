package co.doeat.community.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	// 회원가입시 ID 중복체크(duplicate check)
	@PostMapping("/signup/checkId.do")
	public String idDuplicateCheck(@RequestParam("userId") String triedId) {
		boolean b = userService.isIdCheck(triedId);
		String result = "0"; // when the id doesn't exist
		if (!b) {
			result = "1"; // it exists
		}
		return result;
	}

	// 비밀번호 일치하는지 확인
	@GetMapping("/chkPwdFrm")
	public boolean userPwdForm(UsersVO vo, HttpSession session) {
		boolean a = false;

		String id = (String) session.getAttribute("userId");

		UsersVO uvo = userService.userSelect(id);

		if (uvo != null) {
			if (bcryptEncoder.matches(vo.getPassword(), uvo.getPassword())) { // 암호화 안된거 - 암호화 된거
				a = true;
			}
		}
		return a;
	}

}
