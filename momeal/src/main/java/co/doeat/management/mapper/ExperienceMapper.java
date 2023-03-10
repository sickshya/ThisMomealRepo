package co.doeat.management.mapper;

import java.util.List;

import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;

public interface ExperienceMapper {
	
	// 체험단-메인
	public List<ExperienceVO> getExperList(); // 전체조회

	public List<ExperienceVO> getExperiencing(); // 진행중조회
	
	// 체험단-상세
	public List<ExperienceVO> ExperOne(int no); // 단건조회
	
	// 체험단-신청
	public int ExpInsert(ExperienceVO vo); // 체험단 신청하기
	
  
	// 관리자 +++++++++++++++++++++++++++++++++++
	List<ExperienceVO> adminExperienceGroup();
}
