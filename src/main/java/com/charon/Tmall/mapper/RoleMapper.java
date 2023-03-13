package com.charon.Tmall.mapper;

import com.charon.Tmall.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * selectByFlag
     *
     * @param flag
     * @return
     */
    @Select("select id from role where flag = #{flag}")
    Integer selectByFlag(@Param("flag") String flag);
}
