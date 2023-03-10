package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;

public interface ExperienceMapper {

	// 체험단-메인
	public List<ExperienceVO> getExperList(); // 전체조회

	public List<ExperienceVO> getExperiencing(); // 진행중조회

	// 체험단-상세
	public List<ExperienceVO> ExperOne(int no); // 단건조회

	// 체험단-신청
	public int expInsert(ExprParticipantsVO vo); // 체험단 Insert

	public Map<String, Object> expSelect(int no); // 체험단 select

	public Map<String, Object> expOrderList(int no); // 체험단 정보 조회

	// 관리자 +++++++++++++++++++++++++++++++++++
	List<ExperienceVO> adminExperienceGroup();
}
