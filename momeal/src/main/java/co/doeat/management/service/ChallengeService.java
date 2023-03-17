package co.doeat.management.service;

import java.util.List;
import java.util.Map;

public interface ChallengeService {
	// 메인
	public List<ChallengeVO> hmChlgList(); // 메인에 뿌려줄 챌린지 전체 조회

	// ▶ 챌린지
	public List<Map<String, Object>> getChallList(ChallengeVO vo); // 전체조회

	public List<Map<String, Object>> likeRankChallList(String id); // 인기순(좋아요 많은 순) 조회

//	public List<ChallengeSearchVO> getSearchList(ChallengeSearchVO vo); // 검색

	public int getMaxChallNo(); // 게시물 max 번호
	
	public ChallengeVO getChallenge(String userId, int no); // 단건조회

	public int insertChallenge(ChallengeVO vo); // 챌린지 등록

	public int attendChall(ChallengeVO vo); // 챌린지 참여신청

	// ▶ 나의 챌린지
	// 1. 진행중인 챌린지
	public List<Map<String, Object>> getMyChallList(String userId); // 진행중인 챌린지 전체조회

	public ChallengeVO getMyChall(String userId, int no); // 진행중인 챌린지 단건조회

	public List<ChallengeValidationVO> getChallImgList(String userId, int chalNo); // 챌린지별 참여자 인증 사진 조회

	public List<ChallengeValidationVO> getMyChallImg(String userId, int chalNo); // 챌린지별 나의 인증 사진 조회

	public ChallengeValidationVO getMyChallImgOne(int no); // 인증 사진 단건조회

	public ChallengeValidationVO insertMyChallImg(ChallengeValidationVO vo); // 인증 사진 등록
	
	public Boolean valImgCheck(ChallengeValidationVO vo); // 인증하기 버튼 중복확인

	// 2. 종료된 챌린지
	public List<Map<String, Object>> getMyEndChallList(String userId); // 종료된 챌린지 전체조회

	public List<ChallengeReviewVO> getReviewList(int no); // 챌린지별 후기 전체조회

	public ChallengeReviewVO getReviewOne(String userId, int no); // 후기 단건조회

	public int insertReview(ChallengeReviewVO vo); // 후기 등록

	public int updateReview(ChallengeReviewVO vo); // 후기 수정

	public int deleteReview(ChallengeReviewVO vo); // 후기 삭제

	// ===================관리자===========================
	// ▶ 관리자 챌린지
	// 관리자 챌린지 리스트
	List<ChallengeVO> adminChalList(ChallengeSearchVO svo);// 관리자 챌린지리스트

	int getCountTotal(ChallengeSearchVO svo);

	// 챌린지 등록하기
	int adminCHInsert(ChallengeVO vo);

	// 챌린지 재등록하기
	int adminCHReInsert(ChallengeVO vo);

	// 챌린지 단건조회 select
	ChallengeVO adminCHSelect(int no);

	// 챌린지 삭제
	int adminCHDelete(int no);

	// 챌린지 수정
	int adminCHUpdate(ChallengeVO vo);
}
