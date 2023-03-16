package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeParticipationVO {
	private String userId;
	private int no;
	private int chalRound;
	private String codeId;
}
