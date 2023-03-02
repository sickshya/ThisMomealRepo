package co.doeat.management.service;

import java.util.List;
import java.util.Map;

public interface LikesService {
	
	// 커뮤니티 좋아요 추가
	//public 

	// 마이페이지 - 좋아요 리스트 출력
	public List<Map<String, Object>> myLikeList();

	// 마이페이지 - 좋아요 누른 게시글 상세내용
	public Map<String, Object> mylikeSelect(int postNo);

	// 마이페이지 - 챌린지 좋아요 리스트 출력
	public List<Map<String, Object>> myChallList();

	// 마이페이지 - 좋아요 삭제
	public int myLikeDel(LikesVO vo);

}
