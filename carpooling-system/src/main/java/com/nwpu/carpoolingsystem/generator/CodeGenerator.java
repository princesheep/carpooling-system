package com.nwpu.carpoolingsystem.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Description:
 * @Author: wzy
 * @Date: 2024/12/20/22:30
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url= "jdbc:mysql://localhost:3306/carpooling?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("wzy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\java_project\\carpooling-system\\carpooling-system\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.nwpu.carpoolingsystem") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\java_project\\carpooling-system\\carpooling-system\\src\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok(); // add -> @@Getter and @Setter
                    builder.controllerBuilder().enableRestStyle(); // @Controller -> @RestController
                    builder.entityBuilder().columnNaming(NamingStrategy.no_change); //列名保持不变，下划线不转驼峰法
//                    builder.entityBuilder().fileOverride(); //覆盖已生成实体
                    // 设置需要生成的表名--需要的时候直接替换表名即可
                    builder.addInclude("admin", "carpool", "carpool_order", "users");
//                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

