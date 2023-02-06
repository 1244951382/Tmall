package com.charon.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charon.springboot.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author Charon.Wang
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

  /**
   * 查询全部信息
   * @return
   */
  @Select("select * from user")
  List<User> findAll();

//  /**
//   * 插入数据
//   * @param user
//   * @returm
//   */
//  @Insert("insert into user(username,password,nickname,email,phone,sex,address) values (#{username},#{password},#{nickname},#{email},#{phone},#{sex},#{address})")
//  int insert(User user);
//
//  /**
//   * 更新一条数据
//   * @param user
//   * @return
//   */
////  @Update("update user set username = #{username},password = #{password},nickname = #{nickname},email = #{email},phone = #{phone},sex = #{sex},address = #{address}")
//  int update(User user);

  /**
   * 删除一条数据
   * @param id
   * @return
   */
  @Delete("delete from user where id = #{id}")
  int deleteById(@Param("id") Integer id);

//  /**
//   * 分页功能
//   * @param pageNum  页码
//   * @param pageSize 每页查询的个数
//   * @param username 用户名
//   * @return
//   */
//  @Select("select * from user where username like concat('%',#{username},'%') limit #{pageNum}, #{pageSize}")
//  List<User> selectPage(Integer pageNum, Integer pageSize, String username);

  /**
   * 统计当前数据个数
   * @param username 用户名
   * @return
   */
  @Select("select count(*) from user where username like concat('%',#{username},'%')")
  Integer selectTotal(String username);
}
