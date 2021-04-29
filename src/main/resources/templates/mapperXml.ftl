<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- Mybatis mapper for ${className}  Auto generated over template -->
<#assign pkColumnName = ''>
<#assign pkFieldName = ''>
<#assign pkMapperJavaType = ''>
<#assign pkMapperJdbcType = ''>
<#macro pound>#</#macro>
<mapper namespace="com.arc.code.generator.mapper.${mapperName}">
	<resultMap id="${className}ResultMap" type="${rootNamespace}.${className}">
	<#list columns as col>
		<#if col.columnKey == 'PRI'>
		<id property="${col.fieldName}" column="${col.columnName}"/>
		<#assign pkColumnName = col.columnName>
		<#assign pkFieldName = col.fieldName>
		<#assign pkMapperJavaType = col.mapperJavaType>
		<#assign pkMapperJdbcType = col.mapperJdbcType>
		<#else>
		<result property="${col.fieldName}" column="${col.columnName}"/>
		</#if>
	</#list>
	</resultMap>

	<sql id="sql${className}AllAllColumns">
	<#list columns as col>
		<#if col_has_next>
		${col.columnName},
		<#else>
		${col.columnName}
		</#if>
	</#list>
	</sql>

	<insert id="save" parameterType="${rootNamespace}.${className}" useGeneratedKeys="true" keyProperty="${pkFieldName}">
		INSERT INTO ${tableName} (
		<#list columns as col>
		<#if col.columnKey != 'PRI' && col.columnName != 'update_time' && col.columnName != 'update_user'>
		<#if col_has_next>
			${col.columnName},
		<#else>
			${col.columnName}
		</#if>
		</#if>
		</#list>
		)
        VALUES (
		<#list columns as col>
		<#if col.columnKey != 'PRI' && col.columnName != 'update_time' && col.columnName != 'update_user'>
		<#if col_has_next>
			<@pound></@pound>{${col.fieldName}},
		<#else>
			<@pound></@pound>{${col.fieldName}}
		</#if>
		</#if>
		</#list>
		)
	</insert>

	<update id="update" parameterType="${rootNamespace}.${className}">
		UPDATE ${tableName}
		<set>
		<#list columns as col>
		<#if col.columnKey != 'PRI' && col.columnName != 'create_time' && col.columnName != 'create_user'>
			<if test="${col.fieldName} != null and ${col.fieldName} != ''">, ${col.columnName} = <@pound></@pound>{${col.fieldName}}</if>
			<if test="${col.fieldName} != null">, ${col.columnName} = <@pound></@pound>{${col.fieldName}}</if>
		</#if>
		</#list>
		</set>
		WHERE ${pkColumnName} = <@pound></@pound>{${pkFieldName}}
	</update>

	<delete id="delete" parameterType="${pkMapperJavaType}">
		DELETE
		FROM ${tableName}
		WHERE ${pkColumnName} = <@pound></@pound>{${pkFieldName}}
	</delete>

	<select id="get" parameterType="${pkMapperJavaType}" resultMap="${className}ResultMap">
		SELECT <include refid="sql${className}AllColumns" />
		FROM ${tableName}
		WHERE ${pkColumnName} = <@pound></@pound>{${pkFieldName}}
	</select>

	<select id="list" parameterType="${pkMapperJavaType}" resultMap="${className}ResultMap">
		SELECT <include refid="sql${className}AllColumns" />
		FROM ${tableName}
	</select>

	<!-- 分页查询用的公共sql-->
	<sql id="sqlCommonWhere">
		<where>
			1 = 1
		</where>
	</sql>

	<select id="countForListPage" parameterType="${rootNamespace}.${className}"
			resultType="int">
		SELECT count(*) FROM ${tableName}
		<include refid="sqlCommonWhere"/>
	</select>

	<select id="listPage" parameterType="${rootNamespace}.${className}" resultMap="${className}ResultMap">
		SELECT <include refid="sql${className}AllColumns" />
		FROM ${tableName}
	</select>

</mapper>
