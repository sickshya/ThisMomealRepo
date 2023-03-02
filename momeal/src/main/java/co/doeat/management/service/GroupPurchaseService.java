package co.doeat.management.service;

import java.util.List;

import co.doeat.common.service.ImageVO;

public interface GroupPurchaseService {

	List<GroupPurchaseListVO> getPurList(); // 메인_전체조회

	GroupPurchaseListVO getPurOne(int no); // 메인_단건조회

	// GroupPurchaseListVO getPurCount(String sttlSt); // 주문자 수 카운트

	// ++++++++++++++++++++++++++++++++++++++++++++++마이페이지
	// 마이페이지 - 공동구매
	List<GroupPurchaseListVO> getPurchaseList(); // 공동구매 전체리스트 조회
	// 마이페이지 - 리스트에서 구매상세로

	GroupPurchaseSettlementVO purchaseSelect(String userId); // 공동구매 상세내역 단건조회
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	//관리자
			//공동구매 물품 리스트
			List<GroupPurchaseListVO> getAdminGroupPurchaseList(GroupPurchaseSearchVO svo);
			int getCountTotal(GroupPurchaseSearchVO svo); 
			//공동구매 물품 등록하기
			int adminGPInsert(GroupPurchaseListVO vo);
			int adminGPImInsert(ImageVO evo);
}
