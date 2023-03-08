package co.doeat.activity.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;

@RestController
public class MealRestController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;

	// 날짜에 따른 피드 불러오기
	@RequestMapping("/myFeed/{postDate}")
	public List<MealVO> myFeedList(@PathVariable String postDate) {
		return mealService.myFeedList(postDate);
	}
}
