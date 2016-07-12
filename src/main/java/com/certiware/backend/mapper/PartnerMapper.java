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
	 * TB_PARTNER 테이블 조회(리스트)
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script> "
			+ "SELECT A.PARTNERID, A.PARTNERNAME, A.BUSINESSNUMBER, A.BUSINESSCODE, B.DESCRIPTION AS BUSINESSDESCRIPTION, "
			+ "        A.PARTNERCODE, C.DESCRIPTION AS PARTNERDESCRIPTION, A.CREATEDATE "
			+ " FROM TB_PARTNER A, TB_BUSINESSCODE B, TB_PARTNERCODE C "
			+ " WHERE A.BUSINESSCODE = B.BUSINESSCODE "
			+ " AND A.PARTNERCODE = C.PARTNERCODE "
			
			// partnerName
			+ "<if test=\"partnerName != null and partnerName != ''\"> "
			+ " AND A.PARTNERNAME LIKE CONCAT('%',#{partnerName}, '%') "
			+ "</if>"
			
			// businessNumber
			+ "<if test=\"businessNumber != 0\"> "
			+ " AND A.BUSINESSNUMBER LIKE CONCAT('%',#{businessNumber}, '%') "
			+ "</if>"
			
			+ "</script> "
			)
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws Exception;
	
	/**
	 * TB_PARTNER 테이블 조회(단건)
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_PARTNER "
			+ "WHERE PARTNERID = #{param1}")
	public PartnerModel selectPartnerByPartnerId(int partnerId) throws Exception;
	
	/**
	 * TB_PARTNER 테이블 입력
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_PARTNER (PARTNERNAME, PARTNERCODE, BUSINESSNUMBER, BUSINESSCODE)" 
			+ "VALUES "
			+ "("
			+ "		#{partnerName}, "
			+ "		#{partnerCode},  "
			+ "		#{businessNumber}, "
			+ "		#{businessCode}"
			+ ")"
			)
	@Options(useGeneratedKeys = true, keyProperty="partnerId")
	public void insertPartner(PartnerModel partnerModel) throws Exception;
	
	
	/**
	 * TB_PARTNER 테이블 변경
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PARTNER "
			+ " SET "
			+ "		PARTNERNAME=#{partnerName}, "
			+ " 	PARTNERCODE=#{partnerCode},"
			+ " 	BUSINESSNUMBER=#{businessNumber},"
			+ " 	BUSINESSCODE=#{businessCode} "
			+ " WHERE PARTNERID = #{partnerId} "
			)
	public int updatePartnerByPartnerId(PartnerModel partnerModel) throws Exception;
	
	/**
	 * TB_PARTNER 테이블 삭제
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	@Delete(  " DELETE "
			+ " FROM TB_PARTNER "
			+ " WHERE PARTNERID = #{param1}"
			)
	public int deletePartnerByPartnerId(int partnerId) throws Exception;
	
	

}
