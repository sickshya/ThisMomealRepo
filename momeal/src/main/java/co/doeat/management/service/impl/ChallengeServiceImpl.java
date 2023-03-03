package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.ChallengeMapper;
import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ChallengeVO;

@Service
public class ChallengeServiceImpl implements ChallengeService {
	
	@Autowired
	private ChallengeMapper challengeMapper;
	
	// ▶ 챌린지
	// 전체조회
	@Override
	public List<Map<String, Object>> getChallList(String id) {
		return challengeMapper.getChallList(id);
	}
	
	// 인기순(좋아요 많은 순) 조회
	@Override
	public List<Map<String, Object>> likeRankChallList(String id) {
		return challengeMapper.likeRankChallList(id);
	}

	// 단건조회
	@Override
	public ChallengeVO getChallenge(Map<String, Object>map) {
		return challengeMapper.getChallenge(map);
	}

	// 챌린지 등록
	@Override
	public int challInsert(ChallengeVO vo) {
		return challengeMapper.challInsert(vo);
	}
	
	// 챌린지 참여하기
	@Override
	public int attendChall(ChallengeParticipationVO vo) {
		return challengeMapper.attendChall(vo);
	}



	// ▶ 나의 챌린지
	// 진행중 - 전체조회
	@Override
	public List<Map<String, Object>> getMyChallList(Map<String, Object> map) {
		return challengeMapper.getMyChallList(map);
	}

	// 진행중 - 단건조회
	@Override
	public Map<String, Object> getMyChall(int no) {
		return challengeMapper.getMyChall(no);
	}

	
	// ▶ 관리자
	@Override
	public List<ChallengeVO> adminChalList() {
		return challengeMapper.adminChalList();
	}

}
