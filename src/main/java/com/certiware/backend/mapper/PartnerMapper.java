package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.partner.SelectDetailModel;
import com.certiware.backend.model.partner.SelectListModel;

public interface PartnerMapper {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT A.PARTNERID, A.PARTNERNAME, A.BUSINESSNUMBER, A.BUSINESSCODE, B.DESCRIPTION AS BUSINESSDESCRIPTION, "
			+ "        A.PARTNERCODE, C.DESCRIPTION AS PARTNERDESCRIPTION, A.CREATEDATE "
			+ " FROM TB_PARTNER A, TB_BUSINESSCODE B, TB_PARTNERCODE C "
			+ " WHERE A.BUSINESSCODE = B.BUSINESSCODE "
			+ " AND A.PARTNERCODE = C.PARTNERCODE "
			)
	public List<SelectListModel> selectList() throws Exception;
	
	/**
	 * 
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_PARTNER "
			+ "WHERE PARTNERID = #{param1}")
	public SelectDetailModel selectPartnerByPartnerId(int partnerId) throws Exception;
	
	/**
	 * 
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_PARTNER (PARTNERNAME, PARTNERCODE, BUSINESSNUMBER, BUSINESSCODE)" 
			+ "VALUES (#{partnerName}, "
			+ "#{partnerCode},  "
			+ "#{businessNumber}, "
			+ "#{businessCode}"
			)
	@Options(useGeneratedKeys = true, keyProperty="partnerId")
	public PartnerModel insertPartner(PartnerModel partnerModel) throws Exception;
	
	
	/**
	 * 
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PARTNER "
			+ " SET PARTNERNAME=#{partnerName}, "
			+ " PARTNERCODE=#{partnerCode},"
			+ " BUSINESSNUMBER=#{businessNumber},"
			+ " BUSINESSCODE=#{businessCode} "
			+ " WHERE PARTNERID = #{partnerId} ")
	public int updatePartnerByPartnerId(PartnerModel partnerModel) throws Exception;
	
	/**
	 * 
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	@Delete(  " DELETE "
			+ " FROM TB_PARTNER "
			+ " WHERE PARTNERID = #{param1}")
	public int deletePartnerByPartnerId(int partnerId) throws Exception;
	
	

}
