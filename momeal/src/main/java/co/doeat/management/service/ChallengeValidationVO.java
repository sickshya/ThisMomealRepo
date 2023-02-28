package co.doeat.management.service;

import java.util.Date;

import lombok.Data;

@Data
public class ChallengeValidationVO {
	private String userId;
	private int no;
	private String chalImg;
	private Date vldDate;
}
