package co.doeat.management.service;

import java.util.List;
import java.util.Map;

public interface ExperienceService {

	// 메인
	public List<ExperienceVO> hmExprList(); // 메인에 뿌려줄 체험단 전체 조회

	// 체험단-메인
	public List<ExperienceVO> getExperList(); // 전체조회

	public List<ExperienceVO> getExperiencing(); // 진행중조회

	// 체험단-상세
	public List<ExperienceVO> ExperOne(int no); // 단건조회

	// 체험단-신청
	public int expInsert(ExprParticipantsVO vo); // 체험단 Insert

	public Map<String, Object> expSelect(int no); // 체험단 select

	public int expUpdate(ExperienceVO vo); // 체험단 Update

	// 체험단-조회
	public List<ExprParticipantsVO> expOrderList(String userId, int no); // 체험단 참여내역 조회

//	// 체험단-신청인원 제한버튼
//	public Boolean ajaxExpBtn(ExperienceVO vo); // 신청인원 제한 시 버튼 막기

	// 관리자 +++++++++++++++++++++++++++++++++++
	List<ExperienceVO> adminExperienceGroupList(ExperienceSearchVO svo);

	int getCountTotal(ExperienceSearchVO svo);

	// 체험단 - 물품등록
	int adminEXInsert(ExperienceVO vo);

	// 체험단 단건조회 select
	ExperienceVO adminEXSelect(int no);

	// 체험단 삭제
	int adminEXDelete(int no);

	// 체험단 update
	int adminEXUpdate(ExperienceVO vo);

	// 체험단 리뷰

	// public List<ExpReviewVO> ExpReviewList(String userId, int no); // 리뷰 전체조회

}
