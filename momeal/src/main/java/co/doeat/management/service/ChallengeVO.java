package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChallengeVO {
	private int no; // 챌린지 게시글 번호
	private String title; // 챌린지 제목
	private String subject; // 챌린지 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date chalStartDate; // 챌린지 시작일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date chalEndDate; // 챌린지 종료일자
	private int round; // 챌린지 진행회차(같은 챌린지 반복진행시 사용)
	private int particir; // 현재 참여자 수
	private int sumParticir; // 누적 참여자 수
	private int deductPoint; // 참여 포인트
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vldStartTime; // 인증 시작 시간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vldEndTime; // 인증 종료 시간
	private String vldPosbDate; // 인증 가능 요일
	private String thumbnailImg; // 썸네일 이미지 이름
	private String thumbnailImgPath; // 썸네일 이미지 주소
	
	
	private String atchPath; // 본문 첨부파일 이미지 주소
	private int totalPoint; // 회원의 현재 포인트
	
	private int sumPoint; // 챌린지에서 모인 포인트
	private String startDay; // 시작까지 남은 날짜
}
