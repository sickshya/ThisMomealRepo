package co.doeat.activity.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;

@Controller
public class MealController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;
	@Value("${momeal.saveImg}")
	private String saveImg;


	@GetMapping("/myFeed")
	public String myFeed() {
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

	
	

	
	//식단 단건조회
	

}
