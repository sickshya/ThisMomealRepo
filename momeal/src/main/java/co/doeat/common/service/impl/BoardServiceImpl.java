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

	
}
