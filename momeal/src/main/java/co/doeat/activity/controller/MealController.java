package co.doeat.activity.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.doeat.activity.mapper.FollowMapper;
import co.doeat.activity.service.FollowService;
import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;
import co.doeat.community.service.UserService;
import co.doeat.community.service.UsersVO;
import groovyjarjarantlr4.v4.codegen.model.ModelElement;

@Controller
public class MealController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;
	@Autowired
	FollowService followservice;
	@Value("${momeal.saveImg}")
	private String saveImg;
	@Autowired 
	UserService userService;



	@GetMapping("/myFeed/{userId}")
	   public String myFeed(Model model, @PathVariable String userId) {
	      model.addAttribute("selects", userService.myFeedUserSelect(userId));
	      model.addAttribute("follow", followservice.followCount(userId));
	      model.addAttribute("mealcnt", mealService.postList(userId));
	      model.addAttribute("mealavg", mealService.mealAvg(userId));
	     
	      System.out.println("가져왔나요 ======" + followservice.followCount(userId));
	      return "myFeed/myFeed";
	   }

	@GetMapping("/adminChart")
	public String adminChart() {
		return "admin/adminChart";
	}

	@GetMapping("/attendance")
	public String attendance() {
		return "myFeed/attendance";
	}

	@GetMapping("/feedUpload")
	public String feedUpload() {
		return "myFeed/feedUpload";
	}
	
	@RequestMapping("/feedUpdateForm/{no}")
	public String feedUpdateForm(@PathVariable int no, Model model ) {
		model.addAttribute("updates", mealService.mealSelect(no));
		return "myFeed/feedUpdateForm";
	}
	
}
