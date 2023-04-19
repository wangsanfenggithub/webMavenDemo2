package com.mapper;

import java.util.*;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("IEmpWelfareMapper")
public interface IEmpWelfareMapper {
	/**����Ա������*/
    public int save(EmpWelfare ewf);
    /**����Ա�����ɾ����������*/
    public int delByEid(Integer eid);
    /**����Ա����Ų�ѯ��������*/
    public List<Welfare> findByEid(Integer eid);
   
}
