package co.doeat.community.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentVO {
	private int no; // 댓글 전체 순번
	private String userId; // 회원 아이디
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date writeDate; // 작성일자
	private String subject; // 댓글 내용
	private int cmmtClass; // 계층(댓글 = 0, 대댓글 = 1)
	private int cmmtOrder; // 한 댓글 묶음에서의 순서
	private int cmmtCode; // 댓글 묶음을 구분짓는 순서
	private int postNo; // 커뮤니티 게시글 번호
	private String boardCode; // 게시글 타입 (식단 = CT04, 그룹 = CT05)
	
	private String nickName; // 댓글 작성자 닉네임
}
