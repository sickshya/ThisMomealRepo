package co.doeat.activity.service;

import java.util.List;

public interface MealService {
	// 마이피드 식단 불러오기
	List<MealVO> myFeedList(String postDate,String userId);

	// 식단 등록
	int insertMeal(MealVO vo);

	// 식단 단건조회
	MealVO mealSelect(int no);

	// 그룹 상세(글 하나, 이미지 다수)
	List<MealVO> mealList(MealVO vo);
	
	//식단 삭제
	int mealDelete(int no);
	
	//식단 수정
	int updateMeal(MealVO vo);
}
