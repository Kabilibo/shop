package com.mycourse.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.mycourse.meta.User;

public interface LoginDao {

	@Select("select * from person where userName=#{userName}")
	User getUser(@Param("userName") String userName);
	
	
	
}
