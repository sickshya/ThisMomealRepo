package co.doeat.management.service;

import java.util.List;


public interface ExperienceService {

	// 체험단-메인
	public List<ExperienceVO> getExperList(); // 전체조회

	public List<ExperienceVO> getExperiencing(); // 진행중조회

	// 체험단-상세
	List<ExperienceVO> ExperOne(int no); // 단건조회
	
	// 체험단-신청
	public int ExpInsert(ExprParticipantsVO vo); // 배송정보 저장

	// 관리자 +++++++++++++++++++++++++++++++++++
	List<ExperienceVO> adminExperienceGroup();

}
