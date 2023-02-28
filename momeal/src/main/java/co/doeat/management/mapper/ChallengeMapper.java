package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.management.service.ChallengeVO;

public interface ChallengeMapper {
	// ▶ 챌린지
	public List<ChallengeVO> getChallList(); // 전체조회
	public ChallengeVO getChallenge(int no); // 단건조회
	public int challInsert(ChallengeVO vo); // 챌린지 등록
	
	// ▶ 나의 챌린지
	// 1. 진행중인 챌린지
	public List<Map<String, Object>> getMyChallList(); // 전체조회. getMyChallList() 여기에 userId 들어가야할거같음
	public Map<String, Object> getMyChall(int no); // 단건조회. 매개변수로 userId도 들어가야함!!!

}
