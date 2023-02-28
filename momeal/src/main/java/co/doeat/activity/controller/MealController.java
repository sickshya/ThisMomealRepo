package co.doeat.activity.controller;


import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;




@Controller
public class MealController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;
	
	
//	@GetMapping("/myFeed")
//	public String myFeed() {
//		return "myFeed/myFeed";
//	}
	


	@GetMapping("/adminChart")
	public String adminChart() {
		return "admin/adminChart";
	}
	
	
	@GetMapping("/attendance")
	public String attendance() {
		return "myFeed/attendance";
	}
	
	@GetMapping("/feedUpload.do")
	public String feedUpload() {
		return "myFeed/feedUpload";
	}
	
	@GetMapping("/mealInsert.do")
	public String mealInsert(MealVO vo, MultipartFile file) {
		String saveFolder = servletContext.getRealPath("/resources/upload/");
		if(!file.isEmpty()) {//첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + file.getOriginalFilename();
			File uploadFile = new File(saveFolder,fileName);
		try {
			file.transferTo(uploadFile); //파일저장하긴
		}catch(Exception e) {
			e.printStackTrace();
		}
		vo.setImg(file.getOriginalFilename());//원본파일명
		vo.setFileDir(saveFolder +fileName);//디렉토리 포함 원본파일
		}
		return "redirect:myFeed.do";
	}
	
	@RequestMapping("/myFeed")
	public String myFeedList(Model model) {
		model.addAttribute("meal", mealService.myFeedList());
		return "myFeed/myFeed";
		
	}
	
//	//단건조회
//	@GetMapping("/myFeed/{no}")
//	@ResponseBody
//	public MealVO mealSelect(Model model, @PathVariable int no) {
//		return mealService.mealSelect(no);
//	}
//	
	
}
