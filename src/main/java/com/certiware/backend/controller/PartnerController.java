package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.partner.SelectCodeModel;
import com.certiware.backend.model.partner.SelectDetailModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.service.PartnerService;

@RestController
@RequestMapping(value="/partner")
public class PartnerController {
	
	@Autowired
	PartnerService partnerService;
	
	@RequestMapping(value="/selectCode")
	public SelectCodeModel selectCode() throws ServletException{		
		
		System.out.println("selectCode() start...");	
		
		SelectCodeModel selectCodeModel = new SelectCodeModel();
		
		
		try{
			
			selectCodeModel = partnerService.selectCode(selectCodeModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("selectCode() end...");
		
		return selectCodeModel;
		
	}// end
	
	@RequestMapping(value="/selectList")
	public List<SelectListModel> selectList() throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListModel> selectListModels = null;
		
		
		try{
			
			selectListModels = partnerService.selectList();
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("selectList() end...");
		return selectListModels;
		
	}// end

	
	@RequestMapping(value="/selectDetail")
	public SelectDetailModel selectDetail(@RequestBody int partnerId) throws ServletException{
		
		System.out.println("selectDetail() start...");	
		
		SelectDetailModel selectDetailModel = new SelectDetailModel();
		
		try{
			
			selectDetailModel = partnerService.selectDetail(partnerId);
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("selectDetail() end...");
		return selectDetailModel;
		
	}// end
	
	@RequestMapping(value="/insert")
	public int insert(@RequestBody PartnerModel partnerModel) throws ServletException{
		
		System.out.println("insert() start...");
		int result=0;		
		
		try{
			
			result = partnerService.insertPartner(partnerModel);		
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("insert() end...");
		return result;
		
	}// end
	
	@RequestMapping(value="/update")
	public int update(@RequestBody PartnerModel partnerModel) throws ServletException{
		
		System.out.println("update() start...");	
		int result=0;
		
		
		try{
			
			result = partnerService.updatePartner(partnerModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("update() end...");
		return result;
		
	}// end
	
	@RequestMapping(value="/delete")
	public int delete(@RequestBody int partnerId) throws ServletException{
		
		System.out.println("delete() start...");
		int result=0;
		
		
		try{
			
			result = partnerService.deletePartner(partnerId);
			
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("delete() end...");
		return result;
		
	}// end





}
