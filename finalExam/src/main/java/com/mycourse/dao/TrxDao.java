package com.mycourse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.mycourse.meta.BuyProduct;

public interface TrxDao {

	@Insert("insert into trx (contentId,personId,price,time) values (#{contentId},#{personId},#{price},#{time})")
	boolean addTrx(@Param("contentId")int contentId, @Param("personId")int personId, 
			@Param("price") int price, @Param("time")long time);
	
	@Results({
		@Result(property="personId",column="personId"),
		@Result(property="id",column="contentId"),
		@Result(property="buyTime",column="time"),
		@Result(property="buySqlPrice",column="price")
	})
	@Select("select * from trx where personId=#{personId}")
	List<BuyProduct> getProductsByUser(@Param("personId")int userId);
	
	@Results({
		@Result(property="personId",column="personId"),
		@Result(property="id",column="contentId"),
		@Result(property="buyTime",column="time"),
		@Result(property="buySqlPrice",column="price")
	})
	@Select("select * from trx where contentId=#{contentId}")
	BuyProduct getProductByProduct(@Param("contentId")int contentId);
	
	
}
