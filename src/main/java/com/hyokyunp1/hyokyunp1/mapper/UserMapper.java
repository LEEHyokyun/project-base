package com.hyokyunp1.hyokyunp1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyokyunp1.hyokyunp1.model.ThymUser;

@Mapper
public interface UserMapper {

	List<ThymUser> getUsers(@Param("username") String usernmae);
}
