package co.doeat.activity.service;

import java.util.List;

public interface MealService {
	List<MealVO> myFeedList(String postDate);

	int insertMeal(MealVO vo);

}
