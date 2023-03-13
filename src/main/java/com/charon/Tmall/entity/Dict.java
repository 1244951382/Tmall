package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Dict
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/6 13:01
 * @Version 1.0
 **/

@TableName("dict")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dict {

    private String name;
    private String value;
    private String type;
}
