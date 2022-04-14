package com.pug.generator.config;

import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.generator.builder.IPugConfigBuilder;
import com.pug.generator.freemarker.tool.*;
import com.pug.generator.pojo.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/2/14$ 1:23$
 */
@Data
public class PugViewConfig {

    private PugDataSourceConfig pugDataSourceConfig;
    private PugDataTableSourceConfig pugDataTableSourceConfig;
    private PugGlobalConfig pugGlobalConfig;
    private PugPackageConfig pugPackageConfig;

    /**
     * 创建文件所在的位置
     * 建议是： System.getProperty("user.dir")
     */
    private String outputDir;

    /**
     * 生成指定的项目名称
     */
    private String projectName;

    /*
     * 模块名称
     * */
    private String model;

    /**
     * 视图文件
     */
    private String viewPath = "src.views";

    /**
     * api的帮助文档
     */
    private String apidocPath = "src.api";

    /**
     * router的帮助文档
     */
    private String routerPath = "src.router.config";

    /*
     * api目录
     */
    private String apiPath = "src.service";

    /**
     * 是否需要富文本
     */
    private boolean isoverride = false;

    /**
     * 是否需要富文本
     */
    private boolean isEditor = false;

    /**
     * 是否需要文件上传
     */
    private boolean isUpload = false;

    /**
     * 是否单页 控制添加页面的步骤
     */
    private boolean isSingle = true;

    /**
     * 是否需要分类
     */
    private boolean isCategory = false;
    /**
     * 对应模板
     */
    private String template;
    /**
     * 文件目录
     */
    private String filepath;
    /**
     * 最终文件名
     */
    private String classname;


    public static Builder builder() {
        return new Builder();
    }


    private static String replaceLine(String content) {
        return content.replaceAll("\\.", "/");
    }


    public static class Builder implements IPugConfigBuilder<PugViewConfig> {

        private final PugViewConfig pugViewConfig;

        public Builder() {
            this.pugViewConfig = new PugViewConfig();
        }

        /**
         * 覆盖已有文件
         */
        public Builder isoverride(boolean override) {
            this.pugViewConfig.isoverride = override;
            return this;
        }

        public Builder dataTableSourceConfig(PugDataTableSourceConfig dataTableSourceConfig) {
            pugViewConfig.pugDataTableSourceConfig = dataTableSourceConfig;
            return this;
        }

        public Builder dataSourceConfig(PugDataSourceConfig dataSourceConfig) {
            pugViewConfig.pugDataSourceConfig = dataSourceConfig;
            return this;
        }

        public Builder globalConfig(PugGlobalConfig globalConfig) {
            pugViewConfig.pugGlobalConfig = globalConfig;
            return this;
        }

        public Builder pugPackageConfig(PugPackageConfig pugPackageConfig) {
            pugViewConfig.pugPackageConfig = pugPackageConfig;
            return this;
        }

        /**
         * 是否上传
         */
        public Builder isUpload(boolean isUpload) {
            this.pugViewConfig.isUpload = isUpload;
            return this;
        }

        /**
         * 是否单页 控制添加页面的步骤
         */
        public Builder isSingle(boolean isSingle) {
            this.pugViewConfig.isSingle = isSingle;
            return this;
        }

        /**
         * 是否富文本
         */
        public Builder isEditor(boolean isEditor) {
            this.pugViewConfig.isEditor = isEditor;
            return this;
        }

        /**
         * 是否分类
         */
        public Builder isCategory(boolean isCategory) {
            this.pugViewConfig.isCategory = isCategory;
            return this;
        }

        /**
         * 对应模块
         */
        public Builder model(String model) {
            this.pugViewConfig.model = model.toLowerCase();
            return this;
        }

        /**
         * 指定输出目录
         */
        public Builder outputDir(String outputDir) {
            this.pugViewConfig.outputDir = outputDir;
            return this;
        }

        /**
         * 指定工程名
         */
        public Builder projectName(String projectName) {
            this.pugViewConfig.projectName = projectName;
            return this;
        }

        /**
         * 视图目录
         */
        public Builder viewPath(String viewPath) {
            this.pugViewConfig.viewPath = viewPath;
            return this;
        }


        /**
         * api目录
         */
        public Builder apiPath(String apiPath) {
            this.pugViewConfig.apiPath = apiPath;
            return this;
        }

        /**
         * api目录
         */
        public Builder routerPath(String routerPath) {
            this.pugViewConfig.routerPath = routerPath;
            return this;
        }

        public Builder routerCompare(String... name) {
            this.pugViewConfig.classname = Vsserts.isNullArray(name) ? "create.js" : name[0];
            this.pugViewConfig.routerPath = replaceLine(
                    this.pugViewConfig.projectName + "." + this.pugViewConfig.routerPath);
            return this;
        }


