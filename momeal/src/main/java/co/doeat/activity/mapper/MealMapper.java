package co.doeat.activity.mapper;

import java.util.List;

import co.doeat.activity.service.MealVO;

public interface MealMapper {
	List<MealVO> myFeedList();
	int insertMeal(MealVO vo);
	//MealVO mealSelect(MealVO vo);
	
}
