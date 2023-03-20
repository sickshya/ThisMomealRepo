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
	
	// 그룹 댓글 조회
	@Override
	public List<CommentVO> groupCommentsList(CommentVO vo) {
		return commentMapper.groupCommentsList(vo);
	}

	// 댓글 등록
	@Override
	public void commentInsert(CommentVO vo) {
		commentMapper.commentInsert(vo);
	}
	
	// 대댓글 등록
	@Override
	public void replyInsert(CommentVO vo) {
		commentMapper.replyInsert(vo);
	}
	
	// 댓글 수정
	@Override
	public void commentUpdate(CommentVO vo) {
		commentMapper.commentUpdate(vo);
	}

	// 댓글 삭제
	@Override
	public void commentDelete(int no) {
		commentMapper.commentDelete(no);
	}

	// 게시물 삭제 시 해당 게시물의 댓글 전체삭제
	@Override
	public void commentAllDelete(int no) {
		commentMapper.commentAllDelete(no);
	}

}
