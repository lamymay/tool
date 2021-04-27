package com.arc.code.generator.model.domain.meta;

import com.arc.code.generator.utils.NameUtil;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 表的元元素描述数据
 *
 * @author 叶超
 * @since 2019/10/3 14:45
 */
@ToString
public class TableMeta implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库名
     */
    private String tableSchema;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 列
     */
    private List<ColumnMeta> columns;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnMeta> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnMeta> columns) {
        this.columns = columns;
    }

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

    public String getClassName(String removePrefix) {
        String name = null;
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

    @Override
    public String toString() {
        return "TableMeta{" +
                "tableName='" + tableName + '\'' +
                ", tableSchema='" + tableSchema + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", columns=" + columns +
                '}';
    }
}
