package co.doeat.record.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
		
	//출석체크 중복체크 ,포인트적립
		@RequestMapping("/ajaxAtCheck")
		@ResponseBody
		public String ajaxAtCheck(AttendanceVO vo, PointLogVO pvo, UsersVO uvo,HttpSession session) {
			vo.setUserId((String) session.getAttribute("userId"));
			pvo.setUserId((String) session.getAttribute("userId"));
			uvo.setUserId((String) session.getAttribute("userId"));
			Boolean b = attendanceService.ajaxAtCheck(vo, pvo, uvo);
			String str = (b!=null)? "true" : " false";
			
			return str;
		}
		
	
	
}
