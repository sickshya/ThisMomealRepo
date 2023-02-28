package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeSearchVO extends ChallengeVO {
	private int first;
	private int last;
	private String keyword;
}