        public Builder apiCompare(String... name) {
            this.pugViewConfig.classname = Vsserts.isNullArray(name) ? "index.js" : name[0];
            this.pugViewConfig.filepath = replaceLine(
                    this.pugViewConfig.projectName + "." + this.pugViewConfig.apiPath + "." + this.pugViewConfig.model);
            this.pugViewConfig.template = "/vue/service.tml";
            return this;
        }

        public Builder listCompare(String... name) {
            this.pugViewConfig.classname = Vsserts.isNullArray(name) ? "index.vue" : name[0];
            this.pugViewConfig.filepath = replaceLine(
                    this.pugViewConfig.projectName + "." + this.pugViewConfig.viewPath + "." + this.pugViewConfig.model);
            this.pugViewConfig.template = "/vue/index.tml";
            return this;
        }

        public Builder addCompare(String... name) {
            this.pugViewConfig.classname = Vsserts.isNullArray(name) ? "add.vue" : name[0];
            this.pugViewConfig.filepath = replaceLine(
                    this.pugViewConfig.projectName + "." + this.pugViewConfig.viewPath + "." + this.pugViewConfig.model);
            this.pugViewConfig.template = "/vue/add.tml";
            return this;
        }

        public Builder apiDoc(String apiDocPath) {
            this.pugViewConfig.apidocPath = apiDocPath;
            return this;
        }

        public Builder apiDocCompare(String... name) {
            this.pugViewConfig.classname = Vsserts.isNullArray(name) ? "api.html" : name[0];
            this.pugViewConfig.filepath = replaceLine(
                    this.pugViewConfig.projectName + "." + this.pugViewConfig.apidocPath + "." + this.pugViewConfig.model);
            this.pugViewConfig.template = "/api/template.html";
            return this;
        }


        public Builder create() {
            try {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
                // 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
                File file = new ClassPathResource("/templates").getFile();
                cfg.setDirectoryForTemplateLoading(file);
                // 指定模板如何检索数据模型，这是一个高级的主题了… // 但先可以这么来用：
                cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));

                // 创建根哈希表
                Map root = new HashMap();
                // 在根中放入字符串"user"
                root.put("tablename", pugViewConfig.pugDataTableSourceConfig.getTablename());

                root.put("author", pugViewConfig.pugGlobalConfig.getAuthor());
                root.put("datetime", pugViewConfig.pugGlobalConfig.getDatetime());
                root.put("title", pugViewConfig.pugGlobalConfig.getTitle());
                root.put("burl", pugViewConfig.pugGlobalConfig.getUrl());
                root.put("version", pugViewConfig.pugGlobalConfig.getVersion());
                root.put("bootversion", pugViewConfig.pugGlobalConfig.getBootversion());
                root.put("gitlink", pugViewConfig.pugGlobalConfig.getGitlink());
                root.put("apititle", pugViewConfig.pugGlobalConfig.getApititle());
                root.put("apidesc", pugViewConfig.pugGlobalConfig.getApidesc());
                root.put("apiurl", pugViewConfig.pugGlobalConfig.getApiurl());
                root.put("apiversion", pugViewConfig.pugGlobalConfig.getApiversion());
                root.put("apimemebers", pugViewConfig.pugGlobalConfig.getApimemebers());

                root.put("beanModel", pugViewConfig.getPugPackageConfig().getModel());
                root.put("model", pugViewConfig.getPugPackageConfig().getModel().toLowerCase());


                root.put("rootPath", "${rootPath}");


                root.put("url", pugViewConfig.pugDataSourceConfig.getUrl());
                root.put("dbusername", pugViewConfig.pugDataSourceConfig.getUsername());
                root.put("dbpwd", pugViewConfig.pugDataSourceConfig.getPassword());


                List<TableInfo> tableInfos = pugViewConfig.pugDataTableSourceConfig.getTableInfos();
                long count = tableInfos.stream().filter(item -> item.getName().equals("pid") || item.getName().equals("parentId")).count();
                long count2 = tableInfos.stream().filter(item -> item.getName().equals("categoryid") || item.getName().equals("categoryId") || item.getName().equals("cid")).count();
                root.put("istree", count > 0);
                root.put("isEditor", pugViewConfig.isEditor());
                root.put("isCategory", count2 > 0);
                root.put("isSingle", pugViewConfig.isSingle());


                root.put("tableInfos", tableInfos);
                root.put("fields", tableInfos);
                root.put("kuohao", new AddKuohu());
                root.put("kuohao2", new AddKuohu2());
                root.put("kuohao3", new AddKuohu3());
                root.put("listtag", new ListTag());
                root.put("Include", new IncludeTagMethod());


                Template temp = cfg.getTemplate(this.pugViewConfig.template);

                // 指定最终渲染的页面存储的位置

                File rootPath = new File(this.pugViewConfig.outputDir, this.pugViewConfig.filepath);
                if (!rootPath.exists()) {
                    rootPath.mkdirs();
                }

