package com.mapper;

import java.util.*;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("IWelfareMapper")
public interface IWelfareMapper {
	/**��ѯ����*/
    public List<Welfare> findAll();

   
}
