package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.ExperienceMapper;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceMapper experienceMapper;

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

	@Override
	public Map<String, Object> expSelect(int no) {
		return experienceMapper.expSelect(no);
	}

	@Override
	public List<ExprParticipantsVO> expOrderList() {
		return experienceMapper.expOrderList();
	}

	// 관리자 +++++++++++++++++++++++++++++++++++
	@Override
	public List<ExperienceVO> adminExperienceGroup() {
		return experienceMapper.adminExperienceGroup();
	}

	
}
