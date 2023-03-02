package co.doeat.management.service;

import lombok.Data;

@Data
public class LikesVO {
	private int no; // 좋아요 번호
	private int postNo; // 글번호
	private String userId; // 유저 아이디
	private String type; // 카테고리 구분자
}
