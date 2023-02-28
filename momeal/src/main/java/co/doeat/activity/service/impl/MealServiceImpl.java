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
		// TODO Auto-generated method stub
		return mealMapper.insertMeal(vo);
	}

	@Override
	public List<MealVO> myFeedList() {
		// TODO Auto-generated method stub
		return mealMapper.myFeedList();
	}



	
	
	
	
	
	
}
