package co.doeat.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	boolean isIdCheck(String id); // 아이디 중복 체크

	// 유저
	int insertUserInfo(UsersVO vo); // 유저정보 추가/삽입

	// 마이페이지
	int updateUserInfo(UsersVO vo); // 회원정보 수정

	int updateWithdraw(UsersVO vo); // 회원탈퇴 업데이트

	UsersVO userSelect(String userId); // 로그인, 회원정보수정폼 호출시

	// 관리자===========================================
	// 회원 목록 출력
	List<UsersVO> adminUserList();

	// 페이징하는 목록
	List<UsersVO> getAdminUserList(UserSearchVO svo);

	// 회원전체수 계산
	int getCountTotal(UserSearchVO svo);

	// 포인트==============================================
	// 출석체크 포인트 업데이트
	int updateATPoint(UsersVO uvo);
}
