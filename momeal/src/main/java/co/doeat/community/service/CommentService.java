package co.doeat.community.service;

import java.util.List;

public interface CommentService {
	public List<CommentVO> commentsList(CommentVO vo); // 댓글 조회
}
