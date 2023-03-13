package co.doeat.common.mapper;

import java.util.List;

import co.doeat.common.service.BoardVO;

public interface BoardMapper {
	public String formLoginpage();
	
	List<BoardVO> faqList(); // faq 리스트

	BoardVO faqSelect(BoardVO vo); // faq 상세

	int faqInsert(BoardVO vo); // faq 추가

	int faqUpdate(BoardVO vo);// faq 수정

	int faqDelete(BoardVO vo); // faq 삭제
}
