package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.component.ExcelComponent;
import com.certiware.backend.component.QueryComponent;
import com.certiware.backend.mapper.ProgressMapper;
import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.QueryModel;
import com.certiware.backend.model.progress.ProjectPartnerModel;
import com.certiware.backend.model.progress.SelectManpowerListModel;
import com.certiware.backend.model.progress.SelectManpowerListReqModel;
import com.certiware.backend.model.progress.SelectManpowerMMHistoryReqModel;
import com.certiware.backend.model.progress.SelectManpowerMMHistoryResModel;
import com.certiware.backend.model.progress.SelectPartnerNameList;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
import com.certiware.backend.model.progress.UpdateManpowerMmReqModel;
import com.certiware.backend.model.progress.UpdateManpowerModel;

@Service
public class ProgressService {
	
	@Autowired
	ProgressMapper progressMapper;
	@Autowired
	CommonService commonService;
	@Autowired
	ExcelComponent excelComponent;
	@Autowired
	QueryComponent queryComponent;	

	
	/**
	 * TB_MANPOWER 테이블조회
	 * @param projectId:프로젝트아이디
	 * @return
	 * @throws Exception
	 */
	public SelectManpowerListModel selectManpowerList(SelectManpowerListModel selectManpowerListModel, SelectManpowerListReqModel selectManpowerListReqModel) throws Exception{
		
		// 외주업체리스트를 가져온다(개인사업자/프리랜서 제외)
		selectManpowerListModel.setProjectPartnerModels(progressMapper.selectOutsourcingByProjectId(selectManpowerListReqModel.getProjectId()));
		
		// 투입인력 이름목록을 가져온다.
		selectManpowerListModel.setManpowerNameModels(progressMapper.selectManpowerByProjectId(selectManpowerListReqModel));		
		
		return selectManpowerListModel;
	}
	
	
	/**
	 * TB_MANPOWER 테이블 INSERT
	 * @param manpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean insertManpower(ManpowerModel manpowerModel) throws Exception{	
	
			// TB_MANPWER 테이블에 정보를 입력한다.
			progressMapper.inserteManpower(manpowerModel);
			
			// 투입기간에 따라 MM을 자동계산하여 보낸다.
			this.mergeManpowerMM(manpowerModel);
		
		return true;
	}
	
	/**
	 * TB_MANPOWER 테이블 UPDATE
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean updateManpower(UpdateManpowerModel updateManpowerModel) throws Exception{
						
		// TB_MANPWER 테이블에 정보를 변경한다.
		progressMapper.updateManpower(updateManpowerModel);
		
		
		// startDate와 endDate가 변경됬다고 보고 TB_MANPWERMM의 정보도 수정해준다.		
		ManpowerModel manpowerModel = new ManpowerModel();
		manpowerModel.setProjectId(updateManpowerModel.getProjectId());
		manpowerModel.setManpowerName(updateManpowerModel.getManpowerName());
		manpowerModel.setStartDate(updateManpowerModel.getStartDate());
		manpowerModel.setEndDate(updateManpowerModel.getEndDate());
		
		// 변경된 기간에 포함되지 않는 기간의 MM정보 삭제
		this.deleteManpowerMmByPeriod(manpowerModel);
		
		// 투입기간 변경에 따라 MM을 자동계산하여 보낸다.
		this.mergeManpowerMM(manpowerModel);		
		
		return true;
	}
	
	/**
	 * Manpower 정보를 삭제한다.
	 * @param manpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpower(ManpowerModel manpowerModel) throws Exception{
		
		// TB_MANOWER 정보 삭제
		progressMapper.deleteManpower(manpowerModel);
		
		// TB_MANPWERMM 정보도 삭제
		//this.deleteManpowerMmByPK(manpowerModel);
		
		return true;
		
	}
	
	/**
	 * TB_MANPOWERMM 테이블조회
	 * @param projectId:프로젝트ID
	 * @param manpowerName:투입인력이름
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception{
		return progressMapper.selectManpowerMmList(projectId, manpowerName);
	}
	
	/**
	 * TB_MANPOWERMM 테이블 MERGE
	 * @param modifyManpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyManpowerMm(List<ManpowerMmModel> manpowerMmModels) throws Exception{
		
		// merge
		for (ManpowerMmModel manpowerMmModel : manpowerMmModels) {
			progressMapper.mergeManpowerMm(manpowerMmModel);
		}
		
		return true;
	}
	
	/**
	 * TB_MANPOWERMM 테이블을 엽데이트하며, 이력을 저장한다.
	 * @param updateManpowerMmReqModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateManpowerMm(UpdateManpowerMmReqModel updateManpowerMmReqModel) throws Exception{
		
		// update
		progressMapper.updateManpowerMm(updateManpowerMmReqModel);
		
		// 이력저장
		progressMapper.insertManpowerMMHistory(updateManpowerMmReqModel);
		
		// error 발생 시 exception을 던지며 controller에서 false 처리
		return true;
	}
	
	/**
	 * TB_MANPOWERMM 테이블 사용자 정보 삭제(기간에 해당하는 것만)
	 * @param manpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpowerMmByPeriod(ManpowerModel manpowerModel) throws Exception{
		
		progressMapper.deleteManpowerMmByPeriod(manpowerModel);
		
		return true;
	}
	
	/**
	 * TB_MANPWERMM 테이블 사용자 정보 삭제
	 * @param manpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpowerMmByPK(ManpowerModel manpowerModel) throws Exception{
		
		progressMapper.deleteManpowerMmByPK(manpowerModel);
		
		return true;
	}	
	
	
	/**
	 * 프로젝트 진행현황 리스트를 가져오는 로직
	 * @param selectProgressListReqModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectProgressListResModel> selectProgressList(SelectProgressListReqModel selectProgressListReqModel) throws Exception {
		
		// 프로젝트 진행현황 조회를 위한 쿼리를 생성함수 호출
		QueryModel queryModel = new QueryModel();		
		queryModel = queryComponent.makeSelectProgressListQuery(queryModel, selectProgressListReqModel);
		
		// 쿼리를 가지고 DB 조회 후 결고 리턴
		return progressMapper.SelectQuery(queryModel);
	}
	
	
	/**
	 * 프로젝트 아이디를가지고 외주회사명을 가져온다.
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public List<SelectPartnerNameList> SelectPartnerNameList(int partnerId) throws Exception{
		return progressMapper.selectOutsourcingByPartnerId(partnerId);
	}
	
	
	/**
	 * 프로젝트 진행 현황 
	 * @param selectProgressListReqModel
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook excelDownload(SelectProgressListReqModel selectProgressListReqModel) throws Exception{
		
		return excelComponent.makeSelectProgressListExcel(				// 엑셀파일 생성
				this.selectProgressList(selectProgressListReqModel));	// 프로젝트 진행현황 DB조회
	
	}	
	
	
	/**
	 * 프로젝트 아이디로 외주 정보를 가져온다.
	 * 개인사업자/프리랜서 제외
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectPartnerModel> selectOutsourcingList(int projectId) throws Exception{
		return progressMapper.selectOutsourcingByProjectId(projectId);
	}
	
	/**
	 * TB_MANPOWERMM 테이블의 수정이력을 가져온다.
	 * @param selectManpowerMMHistoryReqModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectManpowerMMHistoryResModel> selectManpowerMMHistory(SelectManpowerMMHistoryReqModel selectManpowerMMHistoryReqModel) throws Exception{
	
		// DB 조회 및 리턴
		return progressMapper.selectManpowerMMHistory(selectManpowerMMHistoryReqModel);
	}

	
	
	
	
	/************************************** 내부 함수 **************************************/
	
