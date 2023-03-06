package co.doeat.community.service;

import lombok.Data;

@Data
public class UserSearchVO extends UsersVO {
	int first;
	int last;
	String keyword;

}
