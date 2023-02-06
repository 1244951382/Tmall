package com.charon.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @ClassName CodeGenerator
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/1/28 10:48
 * @Version 1.0
 **/

public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
    FastAutoGenerator.create(
            "jdbc:mysql://localhost:3306/foodieShop?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true",
            "root",
            "root")
        .globalConfig(
            builder -> {
              builder
                  .author("Charon.Wang") // 设置作者
                  .enableSwagger() // 开启 swagger 模式
                  .fileOverride() // 覆盖已生成文件
                  .outputDir("D:\\Work\\springboot2\\src\\main\\java"); // 指定输出目录
            })
        .packageConfig(
            builder -> {
              builder
                  .parent("com.charon.springboot") // 设置父包名
                  .moduleName(null) // 设置父包模块名
                  .pathInfo(
                      Collections.singletonMap(
                          OutputFile.mapperXml,
                          "D:\\Work\\springboot2\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
            })
        .strategyConfig(
            builder -> {
              builder.entityBuilder().enableLombok();
              builder
                  .controllerBuilder()
                  .enableHyphenStyle() // 开启驼峰转连字符
                  .enableRestStyle(); // 开启生成@RestController 控制器
              builder
                  .addInclude("menu") // 设置需要生成的表名
                  .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
            })
        .execute();
    }
}
