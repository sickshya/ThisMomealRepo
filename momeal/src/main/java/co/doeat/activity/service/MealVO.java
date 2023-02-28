package co.doeat.activity.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MealVO {
	private int no;
	private String userId;
	private String subject;
	private String img;
	private String fileDir;
	private int mealScore;
	private String typeCode;
	private String mealTime;
	private String mealAmount;
	@DateTimeFormat
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date postDate;
	

}
