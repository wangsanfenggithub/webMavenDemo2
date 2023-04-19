package com.mapper;

import java.util.*;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("ISalaryMapper")
public interface ISalaryMapper {
	/**����н��*/
    public int save(Salary sa);
    /**�޸�н��*/
    public int updateByEid(Salary sa);
    /**ɾ��н��*/
    public int delByEid(Integer eid);
    /**����н��*/
    public Salary findByEid(Integer eid);

   
}
