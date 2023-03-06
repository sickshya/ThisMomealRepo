package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.community.service.UsersVO;
import co.doeat.management.mapper.GroupPurchaseMapper;
import co.doeat.management.service.GroupPurchaseListVO;
import co.doeat.management.service.GroupPurchaseSearchVO;
import co.doeat.management.service.GroupPurchaseService;
import co.doeat.management.service.GroupPurchaseSettlementVO;

@Service
public class GroupPurchaseServiceImpl implements GroupPurchaseService {

	@Autowired
	private GroupPurchaseMapper groupPurchaseMapper;

	// 전체보기
	@Override
	public List<GroupPurchaseListVO> getPurList() {

		return groupPurchaseMapper.getPurList();
	}

	// 진행중
	@Override
	public List<GroupPurchaseListVO> getPurchasingList() {

		return groupPurchaseMapper.getPurchasingList();
	}

	// 단건조회
	@Override
	public List<GroupPurchaseListVO> getPurOne(int no) {
		return groupPurchaseMapper.getPurOne(no);
	}

	@Override
	public List<GroupPurchaseSettlementVO> payInsert(GroupPurchaseSettlementVO pvo) {
		return groupPurchaseMapper.payInsert(pvo);
	}

//	@Override
//	public int kakaoPay(GroupPurchaseSettlementVO vo) {
//		
//		return groupPurchaseMapper.kakaoPay(vo);
//	}

	//공동구매 결제하기
	@Override
	public int attendPurchase(UsersVO vo) {
		return groupPurchaseMapper.attendPurchase(vo);
	}

	// +++++++++++++++++++++++++++마이페이지

	@Override
	public List<GroupPurchaseListVO> getPurchaseList(String userId) {
		return groupPurchaseMapper.getPurchaseList(userId);
	}

	@Override
	public Map<String, Object> purchaseSelect(String userId, int no) {
		return groupPurchaseMapper.purchaseSelect(userId, no);
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++관리자
	@Override
	public List<GroupPurchaseListVO> getAdminGroupPurchaseList(GroupPurchaseSearchVO svo) {
		return groupPurchaseMapper.getAdminGroupPurchaseList(svo);
	}

	@Override
	public int getCountTotal(GroupPurchaseSearchVO svo) {
		return groupPurchaseMapper.getCountTotal(svo);
	}

	@Override
	public int adminGPInsert(GroupPurchaseListVO vo) {
		groupPurchaseMapper.adminGPInsert(vo);
		return vo.getNo();
	}

	@Override
	public GroupPurchaseListVO adminGPSelect(int no) {
		return groupPurchaseMapper.adminGPSelect(no);
	}

	@Override
	public int adminGPDelete(int no) {
		return groupPurchaseMapper.adminGPDelete(no);
	}

	@Override
	public int adminGPUpdate(GroupPurchaseListVO vo) {
		return groupPurchaseMapper.adminGPUpdate(vo);
	}

}
