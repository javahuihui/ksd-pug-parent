package com.pug.single;


import com.pug.generator.config.*;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/2/13$ 19:11$
 */
public class GeneratorProjectsSingle {

    public static void main(String[] args) {

        // 初始化数据源
        PugDataSourceConfig dataSourceConfig = PugDataSourceConfig.builder()
                .url("jdbc:mysql://127.0.0.1:3306/ksd-pug-travel?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .username("root")
                .password("mkxiaoer").build();
        // 获取数据表信息
        PugDataTableSourceConfig pugDataTableSourceConfig = PugDataTableSourceConfig.builder().tablename("kss_user").buildTableInfo(dataSourceConfig);
        // 查询所有的表信息
        PugDataTableSourceConfig pugDataTableSourceConfig1 = PugDataTableSourceConfig.builder().buildTables(dataSourceConfig);
        // 全局设置
        PugGlobalConfig pugGlobalConfig = PugGlobalConfig.builder()
                .isoverride()
                .author("星辰同学")
                .gitlink("www.gitee.com")
                .url("www.baidu.com")
                .title("分类")
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .version("1.0.0")
                .port("9000")
                .redisport("6379")
                .redisip("120.77.34.190")
                .redispwd("mkxiaoer1986.")
                .bootversion("2.5.8")


                .apititle("学相伴在线接口文档")
                .apidesc("学相伴在线接口文档我是一个描述")
                .apimemebers("yykk,kuangshen")
                .apiversion("1.0.0")
                .apiurl("http://localhost:9000")

                .build();

        // 开始设置实体名称和包
        PugPackageConfig pugPackageConfig = PugPackageConfig.builder()
                .dataSourceConfig(dataSourceConfig)
                .dataTableSourceConfig(pugDataTableSourceConfig)
                .globalConfig(pugGlobalConfig)
                .outputDir()
                .projectName("kuangstudy-pug-new-boot")
                .rootPackage("org.ksd.pug")
                .model("User")
                .istree(true)
                .entity("pojo").compareEnity().create()
                .vo("vo").compareVo().create()
                .mapper("mapper").compareMapper().create()
                .mapperxml("mapper").compareMapperXml().create()
                .service("service").compareService().create()
                .serviceImpl("service").compareServiceImpl().create()
                .controller("controller").compareController().create()
                .pom().comparePom().create()
                .api().compareApi().create().build();


        //创建工具类和配置类
        PugCommonConfig.builder().build()
                .create(pugPackageConfig)
                .copymain()
                .copyConfig()
                .copyUtils()
                .copyIndexController()
                .copyService()
                .copyIp()
                .copyLua()
                .copyApplicationxml()
                .copyStatic()
                .copyTemplates();

    }
}
