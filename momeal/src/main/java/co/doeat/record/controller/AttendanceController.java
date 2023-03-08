package co.doeat.record.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.doeat.community.mapper.UserMapper;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import co.doeat.record.service.AttendanceService;
import co.doeat.record.service.AttendanceVO;
import co.doeat.record.service.PointLogService;
import co.doeat.record.service.PointLogVO;

@Controller
public class AttendanceController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	AttendanceService attendanceService;
	@Autowired
	PointLogService pointLogService;
	@Autowired
	UserService userService;
	
	
//	 출석체크
//		@RequestMapping("/attendance")
//		public String attendance() {
//			
//			return "myFeed/attendance";
//		}
		
	//출석체크 페이지 + 정보값
		@RequestMapping("/attendance")
		public String attendance(Model model, HttpServletRequest request) {
			//회원정보
			HttpSession session = request.getSession();
			session.setAttribute("userId", "user1");
			String id = (String) session.getAttribute("userId");
			
			attendanceService.getAttendanceList(id);
			
			return "myFeed/attendance";
		}
		
		
	//출석체크 insert (포인트도 같이)
		@RequestMapping("/attendanceCheck")
		public String attendacne(AttendanceVO vo, PointLogVO pvo, HttpServletRequest request, UsersVO uvo) {
			//회원정보
			//출석테이블에 저장
			attendanceService.atInsert(vo);
			//포인트로그테이블에 저장
			pointLogService.atPointadd(pvo);
			//회원테이블에 누적포인트 업데이트
			userService.updateATPoint(uvo);
			
			return "myFeed/myFeed";
		}
		
	
	
}
