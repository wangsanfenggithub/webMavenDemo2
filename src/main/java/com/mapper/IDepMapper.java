package com.mapper;

import java.util.*;
import org.springframework.stereotype.Service;
import com.po.*;
@Service("IDepMapper")
public interface IDepMapper {
	/**��ѯ��������*/
    public List<Dep> findAll();

   
}
