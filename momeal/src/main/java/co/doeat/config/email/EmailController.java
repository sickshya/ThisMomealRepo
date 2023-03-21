package co.doeat.config.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired RegisterMail registerMail;
	
	@PostMapping("/signup/mailConfirm.do")
	String mailConfirm(@RequestParam("email") String email) throws Exception {
		String code = registerMail.sendSimpleMessage(email);
		return code;
	}
	
	@PostMapping("/grpInvite/mailConfirm.do")
	String inviteMailConfirm(@RequestParam("email") String email) throws Exception {
		String code = registerMail.sendgrpInviteMessage(email);
		return code;
	}
	
}
