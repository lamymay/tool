package com.arc.code.generator.model.domain;

import com.arc.code.generator.model.ClassFullName;
import com.arc.code.generator.utils.NameUtil;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 表的元元素描述数据
 *
 * @author 叶超
 * @since 2019/10/3 14:45
 */
@Data
public class TableMeta implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 首字母小写的类名
     */
    private String lowerCaseFirstWordClassName;

    /**
     * 列
     */
    private List<ColumnMeta> columns;

    /**
     * 数据库名
     */
    @Deprecated
    private String tableSchema;
    /**
     * todo 导入时间
     */
    private boolean importDate = true;


    private Date createTime = new Date();


    private String author;
    private String tableAlias;
    private String javaPackage;


    @Deprecated
    private String rootNamespace;

    /**
     * mapper名称
     */
    @Deprecated
    private String mapperName;

    /**
     * 所有的全限定名称用此类封装
     */
    private ClassFullName classFullName;


    @Deprecated
    public String getClassName() {
        String name = null;
        if (tableName.startsWith("t_")) {
            name = tableName.substring(2);
        } else if (tableName.startsWith("sys_")) {
            name = tableName.substring(4);
        }

        if (name != null) {
            String[] arr = name.split("_");
            StringBuilder sb = new StringBuilder();
            for (String s : arr) {
                sb.append(NameUtil.upperCaseFirstWord(s));
            }
            return sb.toString();
        }

        return tableName;
    }

    /**
     * 获取类名称
     *
     * @param removePrefix 需要去掉的前缀
     * @return 优化前缀的类名称
     */
    public String getClassName(@Nullable String removePrefix) {
        String name = tableName;
        if (removePrefix != null) {
            if (tableName.startsWith(removePrefix)) {
                name = tableName.substring(removePrefix.length());
            }
        }

        if (name != null) {
            String[] arr = name.split("_");
            StringBuilder sb = new StringBuilder();
            for (String s : arr) {
                sb.append(NameUtil.upperCaseFirstWord(s));
            }
            return sb.toString();
        }

        return tableName;
    }

    public String getLowerCaseFirstWordClassName() {
        return NameUtil.lowerCaseFirstWord(getClassName());
    }

    public boolean isDateTypeExists() {
        for (ColumnMeta col : columns) {
            if (col.getDataType().equalsIgnoreCase("date")
                    || col.getDataType().equalsIgnoreCase("datetime")
                    || col.getDataType().equalsIgnoreCase("time")
                    || col.getDataType().equalsIgnoreCase("timestamp")) {
                return true;
            }
        }
        return false;
    }

    public String getMapperName() {
        return getClassName() + "Mapper";
    }

    public String getResultMapId() {
        return NameUtil.lowerCaseFirstWord(getClassName());
    }

}


//    private String mapperXmlOutputFileName = className + "Mapper.xml";
//    private String modelOutputFileName = className+".java";
//    private String mapperInterfaceOutputFileName = className + "Mapper.java";
//
//    private String serviceOutputFileName = className + "Service.java";
//    private String serviceImplOutputFileName = className + "ServiceImpl.java";
//
//    private String controllerOutputFileName = className + "Controller.java";
//
//    private String requestOutputFileName = className + "Request.java";
//    private String responseOutputFileName = className + "Response.java";
