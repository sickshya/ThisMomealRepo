package co.doeat.community.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.Paging;
import co.doeat.community.service.UserSearchVO;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private UserService userService; 
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원가입 폼 호출
	@RequestMapping("/signup/signupFrm")
	public String userJoinForm() {
		return "users/signupFrm";
	}

	// 마이페이지 접근을 위해서, 본인확인용 비밀번호 입력창 호출
	@RequestMapping("/usrs/enterMypage")
	public String membpw(UsersVO vo) {
		return "users/chkPwdFrm";
	}

	// 회원정보 수정폼 호출
	@RequestMapping("/userEditForm")
	public String userEditForm(UsersVO vo, Model model) {

		model.addAttribute("userInfo", userService.userSelect("user2"));

		return "users/userEditForm";
	}

	// 회원정보 수정시 .. 재확인필요
	@RequestMapping("/userEdit")
	public String userEdit(UsersVO vo, Model model, HttpSession session) {
 
		String id = (String) session.getAttribute("userId");
		vo = userService.userSelect(id);
		if (vo != null) {
			session.setAttribute("id", vo.getUserId());
			session.setAttribute("userName", vo.getUserName());
			session.setAttribute("nickName", vo.getNickName());
			session.setAttribute("tel", vo.getTel());
			session.setAttribute("email", vo.getEmail());
			session.setAttribute("zipcode", vo.getZipcode());
			session.setAttribute("addr", vo.getAddr());
		}
		return "myPages/userEdit";
	}

	// 회원탈퇴신청폼 호출
	@RequestMapping("/userWithdrawForm")
	public String userWithdrawForm(UsersVO vo) {
		return "myPages/userWithdrawForm";
	}

	// 회원탈퇴신청 동작
	@RequestMapping("/userWithdraw")
	public String userWithdraw(UsersVO vo, HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.setAttribute("userId", "user1");

		userService.updateWithdraw(vo);
		return "myPages/userWithdraw";
	}

	// =============관리자 유저=================================
	// 페이징
	@RequestMapping("/adminUser")
	public String adminUser(Model model, @ModelAttribute("esvo") UserSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(userService.getCountTotal(svo));

		model.addAttribute("getadminUserList", userService.getAdminUserList(svo));

		return "admin/adminUser";

	}

}
