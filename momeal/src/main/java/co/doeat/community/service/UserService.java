package co.doeat.community.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.doeat.Paging;
import co.doeat.record.service.PointLogVO;

@Service
public interface UserService {
	// 메인
	UsersVO hmUserInfo(String id); // 메인에 뿌려줄, 로그인한 유저의 식단,팔로잉,팔로워 수

	// 회원가입
	boolean isIdCheck(String id); // 아이디 중복 체크

	// 유저
	int insertUserInfo(UsersVO vo); // 유저정보 추가/삽입

	// 마이페이지
	int updateUserInfo(UsersVO vo); // 회원정보 수정

	int updateWithdraw(UsersVO vo); // 회원탈퇴 업데이트

	UsersVO userSelect(String userId); // 로그인, 회원정보수정폼 호출시

	// 그룹 상세(글하나, 이미지 다수) select
	UsersVO grpSelect(String userId);

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

	// myFeed회원정보
	UsersVO myFeedUserSelect(String id);

	// 포인트조회
	List<PointLogVO> pointList(PointLogVO pvo);

	// 누적포인트
	UsersVO myPoint(String userId);

	int getCountTotals(Paging paging, String userId);

}
