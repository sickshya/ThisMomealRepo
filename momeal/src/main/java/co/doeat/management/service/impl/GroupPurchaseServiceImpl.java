package co.doeat.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.GroupPurchaseMapper;
import co.doeat.management.service.GroupPurchaseListVO;
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

	//진행중
	@Override
	public List<GroupPurchaseListVO> getPurchasingList() {
		
		return groupPurchaseMapper.getPurchasingList();
	}
	
	// 단건조회
	@Override
	public GroupPurchaseListVO getPurOne(int no) {
		return groupPurchaseMapper.getPurOne(no);
	}
	
	// +++++++++++++++++++++++++++마이페이지

	@Override
	public List<GroupPurchaseListVO> getPurchaseList() {
		return groupPurchaseMapper.getPurchaseList();
	}

	@Override
	public GroupPurchaseSettlementVO purchaseSelect(String userId) {
		return groupPurchaseMapper.purchaseSelect(userId);
	}

}
