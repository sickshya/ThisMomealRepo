package co.doeat.activity.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;

@RestController
public class MealRestController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;
	@Value("${momeal.saveImg}")
	private String saveImg;

	// 날짜에 따른 피드 불러오기
	@RequestMapping("/myFeed/{postDate}")
	public List<MealVO> myFeedList(@PathVariable String postDate) {
		return mealService.myFeedList(postDate);
	}
	
	//상세조회
	@RequestMapping("/mealSelect/{no}")
	public MealVO mealSelect(@PathVariable int no, MealVO vo, Model model) {
		//model.addAttribute("selects", mealService.mealSelect(no));
		return mealService.mealSelect(no);
	}
	
	@RequestMapping("/insertMeal")
	public String mealInsert(MealVO vo, MultipartFile file) {
		if (!file.isEmpty()) {// 첨부파일이 존재하면
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + file.getOriginalFilename();
			File uploadFile = new File(saveImg, fileName);
			try {
				file.transferTo(uploadFile); // 파일저장하긴
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setImg(file.getOriginalFilename());// 원본파일명
			vo.setFileDir("/mm_images/" + fileName);// 디렉토리 포함 원본파일
		}
		mealService.insertMeal(vo);//db 저장 
		return "true";
	}

}
