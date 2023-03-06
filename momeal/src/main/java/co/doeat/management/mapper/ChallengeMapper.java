package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeSearchVO;
import co.doeat.management.service.ChallengeVO;

public interface ChallengeMapper {
	// ▶ 챌린지
	public List<Map<String, Object>> getChallList(String id); // 전체조회

	public List<Map<String, Object>> likeRankChallList(String id); // 인기순(좋아요 많은 순) 조회

	public ChallengeVO getChallenge(Map<String, Object> map); // 단건조회

	public int challInsert(ChallengeVO vo); // 챌린지 등록

	public int attendChall(ChallengeParticipationVO vo); // 챌린지 참여신청

	// ▶ 나의 챌린지
	// 1. 진행중인 챌린지
	public List<Map<String, Object>> getMyChallList(Map<String, Object> map); // 전체조회. getMyChallList() 여기에 userId
																				// 들어가야할거같음

	public Map<String, Object> getMyChall(int no); // 단건조회. 매개변수로 userId도 들어가야함!!!

	/// ===================관리자===========================
	// ▶ 관리자 챌린지
	// 관리자 챌린지 리스트
	List<ChallengeVO> adminChalList(ChallengeSearchVO svo);// 관리자 챌린지리스트

	int getCountTotal(ChallengeSearchVO svo);

	// 챌린지 등록하기
	int adminCHInsert(ChallengeVO vo);
	// 챌린지 재등록하기

	// 챌린지 단건조회 select
	ChallengeVO adminCHSelect(int no);

	// 챌린지 삭제
	int adminCHDelete(int no);

}
