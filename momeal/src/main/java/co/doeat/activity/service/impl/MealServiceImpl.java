package co.doeat.activity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.activity.mapper.MealMapper;
import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;



@Service
public class MealServiceImpl implements MealService{
	@Autowired
	MealMapper mealMapper;

	@Override
	public int insertMeal(MealVO vo) {
		return mealMapper.insertMeal(vo);
	}

	@Override
	public List<MealVO> myFeedList(String postDate) {
		return mealMapper.myFeedList(postDate);
	}

	@Override
	public List<MealVO> mealList(MealVO vo) {
		return mealMapper.mealList(vo);
	}



	
	
	
	
	
	
}
