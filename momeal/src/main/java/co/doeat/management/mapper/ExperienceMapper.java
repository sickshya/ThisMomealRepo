package co.doeat.management.mapper;

import java.util.List;

import co.doeat.management.service.ExperienceVO;

public interface ExperienceMapper {
	// 체험단 - 메인
	public List<ExperienceVO> getExperList(); // 전체조회

	public List<ExperienceVO> getExperiencing(); // 진행중조회
  
	// 관리자 +++++++++++++++++++++++++++++++++++
	List<ExperienceVO> adminExperienceGroup();
}
