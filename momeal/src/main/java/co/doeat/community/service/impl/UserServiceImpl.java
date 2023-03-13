package co.doeat.community.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.common.service.CommonCodeVO;
import co.doeat.community.mapper.UserMapper;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UserSearchVO;
import co.doeat.community.service.UsersVO;
import co.doeat.record.service.PointLogVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean isIdCheck(String id) {
		return userMapper.isIdCheck(id);
	}

	@Override
	public int insertUserInfo(UsersVO vo) {
		return userMapper.insertUserInfo(vo);
	}

	@Override
	public int updateUserInfo(UsersVO vo) {
		return userMapper.updateUserInfo(vo);
	}

	@Override
	public UsersVO userSelect(String userId) {
		return userMapper.userSelect(userId);
	}

	@Override
	public int updateWithdraw(UsersVO vo) {
		return userMapper.updateWithdraw(vo);
	}

	@Override
	public List<UsersVO> getAdminUserList(UserSearchVO svo) {

		return userMapper.getAdminUserList(svo);
	}

	@Override
	public int getCountTotal(UserSearchVO svo) {

		return userMapper.getCountTotal(svo);
	}

	@Override
	public List<UsersVO> adminUserList() {
		return userMapper.adminUserList();
	}
//===============================================================포인트==========================
	@Override
	public int updateATPoint(UsersVO uvo) {
		return userMapper.updateATPoint(uvo);
	}

	@Override
	public UsersVO grpSelect(String userId) {
		return userMapper.grpSelect(userId);
	}


	@Override
	public UsersVO myFeedUserSelect(String id) {
		return userMapper.myFeedUserSelect(id);
	}

	@Override
	public List<PointLogVO> pointList(String userId) {
		return userMapper.pointList(userId);
	}

	@Override
	public UsersVO myPoint(String userId) {
		return userMapper.myPoint(userId);
	}



	





}
