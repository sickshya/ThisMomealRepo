package co.doeat.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

/*
 * 작성자 : 박정배
 * 작성일자 : 23.03.17
 * 작성내용 : loginForm()
 * 수정자 :
 * 수정일자 :
 * 수정내용 :
 * 
 */

/**
 * 
 * @author admin
 * @since
 * 로그인
 *
 */
@Log4j2
@Controller
public class LoginController {
	@GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        
        log.info("loginForm view resolve");
        return "users/loginFrm";
    }
	
//	@PostMapping("/logout")
//	public String loout(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login";
//    }
}
