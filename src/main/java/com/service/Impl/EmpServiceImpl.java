package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.IEmpService;
import com.util.MapperUtil;
@Service("EmpServiceImpl")
@Transactional
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoService")
    private MapperUtil mapperService;
    
	public MapperUtil getMapperService() {
		return mapperService;
	}

	public void setMapperService(MapperUtil mapperService) {
		this.mapperService = mapperService;
	}
	
	public boolean save(Emp emp) {
		int code =mapperService.getEmpMapper().save(emp);
		if(code>0){
		/**�����ţ����ҵ�ǰԱ��������ݿ�ı�ţ�***/
		Integer eid=mapperService.getEmpMapper().findMaxEid();
		/**���н��**/
		Salary sa=new Salary(eid,emp.getEmoney());
		mapperService.getSalaryMapper().save(sa);
		/***����Ա����Ӧ�������*/
		String[] wids=emp.getWids();
		if(wids!=null&& wids.length>0){
			for (int i = 0; i < wids.length; i++) {
				EmpWelfare ewf=new EmpWelfare(eid,new Integer(wids[i]));
				mapperService.getEmpWelfareMapper().save(ewf);
			}
		}
		return true;
		}
		return false;
	}

	
	public boolean update(Emp emp) {
		int code=mapperService.getEmpMapper().update(emp);
		if(code>0){
			//����н��
			Salary oldsa=mapperService.getSalaryMapper().findByEid(emp.getEid());
			if(oldsa!=null&& oldsa.getEmoney()!=null){
				if(oldsa.getEmoney()<emp.getEmoney()){
					oldsa.setEmoney(emp.getEmoney());
					mapperService.getSalaryMapper().updateByEid(oldsa);
				}
			}else{
				Salary  sa=new Salary(emp.getEid(),emp.getEmoney());
				mapperService.getSalaryMapper().save(sa);
			}
			//���¸���
			//��ȡԭ�и�������
			List<Welfare> lswf=mapperService.getEmpWelfareMapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//ɾ��ԭ�и���
				mapperService.getEmpWelfareMapper().delByEid(emp.getEid());
			}
			//����µĸ���
			String[] wids=emp.getWids();
			if(wids!=null&& wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));
					mapperService.getEmpWelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	}

	
	public boolean delById(Integer eid) {
		//ɾ��н��
		mapperService.getSalaryMapper().delByEid(eid);
	   //ɾ����Ա����Ӧ����
		mapperService.getEmpWelfareMapper().delByEid(eid);
		//��ȡԱ��
		Emp emp=mapperService.getEmpMapper().findById(eid);
		//ɾ��Ա��
		int code= mapperService.getEmpMapper().delById(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	
	public Emp findById(Integer eid) {
		Emp emp=mapperService.getEmpMapper().findById(eid);
		//��ȡн��
		Salary oldsa=mapperService.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&& oldsa.getEmoney()!=null){
			emp.setEmoney(oldsa.getEmoney());
		}
		//��ȡ�������������+�������ϣ�
		List<Welfare> lswf=mapperService.getEmpWelfareMapper().findByEid(eid);
		if(lswf!=null){
			//�����������
			String[] wids=new String[lswf.size()];
			for (int i = 0; i < lswf.size(); i++) {
				Welfare wf=lswf.get(i);
				wids[i]=wf.getWid().toString();
			}
			emp.setWids(wids);
		}
		emp.setLswf(lswf);
		return emp;
	}

	
	public List<Emp> findPageAll(PageBean pb) {
		
		return mapperService.getEmpMapper().findPageAll(pb);
	}

	
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return mapperService.getEmpMapper().findMaxRows();
	}

}
