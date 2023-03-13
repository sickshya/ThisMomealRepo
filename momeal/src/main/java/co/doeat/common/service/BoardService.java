package co.doeat.common.service;

import java.util.List;

public interface BoardService {

	List<BoardVO> faqList(); // faq 리스트
	
	BoardVO faqSelect(BoardVO vo); // faq 상세

	int faqInsert(BoardVO vo); // faq 추가

	int faqUpdate(BoardVO vo);// faq 수정

	int faqDelete(BoardVO vo); // faq 삭제
	
	
	// NOTICE
	List<BoardVO> noticeList(); // 전체 리스트 
	
	List<BoardVO> noticeSearch(String key, String val); // 검색
}
