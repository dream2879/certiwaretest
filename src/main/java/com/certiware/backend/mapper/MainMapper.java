package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.UnitPriceModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.ManpowerMMStatisticsModel;
import com.certiware.backend.model.main.ManpowerStatisticsModel;
import com.certiware.backend.model.main.ProjectStatisticsModel;
import com.certiware.backend.model.main.SelectDashboardReqModel;
import com.certiware.backend.model.main.SelectMenuModel;

public interface MainMapper {
	
	/**
	 * 로그인한다.
	 * String으로 받을경우 param1, param2 ... or 0, 1 ... 으로 매핑
	 * @throws Exception
	 */
	@Select	(  
				" SELECT * "
			+ 	" FROM TB_USER "
			+ 	" WHERE USERID = #{param1} "			
			)
	public UserModel selectUserByPK(String userId) throws Exception;
	
	
	/**
	 * TB_MENU 테이블조회
	 * ROLECODE에 해당되는 코드만 조회
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT *                       "
			+ " FROM TB_MENU                   "
			+ " WHERE ROLECODE <= #{param1}    "
			+ " ORDER BY MAINMENUID, SUBMENUID " )
	public List<SelectMenuModel> selectMenuByRoleCode(String roleCode) throws Exception;
	
	
	/**
	 * TB_PARTNER 테이블조회
	 * PARTNERNAME 컬럼을 조건으로 매출처에 해당하는 값들만 조회.
	 * @param partnerName:매출처명
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT *                           "
			+ " FROM TB_PARTNER                    "			
			+ " WHERE PARTNERNAME LIKE CONCAT('%', #{partnerName},'%') "
			+ " AND BUSINESSCODE IN (#{businessCode}, '3')   "
			)
	public List<PartnerModel> selectCustomerPatner(PartnerModel partnerModel) throws Exception;
	
	
	/**
	 * 월별 프로젝트 정보를 조회한다.
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script> "
			+ "SELECT  SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-01-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M1,								"
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-02-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M2,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-03-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M3,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-04-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M4,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-05-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M5,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-06-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M6,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-07-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M7,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-08-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M8,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-09-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M9,                "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-10-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M10,               "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-11-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M11,               "
			+ "         SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-12-01') BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M12,               "
			+ "         COUNT(*) AS TOTAL                                                                                                                                                         "
			+ " FROM TB_PROJECT                                                                                                                                                                   "
			+ " WHERE YEAR(#{year}) BETWEEN YEAR(STARTDATE) AND YEAR(ENDDATE)                                                                                                                     "
			+ "<if test=\"deptCode != null and deptCode != ''\"> "
			+ " AND DEPTCODE = #{deptCode}                                                                                                                                                        "
			+ "</if> "
			+ "</script>"
			)
	public ProjectStatisticsModel selectProjectStatistics(SelectDashboardReqModel selectDashboardReqModel) throws Exception;
	
	
	/**
	 * 월별 투입인력 정보를 조회한다.
	 * @param dashboardReqModel
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ " SELECT *, M1 + M2 + M3 + M4 + M5 + M6 + M7 + M8 + M9 + M10 + M11 + M12 AS MMTOTAL                                                                                                 "
			+ " FROM (                                                                                                                                                                            "
			+ " 			SELECT  SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-01-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M1,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-02-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M2,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-03-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M3,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-04-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M4,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-05-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M5,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-06-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M6,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-07-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M7,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-08-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M8,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-09-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M9,      "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-10-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M10,     "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-11-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M11,     "
			+ " 			        SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-12-01') BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END) AS M12,     "   
			+ " 			        COUNT(*) AS REALTOTAL                                                                                                                                               "
			+ " 			FROM TB_MANPOWER A, TB_PROJECT B                                                                                                                                            "
			+ " 			WHERE A.PROJECTID = B.PROJECTID                                                                                                                                             "
			+ " 			AND YEAR(#{year}) BETWEEN YEAR(B.STARTDATE) AND YEAR(B.ENDDATE)                                                                                                             "
			+ "<if test=\"deptCode != null and deptCode != ''\"> "
			+ " 			AND B.DEPTCODE = #{deptCode}                                                                                                                                                "
			+ "</if>"
			+ " 		) A "
			+ "</script>    																																										"
			)
	public ManpowerStatisticsModel selectManpowerStatistics(SelectDashboardReqModel dashboardReqModel) throws Exception;
	
	
	/**
	 * 월별 매출 정보를 조회한다.
	 * @param dashboardReqModel
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ " SELECT  TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-01-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M1,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-02-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M2,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-03-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M3,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-04-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M4,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-05-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M5,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-06-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M6,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-07-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M7,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-08-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M8,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-09-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M9,                                                    "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-10-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M10,                                                   "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-11-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M11,                                                   "
			+ "         TRUNCATE((SUM(CASE WHEN DATE_FORMAT(#{year}, '%Y-12-01') = MONTH THEN SELLINGAMOUNT ELSE 0 END) / 10000000), 1) AS M12,                                                   "
			+ "         SUM(SELLINGAMOUNT) AS TOTAL                                                                                                                                               "
			+ " FROM (                                                                                                                                                                            "
			+ "         SELECT DATE_FORMAT(A.MONTH, '%Y-%m-%d') AS MONTH, FLOOR(A.MM * B.SELLINGAMOUNT) AS SELLINGAMOUNT                                                                          "
			+ "         FROM TB_MANPOWERMM A, TB_MANPOWER B, TB_PROJECT C                                                                                                                         "
			+ "         WHERE A.PROJECTID = B.PROJECTID AND B.MANPOWERNAME = A.MANPOWERNAME                                                                                                       "
			+ "         AND B.PARTNERID = C.PARTNERID                                                                                                                                             "
			+ "         AND YEAR(#{year}) = YEAR(A.MONTH)                                                                                                                                         "
			+ "<if test=\"deptCode != null and deptCode != ''\"> "
			+ "         AND C.DEPTCODE = #{deptCode}             "
			+ "</if>                                                                                                                                 "
			+ "       ) A	"
			+ "</script>	                                                                                                                                                                  "
			)
	public ManpowerMMStatisticsModel selectManpowerMMStatistics(SelectDashboardReqModel dashboardReqModel) throws Exception; 
	
	
	/**
	 * 단가 테이블을 조회한다.
	 * 년도 조건이 있다면 현재 년도를 기준으로 3년치의 데이터만 가져온다.
	 * @param param1
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ "SELECT *"
			+ "FROM TB_UNITPRICE"
			+ "<if test=\"param1 != null and param1 != ''\"> "
			+ "WHERE YEAR BETWEEN #{param1} -2 AND #{param1}"
			+ "</if>"			
			+ "</script>")
	public List<UnitPriceModel> selectUnitPrice(@Param("param1")String param1) throws Exception;
	
	
	/**
	 * 단가 테이블을 Merge 한다.
	 * @param unitPriceModel
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_UNITPRICE "	// insert
			+ "VALUES("
			+ "			#{year},"
			+ "			#{ratingCode},"
			+ "			#{unitPrice},"
			+ "			#{priority}"
			+ "		 )"
			+ "ON DUPLICATE KEY UPDATE"	// update
			+ "			UNITPRICE = #{unitPrice},"
			+ "			PRIORITY = #{priority}"
			)
	public void mergeUnitPrice(UnitPriceModel unitPriceModel) throws Exception;
	
	
	/**
	 * 단가 테이블의 정보를 삭제한다.
	 * @param year
	 * @throws Exception
	 */
	@Delete("DELETE FROM TB_UNITPRICE"
			+ "WHERE YEAR = #{param1}")
	public void deleteUnitPrice(String year) throws Exception;
	
	

	

}
