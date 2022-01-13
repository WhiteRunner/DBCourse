package com.pj.project.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pj.utils.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: user -- User
 * @author xyy 
 */

@Mapper
@Repository
// extends BaseMapper<User>
public interface MyUserMapper extends BaseMapper<MyUser> {


}
