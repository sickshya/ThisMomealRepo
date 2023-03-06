package co.doeat.management.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.service.ImageVO;
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
	public List<GroupPurchaseSettlementVO> payList() {

		return groupPurchaseMapper.payList();
	}

	@Override
	public int payInsert(GroupPurchaseSettlementVO vo) {

		return groupPurchaseMapper.payInsert(vo);
	}

	// +++++++++++++++++++++++++++마이페이지

	@Override
	public List<GroupPurchaseListVO> getPurchaseList() {
		return groupPurchaseMapper.getPurchaseList();
	}

	@Override
	public Map<String, Object> purchaseSelect(int prdtNo) {
		return groupPurchaseMapper.purchaseSelect(prdtNo);
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
