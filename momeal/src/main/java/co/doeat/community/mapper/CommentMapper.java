package co.doeat.community.mapper;

import java.util.List;

import co.doeat.community.service.CommentVO;

public interface CommentMapper {
	public List<CommentVO> commentsList(CommentVO vo); // 댓글 조회
	public void commentInsert(CommentVO vo); // 댓글 등록
	public void replyInsert(CommentVO vo); // 대댓글 등록
	public void commentUpdate(CommentVO vo); // 댓글 수정
	public void commentDelete(int no); // 댓글 삭제
}
