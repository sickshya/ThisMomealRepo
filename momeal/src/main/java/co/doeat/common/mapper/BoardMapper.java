package co.doeat.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import co.doeat.Paging;
import co.doeat.common.service.BoardSearchVO;
import co.doeat.common.service.BoardVO;
import co.doeat.community.service.UserSearchVO;
import co.doeat.community.service.UsersVO;
import co.doeat.management.service.GroupPurchaseSearchVO;

public interface BoardMapper {
	public String formLoginpage();

	List<BoardVO> userFaq(); // 유저 faq 리스트

	List<BoardVO> faqList(BoardSearchVO svo); // 관리자 faq 리스트

	int cntTotal(BoardSearchVO svo); // 공지사항 전체 수 계산

	BoardVO faqSelect(BoardVO vo); // faq 상세

	int faqInsert(BoardVO vo); // faq 추가

	int faqUpdate(BoardVO vo);// faq 수정

	int faqDelete(BoardVO vo); // faq 삭제

	// NOTICE
	List<BoardVO> userNotice(); // 전체 조회

	List<BoardVO> noticeList(BoardSearchVO svo); // 전체 조회

	BoardVO noticeSelect(int no); // 단건조회

	int noticeInsert(BoardVO vo); // 등록

	int noticeUpdate(BoardVO vo); // 수정

	int noticeDelete(BoardVO vo); // 삭제

	List<BoardVO> noticeSearch(@Param("key") String key, @Param("val") String val); // 검색

	void noticeHitUpdate(String userId); // 조회수 증가

	int getCountTotal(Paging paging, String userId); // 페이징

	List<BoardVO> getnoticeUserList(BoardVO bvo); // 페이징하는 목록

}
