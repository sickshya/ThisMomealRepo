package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.ExperienceMapper;
import co.doeat.management.service.ExperienceSearchVO;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceMapper experienceMapper;

	@Override
	public List<ExperienceVO> hmExprList() { // 메인에 뿌려줄 체험단 전체 조회
		return experienceMapper.hmExprList();
	}

	// 전체보기
	@Override
	public List<ExperienceVO> getExperList() {
		return experienceMapper.getExperList();
	}

	// 진행중
	@Override
	public List<ExperienceVO> getExperiencing() {
		return experienceMapper.getExperiencing();
	}

	// 단건조회
	@Override
	public List<ExperienceVO> ExperOne(int no) {
		return experienceMapper.ExperOne(no);
	}

	// 체험단-신청
	@Override
	public int expInsert(ExprParticipantsVO vo) {
		return experienceMapper.expInsert(vo);
	}

	// 체험단-상세
	@Override
	public Map<String, Object> expSelect(int no) {
		return experienceMapper.expSelect(no);
	}

	// 체험단-참여내역 조회
	@Override
	public int expUpdate(ExperienceVO vo) {
		return experienceMapper.expUpdate(vo);

	}

	@Override
	public List<ExprParticipantsVO> expOrderList(String userId, int no) {
		return experienceMapper.expOrderList(userId, no);
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	@Override
	public List<ExperienceVO> adminExperienceGroupList(ExperienceSearchVO svo) {
		return experienceMapper.adminExperienceGroupList(svo);
	}

	@Override
	public int getCountTotal(ExperienceSearchVO svo) {
		return experienceMapper.getCountTotal(svo);
	}

	@Override
	public int adminEXInsert(ExperienceVO vo) {
		experienceMapper.adminEXInsert(vo);
		return vo.getNo();
	}

	@Override
	public ExperienceVO adminEXSelect(int no) {
		return experienceMapper.adminEXSelect(no);
	}

	@Override
	public int adminEXDelete(int no) {
		return experienceMapper.adminEXDelete(no);
	}

	@Override
	public int adminEXUpdate(ExperienceVO vo) {
		return experienceMapper.adminEXUpdate(vo);
	}

	// ---- 체험단 리뷰

//	// 전체조회
//	@Override
//	public List<ExpReviewVO> ExpReviewList(String userId, int no) {
//		return experienceMapper.ExpReviewList(userId, no);
//	}
//	

}
