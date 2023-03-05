package co.doeat.community.mapper;

import java.util.List;

import co.doeat.community.service.CommentVO;

public interface CommentMapper {
	public List<CommentVO> commentsList(CommentVO vo); // 댓글 조회
}
