package co.doeat.community.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
