package co.doeat.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.common.mapper.BoardMapper;
import co.doeat.common.service.BoardService;
import co.doeat.common.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	// FAQ

	@Override
	public List<BoardVO> faqList() {
		return boardMapper.faqList();
	}

	@Override
	public int faqInsert(BoardVO vo) {
		return boardMapper.faqInsert(vo);
	}

	@Override
	public int faqUpdate(BoardVO vo) {
		return boardMapper.faqUpdate(vo);
	}

	@Override
	public int faqDelete(BoardVO vo) {
		return boardMapper.faqDelete(vo);
	}

	@Override
	public BoardVO faqSelect(BoardVO vo) {
		return boardMapper.faqSelect(vo);
	}
	
	// NOTICE

	// 전체조회
	@Override
	public List<BoardVO> noticeList() {
		return boardMapper.noticeList();
	}

	// 검색기능
	@Override
	public List<BoardVO> noticeSearch(String key, String val) {
		return boardMapper.noticeSearch(key, val);
	}

	// 단건조회
	@Override
	public BoardVO noticeSelect(int no) {
		return boardMapper.noticeSelect(no);
	}

	@Override //보류
	public void noticeHitUpdate(String userId) {
		return;		
	}

	// 등록
	@Override
	public int noticeInsert(BoardVO vo) {
		return boardMapper.noticeInsert(vo);
	}

	// 수정
	@Override
	public int noticeUpdate(BoardVO vo) {
		return boardMapper.noticeUpdate(vo);
	}

	// 삭제
	@Override
	public int noticeDelete(BoardVO vo) {
		return boardMapper.noticeDelete(vo);
	}
	
	

}
