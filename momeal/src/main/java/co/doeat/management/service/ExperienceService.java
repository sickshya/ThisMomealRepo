package co.doeat.management.service;

import java.util.List;

public interface ExperienceService{

	// 체험단-메인
	public List<ExperienceVO> getExperList(); // 전체조회
	
	public List<ExperienceVO> getExperiensing(); // 진행중조회
	
	
}
