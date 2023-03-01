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
	private ChallengeMapper map;
	
	// ▶ 챌린지
	// 전체조회
	@Override
	public List<Map<String, Object>> getChallList() {
		return map.getChallList();
	}

	// 단건조회
	@Override
	public ChallengeVO getChallenge(int no) {
		return map.getChallenge(no);
	}

	// 챌린지 등록
	@Override
	public int challInsert(ChallengeVO vo) {
		return map.challInsert(vo);
	}
	
	// 챌린지 참여하기
	@Override
	public int attendChall(ChallengeParticipationVO vo) {
		return map.attendChall(vo);
	}



	// ▶ 나의 챌린지
	// 진행중 - 전체조회
	@Override
	public List<Map<String, Object>> getMyChallList() {
		return map.getMyChallList();
	}

	// 진행중 - 단건조회
	@Override
	public Map<String, Object> getMyChall(int no) {
		return map.getMyChall(no);
	}

	
	// ▶ 관리자
	@Override
	public List<ChallengeVO> adminChalList() {
		return map.adminChalList();
	}



}
