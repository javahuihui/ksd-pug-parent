package com.pug.generator.config;

import com.pug.generator.builder.IPugConfigBuilder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/2/14$ 1:03$
 */
@Data
public class PugGlobalConfig {


    /**
     * 是否覆盖已有文件（默认 false）
     */
    private boolean isoverride = false;


    /**
     * 作者
     */
    private String author = "作者";

    /**
     * gitlink
     */
    private String gitlink;

    /**
     * url地址
     */
    private String url;

    /**
     * 时间
     */
    private String datetime;

    /**
     * 标题
     */
    private String title;

    /**
     * 版本号
     */
    private String version = "1.0.0";

    /**
     * boot版本
     */
    private String bootversion = "2.5.8";
    /**
     * 端口
     */
    private String port = "9000";
    /**
     * redisip
     */
    private String redisip = "127.0.0.1";
    /**
     * redis端口
     */
    private String redisport = "6379";
    /**
     * redis密码
     */
    private String redispwd = "";
    /**
     * api标题
     */
    private String apititle = "学相伴PugAdmin文档接口";
    /**
     * api描述
     */
    private String apidesc = "学相伴PugAdmin文档接口描述";
    /**
     * api版本
     */
    private String apiversion = "1.0.0";
    /**
     * apiurl地址
     */
    private String apiurl = "1.0.0";
    /*
     * api开发成员
     * */
    private String apimemebers = "yykk";

    /**
     * 是否开启属性注释
     */
    private boolean isComment = true;


    public static Builder builder() {
        return new Builder();
    }


    public static class Builder implements IPugConfigBuilder<PugGlobalConfig> {

        private final PugGlobalConfig PugGlobalConfig;

        public Builder() {
            this.PugGlobalConfig = new PugGlobalConfig();
        }

        /**
         * 覆盖已有文件
         */
        public Builder isoverride() {
            this.PugGlobalConfig.isoverride = true;
            return this;
        }


        /**
         * 作者
         */
        public Builder author(String author) {
            this.PugGlobalConfig.author = author;
            return this;
        }

        /**
         * 标题
         */
        public Builder title(String title) {
            this.PugGlobalConfig.title = title;
            return this;
        }

        /**
         * 版本号
         */
        public Builder version(String version) {
            this.PugGlobalConfig.version = version;
            return this;
        }


        /**
         * 版本号
         */
        public Builder bootversion(String bootversion) {
            this.PugGlobalConfig.bootversion = bootversion;
            return this;
        }

        /**
         * 服务端口
         */
        public Builder port(String port) {
            this.PugGlobalConfig.port = port;
            return this;
        }

        /**
         * redisip
         */
        public Builder redisip(String redisip) {
            this.PugGlobalConfig.redisip = redisip;
            return this;
        }

        /**
         * redis密码
         */
        public Builder redispwd(String redispwd) {
            this.PugGlobalConfig.redispwd = redispwd;
            return this;
        }


        /**
         * redis端口
         */
        public Builder redisport(String redisport) {
            this.PugGlobalConfig.redisport = redisport;
            return this;
        }


        /**
         * 作者
         */
        public Builder gitlink(String gitlink) {
            this.PugGlobalConfig.gitlink = gitlink;
            return this;
        }

        /**
         * 作者
         */
        public Builder url(String url) {
            this.PugGlobalConfig.url = url;
            return this;
        }


        /**
         * 标题
         */
        public Builder apititle(String apititle) {
            this.PugGlobalConfig.apititle = apititle;
            return this;
        }


        /**
         * 描述
         */
        public Builder apidesc(String apidesc) {
            this.PugGlobalConfig.apidesc = apidesc;
            return this;
        }



        /**
         * 版本
         */
        public Builder apiversion(String apiversion) {
            this.PugGlobalConfig.apiversion = apiversion;
            return this;
        }



        /**
         * 成员
         */
        public Builder apimemebers(String apimemebers) {
            this.PugGlobalConfig.apimemebers = apimemebers;
            return this;
        }


        /**
         * api路径
         */
        public Builder apiurl(String apiurl) {
            this.PugGlobalConfig.apiurl = apiurl;
            return this;
        }

        /**
         * 时间类型对应策略
         */
        public Builder datetime(String datetime) {
            this.PugGlobalConfig.datetime = datetime;
            return this;
        }

        /**
         * 指定注释日期格式化
         *
         * @param pattern 格式
         * @return this
         * @since 3.5.0
         */
        public Builder commentDate(String pattern) {
            this.PugGlobalConfig.datetime = new SimpleDateFormat(pattern).format(new Date());
            return this;
        }


        @Override
        public PugGlobalConfig build() {
            return this.PugGlobalConfig;
        }
    }

}
