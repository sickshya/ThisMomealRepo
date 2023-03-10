package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeSearchVO extends ChallengeVO {
	int first;
	int last;
	String keyword;
}
