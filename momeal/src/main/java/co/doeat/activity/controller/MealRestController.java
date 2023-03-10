package co.doeat.activity.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.activity.service.MealService;
import co.doeat.activity.service.MealVO;
import co.doeat.management.service.GroupPurchaseListVO;

@RestController
public class MealRestController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	MealService mealService;
	@Value("${momeal.saveImg}")
	private String saveImg;

	// 날짜에 따른 피드 불러오기
	@RequestMapping("/myFeed/{userId}/{postDate}")
	public List<MealVO> myFeedList(@PathVariable String postDate, @PathVariable String userId) {
		return mealService.myFeedList(postDate,userId);
	}
	
	//상세조회
	@RequestMapping("/mealSelect/{no}")
	public MealVO mealSelect(@PathVariable int no, MealVO vo, Model model) {
		//model.addAttribute("selects", mealService.mealSelect(no));
		return mealService.mealSelect(no);
	}
	
	@RequestMapping("/insertMeal")
	public String mealInsert(MealVO vo, MultipartFile file, HttpSession session) {
		vo.setUserId((String) session.getAttribute("userId"));
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

	
	//식단 삭제
	@RequestMapping("/mealDelete/{no}")
	public int mealDelete(@PathVariable int no, GroupPurchaseListVO vo) {
		return mealService.mealDelete(no);
		
	}
	
}
