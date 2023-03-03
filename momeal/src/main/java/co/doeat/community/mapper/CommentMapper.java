package co.doeat.community.mapper;

import co.doeat.community.service.CommentVO;

public interface CommentMapper {
	public CommentVO commentsList(CommentVO vo); // 댓글 조회
}
