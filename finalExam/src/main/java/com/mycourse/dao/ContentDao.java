package com.mycourse.dao;

import java.sql.Blob;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mycourse.meta.Product;

public interface ContentDao {

	@Insert("insert into content (title,price,icon,abstract,text) values (#{title},#{price},#{icon},#{abstract},#{text})")
	void addProduct(@Param("title") String title, @Param("price") int price, @Param("icon") Blob icon, @Param("abstract") 
		String summary, @Param("text") Blob detail);
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="sqlPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="summary",column="abstract"),
		@Result(property="detailByte",column="text"),
		@Result(property="imageByte",column="icon")
	})
	@Select("select * from content where title=#{title}")
	Product getProductByTitle(@Param("title") String title);
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="sqlPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="summary",column="abstract"),
		@Result(property="detailByte",column="text"),
		@Result(property="imageByte",column="icon")
	})
	@Select("select * from content where id=#{id}")
	Product getProductById(@Param("id") int id);
	
	@Results({
		@Result(property="id",column="id"),
		@Result(property="sqlPrice",column="price"),
		@Result(property="title",column="title"),
		@Result(property="summary",column="abstract"),
		@Result(property="detailByte",column="text"),
		@Result(property="imageByte",column="icon")
	})
	@Select("select * from content")
	List<Product> getProducts();
	
	@Update("update content set title=#{title},price=#{price},icon=#{icon},abstract=#{abstract},text=#{text} where id=#{id}")
	void updateProduct(@Param("id") int id, @Param("title") String title, @Param("price") int price, @Param("icon") Blob icon, 
			@Param("abstract") String summary, @Param("text") Blob detail);
	
	@Delete("delete from content where id=#{id}")
	boolean deleteProduct(@Param("id") int id);
	
}
