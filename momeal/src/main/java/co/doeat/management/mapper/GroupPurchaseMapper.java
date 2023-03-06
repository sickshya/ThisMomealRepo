package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.common.service.ImageVO;
import co.doeat.management.service.GroupPurchaseListVO;
import co.doeat.management.service.GroupPurchaseSearchVO;
import co.doeat.management.service.GroupPurchaseSettlementVO;

public interface GroupPurchaseMapper {

	// 공동구매-메인
	List<GroupPurchaseListVO> getPurList(); // 전체조회

	List<GroupPurchaseListVO> getPurchasingList(); // 진행중조회

	// 공동구매-상세
	List<GroupPurchaseListVO> getPurOne(int no); // 메인_단건조회

	// 공동구매-주문하기
	List<GroupPurchaseSettlementVO> payList(); // 전체조회

	int payInsert(GroupPurchaseSettlementVO vo); // 저장

	// ++++++++++++++++++++++++++++++++++++++++++++++마이페이지
	// 마이페이지 - 공동구매
	List<GroupPurchaseListVO> getPurchaseList(String userId); // 공동구매 전체리스트 조회

	// 마이페이지 - 리스트에서 구매상세로
	public Map<String, Object> purchaseSelect(String userId, int no); // 공동구매 상세내역 단건조회
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	// 공동구매 물품 리스트

	List<GroupPurchaseListVO> getAdminGroupPurchaseList(GroupPurchaseSearchVO svo);

	int getCountTotal(GroupPurchaseSearchVO svo);
	// 공동구매 물품 등록하기

	int adminGPInsert(GroupPurchaseListVO vo);

	// 공동구매 단건조회 select
	GroupPurchaseListVO adminGPSelect(int no);

	// 공동구매 삭제
	int adminGPDelete(int no);

}
