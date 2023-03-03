package co.doeat.community.service;

import lombok.Data;

@Data
public class GroupsVO {
	private int no;
	private String grpLeader;
	private String grpName;
	private String grpStatus;
	private String grpCode;
	
	private String userId;
	
	private int grpOrder;
}
