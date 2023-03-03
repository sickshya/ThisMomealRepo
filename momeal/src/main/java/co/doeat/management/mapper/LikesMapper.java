package co.doeat.management.mapper;

import java.util.List;
import java.util.Map;

import co.doeat.management.service.LikesVO;

public interface LikesMapper {

	// 마이페이지 - 식단 좋아요 추가
	//public int insertLikeMeal(LikesVO vo);

	// 마이페이지 - 식단 좋아요 리스트 출력
	public List<Map<String, Object>> myLikeList(String userId);

	// 마이페이지 - 식단 좋아요 누른 게시글 상세내용
	public Map<String, Object> mylikeSelect(LikesVO vo);

	// +++++++++++++++++++++++++++++++++++++++++++++++++++
	// 마이페이지 - 챌린지 좋아요 추가
	//public int insertLikeChall(LikesVO vo);

	// 마이페이지 - 챌린지 좋아요 리스트 출력
	public List<Map<String, Object>> myChallList(String userId);

	// 마이페이지 - 좋아요 삭제
	public int myLikeDel(LikesVO vo);
}
