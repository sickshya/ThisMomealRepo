package co.doeat.management.service;

import java.util.List;
import java.util.Map;

public interface LikesService {

	// 마이페이지 - 좋아요 리스트 출력
	public List<Map<String, Object>> myLikeList(); // 공동구매 전체리스트 조회

	// 마이페이지 - 좋아요 누른 게시글 상세내용
	public Map<String, Object> mylikeSelect(String userId); // 공동구매 상세내역 단건조회

}
