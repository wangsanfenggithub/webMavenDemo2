package com.mapper;

import java.util.*;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("IEmpMapper")
public interface IEmpMapper {
    public int save(Emp emp);
    public int update(Emp emp);
    public int delById(Integer eid);
    public Emp findById(Integer eid);
    public List<Emp> findPageAll(PageBean pb);
    public int findMaxRows();
    /***��ѯ�¼����Ա���ı��***/
    public int findMaxEid();

   
}
