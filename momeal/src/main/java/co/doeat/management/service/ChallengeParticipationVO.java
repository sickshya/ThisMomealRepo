package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeParticipationVO {
	private String userId;
	private int no;
	private int round;
	private String codeId;
}
