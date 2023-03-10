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
	public List<GroupPurchaseListVO> pchAllList() {

		return groupPurchaseMapper.pchAllList();
	}

	// 진행중
	@Override
	public List<GroupPurchaseListVO> pchIngList() {

		return groupPurchaseMapper.pchIngList();
	}

	// 단건조회
	@Override
	public List<GroupPurchaseListVO> pchDetail(int no) {
		return groupPurchaseMapper.pchDetail(no);
	}

	// 공동구매 결제하기
	@Override
	public int payInsert(GroupPurchaseSettlementVO pvo) {
		return groupPurchaseMapper.payInsert(pvo);
	}

	
	// 공동구매 결제화면 전체조회
	@Override
	public List<GroupPurchaseSettlementVO> pchOrderList() {
		return groupPurchaseMapper.pchOrderList();
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
