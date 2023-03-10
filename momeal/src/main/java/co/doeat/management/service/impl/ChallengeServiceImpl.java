package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.ChallengeMapper;
import co.doeat.management.mapper.GroupPurchaseMapper;
import co.doeat.management.service.ChallengeParticipationVO;
import co.doeat.management.service.ChallengeSearchVO;
import co.doeat.management.service.ChallengeService;
import co.doeat.management.service.ChallengeVO;
import co.doeat.management.service.ChallengeValidationVO;

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
	public ChallengeVO getChallenge(Map<String, Object> map) {
		return challengeMapper.getChallenge(map);
	}

	// 챌린지 등록
	@Override
	public int insertChallenge(ChallengeVO vo) {
		return challengeMapper.insertChallenge(vo);
	}

	// 챌린지 참여하기
	@Override
	public int attendChall(ChallengeParticipationVO vo) {
		return challengeMapper.attendChall(vo);
	}

	// ▶ 나의 챌린지 - 진행중
	// 진행중 - 전체조회
	@Override
	public List<Map<String, Object>> getMyChallList(String userId) {
		return challengeMapper.getMyChallList(userId);
	}

	// 진행중 - 단건조회
	@Override
	public Map<String, Object> getMyChall(String userId, int chalNo) {
		return challengeMapper.getMyChall(userId, chalNo);
	}
	
	// 진행중 - 나의 인증 사진 조회
	@Override
	public List<ChallengeValidationVO> getMyChallImg(String userId, int no) {
		return challengeMapper.getMyChallImg(userId, no);
	}
	
	// 진행중 - 인증 사진 등록
	@Override
	public ChallengeValidationVO insertMyChallImg(ChallengeValidationVO vo) {
		return challengeMapper.insertMyChallImg(vo);
	}
	
	// ▶ 나의 챌린지 - 종료
	// 종료 - 전체조회
	@Override
	public List<Map<String, Object>> getMyEndChallList(String userId) {
		return challengeMapper.getMyEndChallList(userId);
	}

	
	// ▶ 관리자======================================================

	@Override
	public List<ChallengeVO> adminChalList(ChallengeSearchVO svo) {
		return challengeMapper.adminChalList(svo);
	}

	@Override
	public int getCountTotal(ChallengeSearchVO svo) {
		return challengeMapper.getCountTotal(svo);
	}

	@Override
	public int adminCHInsert(ChallengeVO vo) {
		challengeMapper.adminCHInsert(vo);
		return vo.getNo();
	}

	@Override
	public ChallengeVO adminCHSelect(int no) {
		return challengeMapper.adminCHSelect(no);
	}

	@Override
	public int adminCHDelete(int no) {
		return challengeMapper.adminCHDelete(no);
	}

	@Override
	public int adminCHReInsert(ChallengeVO vo) {
		return challengeMapper.adminCHReInsert(vo);
	}

	@Override
	public int adminCHUpdate(ChallengeVO vo) {
		return challengeMapper.adminCHUpdate(vo);
	}

}