	/**
	 * 프로젝트 투입기간에 따라 M/M 자동 계산하고, TB_MANPOWERMM 테이블에 반영한다.
	 * @param manpowerModel
	 * @throws Exception
	 */
	private void mergeManpowerMM(ManpowerModel manpowerModel) throws Exception {
		
		List<ManpowerMmModel> manpowerMmModels = new ArrayList<ManpowerMmModel>();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		
		int projectId = manpowerModel.getProjectId();
		String manpowerName = manpowerModel.getManpowerName();
		Date startDate =  manpowerModel.getStartDate();
		Date endDate = manpowerModel.getEndDate();		
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(startDate);
		cal2.setTime(endDate);		
		
		// 시작점 확인
		boolean flag = true;
		double mm=0;
		
		while(!(-1 == endDate.compareTo(df.parse(df.format((cal.getTime())))))){
			
			ManpowerMmModel temp = new ManpowerMmModel();
			
			// MM을 계산하기전 기본 세팅
			temp.setProjectId(projectId);
			temp.setManpowerName(manpowerName);
			temp.setMonth(cal.getTime()); // 년,월만 잘 들어가지면 됨 		
		
			
			// 반복문 시작 loop 
			if(flag)
			{
				// MM 구하기 근무일수/해당월의일수
				mm = (double) (cal.getActualMaximum(cal.DAY_OF_MONTH) - cal.get(cal.DAY_OF_MONTH) + 1) / (double) cal.getActualMaximum(cal.DAY_OF_MONTH); 
				cal.set(cal.DATE, 1);
				
				flag = false;
				
			}
			// 반복문 중간
			else
			{				
				mm = 1.0;				
			}
			
			// cal에 월을 더 해준다. 
			cal.add(Calendar.MONTH, 1);
			
			// 반복문 마지막 loop			
			if((-1 >= endDate.compareTo(df.parse(df.format((cal.getTime())))))){
				
				// MM 구하기 근무일수/해당월의일수
				mm = (double) cal2.get(cal2.DAY_OF_MONTH) / (double) cal2.getActualMaximum(cal2.DAY_OF_MONTH);				
			}
			
			// 소수점 4자리까지 구하기(버림)
			mm = (int) (mm * 10000) / 10000.0;	
			// temp 객체에 mm추가
			temp.setMm(mm);
			// temp 객체 add
			manpowerMmModels.add(temp);			
			
		};
		
		this.modifyManpowerMm(manpowerMmModels);		
	}

}
