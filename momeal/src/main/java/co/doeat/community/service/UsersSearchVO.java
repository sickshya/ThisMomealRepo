package co.doeat.community.service;

import lombok.Data;

@Data
public class UsersSearchVO extends UsersVO {
	int first;
	int last;
	String keyword;

}
