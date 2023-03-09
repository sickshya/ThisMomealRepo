package co.doeat.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	// 회원가입시 ID 중복체크(duplicate check)
	@GetMapping("/idDuplicateCheck.do")
	public String idDuplicateCheck(@RequestParam("id") String triedId) {
		boolean b = userService.isIdCheck(triedId);
		String result = "1"; // when the id doesn't exist
		if (!b) {
			result = "0"; // it exists
		}
		return result;
	}

	// 비밀번호 일치하는지 확인
	// 수정 예정
	@GetMapping("/userPwdForm")
	public boolean userPwdForm(UsersVO vo, Model model, HttpSession session, HttpServletRequest request) {
		boolean a = false;
		 session = request.getSession();
		 session.setAttribute("userId", "user1");
		 
		String id = (String) session.getAttribute("userId");
		
		UsersVO uvo = userService.userSelect(id);

		if (uvo != null) {
			if (!uvo.getPassword().equals(vo.getPassword())) {
				a = true;
			}
		}
		return a;
	}

	// 폼에 입력된 값들로 회원가입
	// 수정 예정
	@PostMapping("/userJoin.do")
	public String userJoin(UsersVO uvo, Model model) {
		userService.insertUserInfo(uvo);
		model.addAttribute("userInfo", uvo);

		return "myFeed/myFeed"; // 임의값
	}

}
