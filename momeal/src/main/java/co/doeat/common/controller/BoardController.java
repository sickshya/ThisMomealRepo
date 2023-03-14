package co.doeat.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.service.BoardService;
import co.doeat.common.service.BoardVO;


@Controller
public class BoardController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Autowired
	BoardService boardService;

	@Autowired
	ServletContext servletContext;

	// 자주묻는질문 FAQ

	// Faq user가 보는 전체리스트
	@RequestMapping("/faq")
	public String faq(Model model) {
		model.addAttribute("faqAllList", boardService.faqList());
		return "board/faq";
	}

	// Faq 전체리스트 보기
	@RequestMapping("/admin/adminFaq")
	public String adminFaq(Model model) {
		model.addAttribute("adminFaqList", boardService.faqList());
		return "admin/adminFaq";
	}

	// Faq 추가
	@RequestMapping("/admin/adminFaqInsertForm")
	public String farInsertForm() {
		return "admin/adminFaqInsertForm";
	}

	// Faq 추가
	@RequestMapping("/admin/adminFaqInsert")
	public String adminFaqInsert(BoardVO vo, Model model) {
		vo.setBoardCode("BD02");

		boardService.faqInsert(vo);

		return "redirect:/admin/adminFaq";
	}

	// Faq 수정
	@RequestMapping("/admin/adminFaqUpdateForm/{no}")
	public String adminFaqUpdateForm(@PathVariable int no, Model model, BoardVO vo) {

		vo.setNo(no);
		vo.setBoardCode("BD02");

		model.addAttribute("faqUpdate", boardService.faqSelect(vo));

		return "admin/adminFaqUpdateForm";
	}

	// Faq 수정
	@RequestMapping("/admin/adminFaqUpdate")
	public String adminFaqUpdate(BoardVO vo) {

		vo.setBoardCode("BD02");

		boardService.faqUpdate(vo);

		return "redirect:/admin/adminFaq";
	}

	// Faq 삭제
	@RequestMapping("/admin/adminFaqDelete/{no}")
	public String adminFaqDelete(@PathVariable int no, Model model, BoardVO vo) {
		String boardCode = "BD02";
		no = vo.getNo();

		boardService.faqDelete(vo);
		return "redirect:/admin/adminFaq";
	}

	// 공지사항 NOTICE

	// 전체조회
	@RequestMapping("/notice")
	public String noticeList(Model model) {
		model.addAttribute("noticeList", boardService.noticeList());
		return "board/noticeList";
	}

	// 단건조회
	@PostMapping("/noticeSelect")
	public String noticeSelect(BoardVO vo, Model model) {
		boardService.noticeHitUpdate(vo.getUserId());
		model.addAttribute("notice", boardService.noticeSelect(vo));
		return "board/noticeSelect";
	}

	// 글쓰기 폼 호출
	@RequestMapping("/noticeInsertForm")
	public String noticeInsertForm() {
		return "board/noticeInsertForm";
	}

	// 글 등록
	@PostMapping("/noticeInsert")
	public String noticeInsert(BoardVO vo, MultipartFile file) {

//			// 파일 저장
//			String saveFolder = servletContext.getRealPath("/resources/upload/"); // 파일 저장 위치(물리적인 저장 위치)
//			
//			if(!file.isEmpty()) { // 첨부파일이 존재하면
//				// 동일한 파일명이면 파일이 덮어 씌워지는것을 막기 위해 UUID 사용 (유니크한 ID를 만들기 위해)
//				String fileName = UUID.randomUUID().toString();
//				fileName =  "_" + fileName + file.getOriginalFilename(); // 나중에 파일명만 자르기 쉽게 언더바를 넣어줌
//				
//				File uploadFile = new File(saveFolder, fileName); // (파일저장폴더명, 파일이름)
//				
//				try {
//					file.transferTo(uploadFile); // 파일 저장하기
//				}catch(Exception e) {
//					e.printStackTrace();
//				}
//				// vo에 파일 담기
//				vo.setNoticeFile(file.getOriginalFilename()); // 원본 파일 명
//				// 경로는 알고 있으니까 저장할때 fileName만 저장해도 됨! 그러면 불러올 때 맞춰둔 경로를 다 적어준 후, DB에서 파일 이름만 가져와 뿌려주면 됨
//				vo.setNoticeFileDir(saveFolder + fileName); // 물리적 파일 저장 위치 (파일 불러올때를 위해 이렇게 저장)
//			}
//			
		// DB 저장
		boardService.noticeInsert(vo);

		return "redirect:notice";
	}
	
	// 글 수정 폼 호출
	@PostMapping("/noticeEditForm")
	public String noticeEditForm(BoardVO vo, Model model) { 
		model.addAttribute("notice", boardService.noticeSelect(vo)); 
		return "board/noticeEditForm";
	}
	
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(@ModelAttribute("notice") BoardVO vo, Model model) {

		String viewPage = null;
		int n = boardService.noticeUpdate(vo);
		
		if(n != 0) {
			model.addAttribute("message", "정상적으로 수정되었습니다");
			viewPage = "board/noticeUpdate";
		}else {
			model.addAttribute("message", "수정이 정상적으로 처리되지 못했습니다");
			viewPage = "board/noticeMessage";
		}
		return viewPage; 
	}
	
	// 글 삭제 처리
	@PostMapping("/noticeDelete")
	public void noticeDelete(BoardVO vo, HttpServletResponse response) {
		// DB에서만 지워지고 물리적 경로에 이미지 파일은 그대로 남아있음..! 서버에 남아있는 file 지우기
//		if(vo.getNoticeFileDir() != null) {
//			File file = new File(vo.getNoticeFileDir());
//			boolean result = file.delete();
//			System.out.println("삭제 확인 " + result);
//		}
//		
		// DB에서 삭제
		int n = boardService.noticeDelete(vo);
		
		try {
			if (n != 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('글이 정상적으로 삭제되었습니다.');location.href='notice';");
				out.println("</script>");

				out.flush();

			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('글이 정상적으로 삭제되지 않았습니다.');location.href='notice';");
				out.println("</script>");

				out.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
