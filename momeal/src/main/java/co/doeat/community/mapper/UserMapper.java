package co.doeat.community.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.community.service.UserSearchVO;
import co.doeat.community.service.UsersVO;
import co.doeat.record.service.PointLogVO;

public interface UserMapper {
	boolean isIdCheck(String id); // 아이디 중복 체크

	// 유저
	int insertUserInfo(UsersVO vo); // 유저정보 추가/삽입

	// 마이페이지
	int updateUserInfo(UsersVO vo); // 회원정보 수정

	int updateWithdraw(UsersVO vo); // 회원탈퇴 업데이트

	UsersVO userSelect(String userId); // 로그인, 회원정보수정폼 호출시
	
	// 그룹 상세(글하나, 이미지 다수) select
	UsersVO grpSelect(String userId);

	// 매퍼
	// 페이징
	List<UsersVO> adminUserList();

	List<UsersVO> getAdminUserList(UserSearchVO svo);

	int getCountTotal(UserSearchVO svo);

	//포인트=========================================================
	// 출석체크 포인트 업데이트
	int updateATPoint(UsersVO uvo);

	
	//myFeed회원정보
	UsersVO myFeedUserSelect(String id);
	
	//포인트로그
	List<PointLogVO> pointList(String userId);
	
	//누적포인트
	UsersVO myPoint(String userId);
	
}
