package co.doeat.activity.service;

import java.util.List;

public interface MealService {
	List<MealVO> myFeedList(String postDate);

	int insertMeal(MealVO vo);

	// 그룹 상세(글 하나, 이미지 다수)
	List<MealVO> mealList(MealVO vo);
}
