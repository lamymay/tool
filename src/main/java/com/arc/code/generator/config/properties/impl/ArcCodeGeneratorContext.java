package com.arc.code.generator.config.properties.impl;

import com.arc.code.generator.model.ClassFullName;
import lombok.Data;

import java.io.File;

/**
 * 代码生成器环境上下文
 *
 * @author may
 * @since 2021/4/16 18:19
 */
@Data
public final class ArcCodeGeneratorContext   {

    private String url; //数据库连接url
    private String user;  //数据库账号
    private String password; //数据库密码
    private String driverClassName; //驱动名称

    private String schemaName;//schemaName(数据库名)【必要】
    private String tableName;//表名
    private String removePrefix;//数据库表的前缀

    private String tableAlias;//数据库表在mapper的sql中的别名
    private String author;//"@author" 需要指定,缺省情况下获取机器当前用户

    private String output = File.separator + "code_output_";// //T:\data\log\

    //输出配置
    private String rootNamespace = "com.arc.zero";

    /**
     * 命名
     */
    private ClassFullName classFullName;

    /**
     * 代码生成方案
     */
    private Integer generateType;


    /**
     * 要多行注释还单行注释
     * multiline comment 多行注释
     * End-of-line comment  行尾注释
     */
    private boolean commentFormatAsEndOfLine = false;//End-of-line comment  行尾注释

    @Deprecated
    private boolean onlyEnableEndOfLineCommentAndDisableMultilineComment = false;

    private boolean success;

    @Deprecated
    private boolean useProjectDefaultDataSource;// true=使用项目默认的数据源/false=使用用户指定的数据源

    public String getAuthor() {
        if (this.author == null) {
            author = System.getProperty("user.name");
        }
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
