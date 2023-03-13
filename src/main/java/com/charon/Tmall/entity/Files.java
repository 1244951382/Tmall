package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Files
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/5 16:30
 * @Version 1.0
 **/

@Data
@TableName("file")
@AllArgsConstructor
@NoArgsConstructor
public class Files {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private long size;
    private String url;
    private Boolean isDelete;
    private Boolean enable;
    private String md5;
}
