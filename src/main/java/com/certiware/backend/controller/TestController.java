package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.service.TestService;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value="/excelDownload")
	public List<TestExcelModel> excelDownload() throws ServletException{
		
		List<TestExcelModel> excelModels = null;
		
		try {
			excelModels = testService.selectExcel();
			
		}catch(Exception e){
			System.out.println(e.toString());
			throw new ServletException(e.toString());
		}
		
		return excelModels;
		
	}

}
