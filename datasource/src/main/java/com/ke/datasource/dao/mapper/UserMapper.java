package com.ke.datasource.dao.mapper;

import com.ke.datasource.model.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//有了mapperScannan就不加@mapper注解了
public interface UserMapper {

	@Select("select * from user")
	public List<User> findUser();
}
