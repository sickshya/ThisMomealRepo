package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeParticipationVO {
	private String userId;
	private int no;
	private int round;
	private String codeId;
	
	private String USER_ID; // selectKey 넘어오는지 테스트중
}
