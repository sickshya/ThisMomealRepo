package co.doeat.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.community.mapper.CommentMapper;
import co.doeat.community.service.CommentService;
import co.doeat.community.service.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentMapper commentMapper;

	// 댓글 조회
	@Override
	public List<CommentVO> commentsList(CommentVO vo) {
		return commentMapper.commentsList(vo);
	}

	// 댓글 등록
	@Override
	public void commentInsert(CommentVO vo) {
		commentMapper.commentInsert(vo);
	}
	
}
