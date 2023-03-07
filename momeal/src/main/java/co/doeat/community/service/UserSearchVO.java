package co.doeat.community.service;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserSearchVO extends UsersVO {
	int first;
	int last;
	String keyword;
	String typeArr;
	String type;
}
