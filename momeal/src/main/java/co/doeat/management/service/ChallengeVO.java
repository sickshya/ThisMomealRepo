package co.doeat.management.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.doeat.common.service.ImageVO;
import lombok.Data;

@Data
public class ChallengeVO extends ImageVO {
	private int no; // 챌린지 게시글 번호
	private String title; // 챌린지 제목
	private String subject; // 챌린지 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
	private Date chalStartDate; // 챌린지 시작일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
	private Date chalEndDate; // 챌린지 종료일자
	private int chalRound; // 챌린지 진행회차(같은 챌린지 반복진행시 사용)
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

	private int totalPoint; // 회원의 현재 포인트
	private String nickName; // 회원 닉네임
	private String userId; // 회원 아이디
	private String codeId; // 챌린지 성공, 실패 여부 코드
	private int rewardPoint; // 챌린지 성공시 획득하는 리워드 포인트
	private int percent; // 챌린지 성공 확률(절상)
	private int totalDay; // 챌린지 총 진행일수
	private int success; // 챌린지 인증 성공 횟수
	private int reviewCheck; // 리뷰 작성 여부 판단(리뷰 있으면 = 1 / 없으면 = 0)
	
	private String atchPath; // 본문 첨부파일 이미지 주소
	private int sumPoint; // 챌린지에서 모인 포인트
	private String startDay; // 시작까지 남은 날짜
	private int likeNo; // 좋아요 유무 체크 (좋아요 한 상태 = 해당 좋아요 번호 / 아니면 = 0)
	private int attendNo; // 챌린지 참여 유무 체크 (참여한 상태 = 1 / 아니면 = 0)
	
	private String result; // 챌린지 참여신청 결과 확인

	private List<ImageVO> imgPath; // 관리자 상세조회 시, 이미지 경로값
	private String chstatus; // 날짜에 따른 공동구매 진행상태

	
	// 검색 필터
	private int rn; // rownum
	private String type; // 검색 타입
	private String keyword; // 검색 내용
}
