package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChallengeVO {
	private int no;
	private String title;
	private String subject;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chalStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date chalEndDate;
	private int round;
	private int particir;
	private int sumParticir;
	private int savePoint;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vldStartTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date vldEndTime;
	private String  vldPosbDate;
	
	private String atchPath;
	private int totalPoint;
	private String startDay;
}
