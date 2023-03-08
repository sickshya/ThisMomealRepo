package co.doeat.prj;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodingTest {

	@Test
	public void asdf() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		String userpwd123 = bcrypt.encode("1234");
		String adminpwd = bcrypt.encode("admin");
		System.out.println(adminpwd);
	}
}
