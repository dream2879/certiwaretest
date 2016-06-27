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
import com.certiware.backend.model.common.UserModel;

public interface CommonMapper {
	
	/**
	 * TB_BUSINESSCODE 테이블을 조회한다.
	 * @return com.certiware.backend.model.common.BusinessCode 
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_BUSINESSCODE "
			+ "ORDER BY PRIORITY")
	public List<BusinessCodeModel> SelectBusinessCode() throws Exception;
	
	/**
	 * TB_DEPTCODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_DEPTCODE "
			+ "ORDER BY PRIORITY")
	public List<DeptCodeModel> SelectDeptCode() throws Exception;
	
	/**
	 * TB_OUTSOURCINGCODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_OUTSOURCINGCODE "
			+ "ORDER BY PRIORITY")
	public List<OutsourcingCodeModel> SelectOutsourcingCode() throws Exception;
	
	/**
	 * TB_PARTNERCODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_PARTNERCODE "
			+ "ORDER BY PRIORITY")
	public List<PartnerCodeModel> SelectPartnerCode() throws Exception;
	
	/**
	 * TB_RANKCODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_RANKCODE "
			+ "ORDER BY PRIORITY")
	public List<RankCodeModel> SelectRankCode() throws Exception;
	
	/**
	 * TB_RATINGCODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_RATINGCODE "
			+ "ORDER BY PRIORITY")
	public List<RatingCodeModel> SelectRatingCode() throws Exception;
	
	/**
	 * TB_ROLECODE 테이블을 조회한다.
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_ROLECODE "
			+ "ORDER BY PRIORITY")
	public List<RoleCodeModel> SelectRoleCode() throws Exception;	

}
