package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.BusinessCode;
import com.certiware.backend.model.common.DeptCode;
import com.certiware.backend.model.common.OutsourcingCode;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCode;
import com.certiware.backend.model.common.RatingCode;
import com.certiware.backend.model.common.RoleCode;

public interface CommonMapper {
	
	/**
	 * TB_BUSINESS_CODE 테이블을 조회한다.
	 * @return com.certiware.backend.model.common.BusinessCode 
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_BUSINESS_CODE ORDER BY PRIORITY")
	public List<BusinessCode> SelectBusinessCode() throws Exception;
	
	/**
	 * TB_DEPT_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_DEPT_CODE ORDER BY PRIORITY")
	public List<DeptCode> SelectDeptCode() throws Exception;
	
	/**
	 * TB_OUTSOURCING_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_OUTSOURCING_CODE ORDER BY PRIORITY")
	public List<OutsourcingCode> SelectOutsourcingCode() throws Exception;
	
	/**
	 * TB_PARTNER_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_PARTNER_CODE ORDER BY PRIORITY")
	public List<PartnerCodeModel> SelectPartnerCode() throws Exception;
	
	/**
	 * TB_RANK_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_RANK_CODE ORDER BY PRIORITY")
	public List<RankCode> SelectRankCode() throws Exception;
	
	/**
	 * TB_RATING_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_RATING_CODE ORDER BY PRIORITY")
	public List<RatingCode> SelectRatingCode() throws Exception;
	
	/**
	 * TB_ROLE_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_ROLE_CODE ORDER BY PRIORITY")
	public List<RoleCode> SelectRoleCode() throws Exception;

}
