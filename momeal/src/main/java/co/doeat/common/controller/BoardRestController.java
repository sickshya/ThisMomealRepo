package co.doeat.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.doeat.common.service.BoardService;

@RestController
public class BoardRestController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/AjaxSearchList.do", produces = "application/json;charset=UTF-8")
	
	public String ajaxSearchList(
			@RequestParam("key") String key,
			@RequestParam("val") String val) {
		
		String str = null;
		ObjectMapper json = new ObjectMapper();
		try {
			str = json.writeValueAsString(boardService.noticeSearch(key, val));
		} catch (com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return str;
		
	}
			
}
