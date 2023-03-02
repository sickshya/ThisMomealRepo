package co.doeat.management.service;

import java.util.List;
import java.util.Map;

public interface GroupPurchaseService {

	List<GroupPurchaseListVO> getPurList(); // 전체조회

	List<GroupPurchaseListVO> getPurchasingList(); // 진행중조회

	GroupPurchaseListVO getPurOne(int no); // 메인_단건조회

	// ++++++++++++++++++++++++++++++++++++++++++++++마이페이지
	// 마이페이지 - 공동구매
	List<GroupPurchaseListVO> getPurchaseList(); // 공동구매 전체리스트 조회
	
	// 마이페이지 - 리스트에서 구매상세로
	public Map<String, Object> purchaseSelect(int prdtNo); // 공동구매 상세내역 단건조회

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++관리자
	// 관리자
	// 공동구매 물품 리스트
	List<GroupPurchaseListVO> getAdminGroupPurchaseList(GroupPurchaseSearchVO svo);

	int getCountTotal(GroupPurchaseSearchVO svo);
}
