package co.doeat.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.service.ImageVO;
import co.doeat.community.service.UsersVO;

public interface GroupPurchaseService {

	// 공동구매-메인
	public List<GroupPurchaseListVO> pchAllList(); // 전체조회

	public List<GroupPurchaseListVO> pchIngList(); // 진행중조회

	// 공동구매-상세
	public List<GroupPurchaseListVO> pchDetail(int no); // 단건조회

	// 공동구매-주문하기 form
	public int payInsert(GroupPurchaseSettlementVO pvo); // 배송정보 저장

	// 공동구매 참여내역 조회
	public List<GroupPurchaseSettlementVO> pchOrderList(int no); // 공구 참여내역 조회

	
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

	// 공동구매 update
	int adminGPUpdate(GroupPurchaseListVO vo);

	

}
