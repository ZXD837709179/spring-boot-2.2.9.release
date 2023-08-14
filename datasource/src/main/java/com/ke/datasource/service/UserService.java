package com.ke.datasource.service;

import com.ke.datasource.dao.mapper.UserMapper;
import com.ke.datasource.model.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

	@Resource
	UserMapper userMapper;


	public List<User> findAllUser(){
		return userMapper.findUser();
	}
}
