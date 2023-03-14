package co.doeat.common.service;

import java.util.List;

public interface BoardService {

	List<BoardVO> faqList(); // faq 리스트
	
	BoardVO faqSelect(BoardVO vo); // faq 상세

	int faqInsert(BoardVO vo); // faq 추가

	int faqUpdate(BoardVO vo);// faq 수정

	int faqDelete(BoardVO vo); // faq 삭제
	
	
	// NOTICE
	List<BoardVO> noticeList(); // 전체 조회
	
	BoardVO noticeSelect(BoardVO vo); // 단건조회
	
	int noticeInsert(BoardVO vo); // 등록
	
	int noticeUpdate(BoardVO vo); // 수정
	
	int noticeDelete(BoardVO vo); // 삭제
	
	List<BoardVO> noticeSearch(String key, String val); // 검색
	
	void noticeHitUpdate(String userId); // 조회수 증가

}
