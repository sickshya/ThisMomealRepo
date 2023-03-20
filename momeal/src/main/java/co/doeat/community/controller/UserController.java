package co.doeat.community.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.doeat.Paging;
import co.doeat.common.service.CommonCodeVO;
import co.doeat.community.service.UserSearchVO;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import co.doeat.record.service.PointLogVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	// 회원가입 폼 호출
	@RequestMapping("/signup/signupFrm")
	public String userJoinForm() {
		return "users/signupFrm";
	}

	// 마이페이지 접근을 위해서, 본인확인용 비밀번호 입력창 호출
	@RequestMapping("/usr/enterMypage")
	public String membpw(UsersVO vo) {
		return "users/chkPwdFrm";
	}

	// 회원정보 수정폼 호출
	@RequestMapping("/usr/userEditForm")
	public String userEditForm(UsersVO vo, Model model, HttpSession session) {
		model.addAttribute("userInfo", userService.userSelect((String) session.getAttribute("userId")));
		return "users/userEditForm";
	}

//	// 회원정보 수정시
//	@RequestMapping("/userEdit")
//	public String userEdit(UsersVO vo) {
//
//		userService.updateUserInfo(vo);
//
//		return "redirect:/userEditForm";
//	}

	// 회원정보 수정시
	@PostMapping("/userEdit")
	public String userEdit(UsersVO vo) {

		vo.setPassword(bcryptEncoder.encode(vo.getPassword()));
		userService.updateUserInfo(vo);

		return "redirect:/userEditForm";
	}

	// 회원탈퇴신청폼 호출
	@RequestMapping("/usr/userWithdrawForm")
	public String userWithdrawForm(UsersVO vo, HttpSession session, Model model) {
		model.addAttribute("userInfo", userService.userSelect((String) session.getAttribute("userId")));
		return "users/userWithdrawForm";
	}

	// 회원탈퇴신청 동작
	@RequestMapping("/userWithdraw")
	public String userWithdraw(UsersVO vo) {
		userService.updateWithdraw(vo);
		return "users/loginFrm";
	}

	// =============관리자 유저=================================
	// 페이징
	@RequestMapping("/admin/usrList")
	public String adminUserList(Model model, @ModelAttribute("esvo") UserSearchVO svo, Paging paging) {
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(userService.getCountTotal(svo));

		model.addAttribute("allUser", userService.getAdminUserList(svo));

		return "admin/allUserList";

	}

	// 마이페이지 포인트
	@RequestMapping("/myPoint")
	public String myPoint(CommonCodeVO cvo, PointLogVO pvo, UsersVO uvo, Model model, HttpSession session,
			Paging paging) {
		String userId = (String) session.getAttribute("userId");
		pvo.setUserId(userId);

		paging.setTotalRecord(userService.getCountTotals(paging, userId));
		pvo.setFirst(paging.getFirst());
		pvo.setLast(paging.getLast());
		model.addAttribute("pointList", userService.pointList(pvo));
		model.addAttribute("selects", userService.myPoint(userId));
		return "myPages/myPoint";
	}

}
