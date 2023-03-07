package co.doeat.activity.mapper;

import java.util.List;

import co.doeat.activity.service.MealVO;

public interface MealMapper {
	List<MealVO> myFeedList(String postDate);

	int insertMeal(MealVO vo);
	// MealVO mealSelect(MealVO vo);
	
	// 그룹 상세(글 하나, 이미지 다수)
	List<MealVO> mealList(MealVO vo);

}
