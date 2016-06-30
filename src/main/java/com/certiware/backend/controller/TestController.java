package com.certiware.backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.service.TestService;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value="/excelDownload" , method=RequestMethod.GET)
	public List<TestExcelModel> excelDownload() throws ServletException{
		
		System.out.println("excelDownload() start..");
		
		List<TestExcelModel> excelModels = null;
		List<SelectListModel> selectListModels = new ArrayList<>();
		SelectListModel selectListModel = new SelectListModel();
		
		try {
			excelModels = testService.selectExcel();
			selectListModels = testService.selectList(selectListModel);
		}catch(Exception e){
			System.out.println(e.toString());
			throw new ServletException(e.toString());
		}
		
		return excelModels;
		
	}

}
