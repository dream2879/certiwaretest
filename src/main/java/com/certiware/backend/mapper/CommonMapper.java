package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.BusinessCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.OutsourcingCodeModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCodeModel;
import com.certiware.backend.model.common.RatingCodeModel;
import com.certiware.backend.model.common.RoleCodeModel;

public interface CommonMapper {
	
	/**
	 * TB_BUSINESS_CODE 테이블을 조회한다.
	 * @return com.certiware.backend.model.common.BusinessCode 
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_BUSINESS_CODE ORDER BY PRIORITY")
	public List<BusinessCodeModel> SelectBusinessCode() throws Exception;
	
	/**
	 * TB_DEPT_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_DEPT_CODE ORDER BY PRIORITY")
	public List<DeptCodeModel> SelectDeptCode() throws Exception;
	
	/**
	 * TB_OUTSOURCING_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_OUTSOURCING_CODE ORDER BY PRIORITY")
	public List<OutsourcingCodeModel> SelectOutsourcingCode() throws Exception;
	
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
	public List<RankCodeModel> SelectRankCode() throws Exception;
	
	/**
	 * TB_RATING_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_RATING_CODE ORDER BY PRIORITY")
	public List<RatingCodeModel> SelectRatingCode() throws Exception;
	
	/**
	 * TB_ROLE_CODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_ROLE_CODE ORDER BY PRIORITY")
	public List<RoleCodeModel> SelectRoleCode() throws Exception;

}
