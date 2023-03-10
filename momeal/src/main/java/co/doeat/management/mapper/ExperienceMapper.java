package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.community.service.UsersVO;
import co.doeat.management.service.ExperienceSearchVO;
import co.doeat.management.service.ExperienceVO;
import co.doeat.management.service.ExprParticipantsVO;
import co.doeat.management.service.GroupPurchaseSettlementVO;
import co.doeat.record.service.AttendanceVO;
import co.doeat.record.service.PointLogVO;

public interface ExperienceMapper {

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
	public List<ExprParticipantsVO> expOrderList(); // 배송정보 전체조회
	
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
}
