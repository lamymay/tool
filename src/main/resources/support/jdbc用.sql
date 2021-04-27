select t.table_schema,
       t.table_name,
       t.table_comment,
       c.table_schema,
       c.table_name,
       c.column_name,
       c.column_key,
       c.data_type,
       c.column_comment,
       c.ordinal_position,
       c.column_default,
       c.is_nullable
from information_schema.`tables` t
         inner join information_schema.columns c on t.table_schema = c.table_schema and t.table_name = c.table_name
where t.table_schema = ?
  and t.table_name = ?;


select t.table_schema,t.table_name,t.table_comment,
       c.table_schema,c.table_name,c.column_name,c.column_key,c.data_type,c.column_comment,c.ordinal_position,c.column_default,c.is_nullable
from information_schema.`tables` t
inner join information_schema.columns c on t.table_schema = c.table_schema and t.table_name = c.table_name
where t.table_schema = ? and t.table_name = ?;



select t.table_schema     AS TABLE_SCHEMA,
       t.table_name       AS TABLE_NAME,
       t.table_comment    AS TABLE_COMMENT,

       c.table_schema     AS COL_TABLE_SCHEMA,
       c.table_name       AS COL_TABLE_NAME,
       c.column_name      AS COL_COLUMN_NAME,
       c.column_key       AS COL_COLUMN_KEY,
       c.data_type        AS COL_DATA_TYPE,
       c.column_comment   AS COL_COLUMN_COMMENT,
       c.ordinal_position AS COL_ORDINAL_POSITION,
       c.column_default   AS COL_COLUMN_DEFAULT,
       c.is_nullable      AS COL_IS_NULLABLE


from information_schema.`tables` t
         inner join information_schema.columns c on t.table_schema = c.table_schema and t.table_name = c.table_name
where t.table_schema = ?
  and t.table_name = ?;;


/**
 * @param tableSchema 数据库名称
 * @param tableName   表名称
 * @return
 */
public static String getSqlSelectForTableMate(String tableSchema, String tableName) {
        return "select " +
                "t.table_schema, " +
                "t.table_name, " +
                "t.table_comment, " +
                "c.table_schema, " +
                "c.table_name, " +
                "c.column_name, " +
                "c.column_key, " +
                "c.data_type, " +
                "c.column_comment, " +
                "c.ordinal_position, " +
                "c.column_default, " +
                "c.is_nullable " +
                "from " +
                "information_schema.`tables`  t " +
                "inner join information_schema.columns c on t.table_schema = c.table_schema and t.table_name = c.table_name " +
                "where " +
                "t.table_schema = " + tableSchema + " and t.table_name = " + tableName;
}
