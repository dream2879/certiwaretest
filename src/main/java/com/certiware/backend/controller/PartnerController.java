package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.partner.SelectCodeModel;
import com.certiware.backend.model.partner.SelectDetailModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.service.PartnerService;

@RestController
@RequestMapping(value="/partner")
public class PartnerController {
	
	@Autowired
	PartnerService partnerService;
	
	/**
	 * 거래처 정보를 조회한다.(리스트)
	 * 조건에 따라 사용되는 쿼리문이 다르다.
	 * @param selectListModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectList")
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListModel> selectListModels = null;
		
		
		try{
			
			selectListModels = partnerService.selectList(selectListModel);
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}		
		
		System.out.println("selectList() end...");
		return selectListModels;
		
	}// end

	/**
	 * PK를 가지고 거래처 정보를 가져온다.
	 * @param partnerId
	 * @return
	 * @throws ServletException
	 */
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
	
	/**
	 * 거래처 정보를 입력한다.
	 * @param partnerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insert")
	public ResultModel insert(@RequestBody PartnerModel partnerModel) throws ServletException{
		
		System.out.println("insert() start...");
		ResultModel resultModel = new ResultModel();		
		
		try{
			
			resultModel.setResult(partnerService.insertPartner(partnerModel));	
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}		
		
		System.out.println("insert() end...");
		return resultModel;
		
	}// end
	
	/**
	 * 거래처 정보를 변경한다.
	 * @param partnerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/update")
	public ResultModel update(@RequestBody PartnerModel partnerModel) throws ServletException{
		
		System.out.println("update() start...");	
		ResultModel resultModel = new ResultModel();
		
		
		try{
			
			resultModel.setResult(partnerService.updatePartner(partnerModel));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}		
		
		System.out.println("update() end...");
		return resultModel;
		
	}// end
	
	
	/**
	 * 거래처 정보를 삭제한다.
	 * @param partnerId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/delete")
	public ResultModel delete(@RequestBody int partnerId) throws ServletException{
		
		System.out.println("delete() start...");
		ResultModel resultModel = new ResultModel();		
		
		try{
			
			resultModel.setResult(partnerService.deletePartner(partnerId));			
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}		
		
		System.out.println("delete() end...");
		return resultModel;
		
	}// end





}
