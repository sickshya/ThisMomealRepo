package co.doeat.community.service;

import java.util.List;

import co.doeat.activity.service.MealVO;
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

	private List<MealVO> mealsImg;

	private String profileImgPath;
	private String nickName;
}
