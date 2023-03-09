package co.doeat.config.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class EmailRestController {

	@Autowired RegisterMail registerMail;
	
	@PostMapping("/signup/mailConfirm.do")
	public String mailConfirm(@RequestParam("email") String email) throws Exception {
		String code = registerMail.sendSimpleMessage(email);
		log.info("입력된 email 주소 : " + email);
		log.info("인증하기위한 code : " + code);
		return code;
	}
}
