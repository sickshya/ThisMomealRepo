package co.doeat.community.mapper;

import java.util.List;

import co.doeat.community.service.UsersSearchVO;
import co.doeat.community.service.UsersVO;

public interface UserMapper {
	boolean isIdCheck(String id); // 아이디 중복 체크

	// 유저
	int insertUserInfo(UsersVO vo); // 유저정보 추가/삽입

	int updateUserInfo(UsersVO vo); // 회원정보 수정

	int updateWithdraw(UsersVO vo); // 회원탈퇴 업데이트

	UsersVO userSelect(String userId); // 로그인, 회원정보수정폼 호출시

	// 매퍼
	// 페이징
	List<UsersVO> adminUserList();

	List<UsersVO> getAdminUserList(UsersSearchVO svo);

	int getCountTotal(UsersSearchVO svo);

}
