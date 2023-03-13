package com.charon.Tmall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charon.Tmall.controller.dto.UserPasswordDTO;
import com.charon.Tmall.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author Charon.Wang
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询全部信息
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 删除一条数据
     *
     * @param id
     * @return
     */
    @Delete("delete from user where id = #{id}")
    int deleteById(@Param("id") Integer id);


    /**
     * 统计当前数据个数
     *
     * @param username 用户名
     * @return
     */
    @Select("select count(*) from user where username like concat('%',#{username},'%')")
    Integer selectTotal(String username);

    /**
     * 修改密码
     *
     * @param userPasswordDTO
     * @return
     */
    int updatePassword(@Param("userPasswordDTO") UserPasswordDTO userPasswordDTO);
}
