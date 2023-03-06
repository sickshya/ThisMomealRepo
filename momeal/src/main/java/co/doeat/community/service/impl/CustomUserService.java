/*package co.doeat.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.doeat.community.mapper.UserMapper;
import co.doeat.community.service.CustomUserVO;
import co.doeat.community.service.UsersVO;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersVO vo = new UsersVO();
		vo.setUserId(username);
		vo = userMapper.userSelect(vo.getUserId());

		// 사용자가 존재하지 않는 경우
		if (vo == null) {
			throw new UsernameNotFoundException("no user");
		}

		CustomUserVO cvo = new CustomUserVO();
		cvo.setUserId(vo.getUserId());
		cvo.setPwd(vo.getPwd());
		cvo.setUserRole(vo.getUserRole());
		return cvo;
	}

}
*/