                File targetFile = new File(rootPath, this.pugViewConfig.classname);
                Writer out = new OutputStreamWriter(new FileOutputStream(targetFile), StandardCharsets.UTF_8);
                // freemaker的模板渲染替换
                temp.process(root, out);
                out.flush();

                return this;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


        public Builder addMenu() {
            try {
                String title = pugViewConfig.getPugGlobalConfig().getTitle();
                String model = pugViewConfig.getModel();
                // 1: 转换成map
                Connection connection = pugViewConfig.getPugDataSourceConfig().getConn();
                PreparedStatement statement1 = connection.prepareStatement("select count(1) from kss_nav_menu where path ='/" + model + "'");
                PreparedStatement statement2 = connection.prepareStatement("select count(1) from kss_nav_menu");
                PreparedStatement statement3 = connection.prepareStatement("select max(indexon) from kss_nav_menu");
                ResultSet rs = statement1.executeQuery();
                ResultSet rs2 = statement2.executeQuery();
                ResultSet rs3 = statement3.executeQuery();
                rs.next();
                rs2.next();
                rs3.next();
                int count = rs.getInt(1);
                int count2 = rs2.getInt(1) + 1;
                int sorted = rs3.getInt(1);
                if (count == 0) {
                    StringBuilder builder = new StringBuilder();
                    int index = count2 - 1;
                    int count3 = count2;
                    builder.append("(" + count2 + ",'" + title + "管理'," + (sorted + 2) + ",'/" + model + "','icon-dashboard',1,now(),now(),0,'@/views/admin/" + model + "/index.vue','" + model + "','layout'," + (sorted + 1) + ",1,0),");
                    builder.append("(" + (++count3) + ",'" + title + "列表',1,'/" + model + "/list','',1,now(),now()," + count2 + ",'@/views/admin/" + model + "/index.vue','" + model + "list','layout'," + (sorted + 1) + ",1,0),");
                    builder.append("(" + (++count3) + ",'" + title + "添加',2,'/" + model + "/add','',1,now(),now()," + count2 + ",'@/views/admin/" + model + "/add.vue','" + model + "add','layout'," + (sorted + 1) + ",1,0),");
                    builder.append("(" + (++count3) + ",'" + title + "编辑',3,'/" + model + "/edit/:id','',1,now(),now()," + count2 + ",'@/views/admin/" + model + "/add.vue','" + model + "edit','layout'," + (sorted + 1) + ",0,0)");
                    String sql = "INSERT INTO `kss_nav_menu` (id,name,sorted,path,icon,status,create_time,update_time,pid,componentname,pathname,layout,indexon,showflag,isdelete)  VALUES " + builder.toString();
                    PreparedStatement statement = connection.prepareStatement(sql);
                    int update = statement.executeUpdate();
                    List<String> lines = new ArrayList<>();
                    if (update > 0) {
                        System.out.println(title + "菜单添加成功!!!");
                    }
                    statement.close();
                } else {
                    System.out.println(title + "已经添加过了....");
                }
                rs2.close();
                rs.close();
                statement2.close();
                statement1.close();
                connection.close();
                return this;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public Builder toRouter() {
            try {
                // 1: 转换成map
                Connection connection = pugViewConfig.getPugDataSourceConfig().getConn();
                PreparedStatement statement = connection.prepareStatement("select * from kss_nav_menu");
                ResultSet rs = statement.executeQuery();
                StringBuilder builder = new StringBuilder();
                builder.append("import transferRouter from \"@/utils/menu\";\n");
                builder.append("\nconst MenuList = [\n");
                while (rs.next()) {
                    builder.append("    transferRouter(" + rs.getLong("id") + ", " + rs.getLong("pid") + ", \"" + rs.getString("name") + "\", \"" + rs.getString("pathname") + "\", \"" + rs.getString("path") + "\", import('" + rs.getString("componentname") + "'), \"" + rs.getString("icon") + "\", " + rs.getInt("indexon") + "," + (rs.getInt("showflag") == 1) + "),\n");
                }
                String str = builder.toString();
                String appenstr = str.substring(0, str.length() - 2);
                StringBuilder builder1 = new StringBuilder();
                builder1.append(appenstr);
                builder1.append("\n];\n\n");
                builder1.append("export default MenuList;");

                rs.close();
                statement.close();
                connection.close();
                File rootPath = new File(this.pugViewConfig.outputDir, this.pugViewConfig.routerPath);
                if (!rootPath.exists()) {
                    rootPath.mkdirs();
                }
                File targetFile = new File(rootPath, this.pugViewConfig.classname);
                FileUtils.writeStringToFile(targetFile, builder1.toString());

            } catch (Exception ex) {
                System.out.println("数据库出错了...");
            }

            return this;
        }


        /**
         * 开始构建
         */
        @Override
        public PugViewConfig build() {
            return pugViewConfig;
        }
    }
}
