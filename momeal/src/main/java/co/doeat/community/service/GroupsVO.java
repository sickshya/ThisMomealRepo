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
	private String postDate; // 안에서 TODATE 해서 String으로 넘겨주면 된당
}
