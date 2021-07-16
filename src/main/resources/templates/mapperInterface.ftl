package ${rootNamespace}.mapper;

import ${rootNamespace}.${className};c
import com.arc.test.model.request.${className};
import java.util.List;

<#if importDate>
    import java.util.Date;
</#if>


/**
* ${tableComment}服务
*
* @author ${author?default("")}
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
public interface ${className}Mapper {

/**
* 保存一条数据并返回数据的主键
*
* @param ${lowerCaseFirstWordClassName} 实体
* @return 主键 PK
*/
Long save(${className} ${lowerCaseFirstWordClassName});

/**
* 通过主键删除一条数据
*
* @param id 主键
* @return 影响数据条数
*/
int delete(Long id);

/**
* 更新一条数据
*
* @param ${lowerCaseFirstWordClassName}
* @return 影响数据条数
*/
int update(${className} ${lowerCaseFirstWordClassName});

/**
* 通过主键查询一条数据
*
* @param id 主键
* @return 返回一条数据
*/
${className} get(Long id);

/**
* 条件查询数据列表
*
* @return 数据集合
*/
List<${className}> list(${className} ${lowerCaseFirstWordClassName});

/**
* 分页条件查询数据列表
*
* @param ${lowerCaseFirstWordClassName}
* @return 数据集合
*/
int countForListPage(${className} ${lowerCaseFirstWordClassName});

/**
* 分页条件查询数据列表
*
* @param ${lowerCaseFirstWordClassName}
* @return 数据集合
*/
List<${className}> listPage(${className} ${lowerCaseFirstWordClassName});

<#--    /**
    * 批量插入
    *
    * @param ${lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    Integer saveBatch(List<${className}> ${lowerCaseFirstWordClassName}s);

    /**
    * 批量删除
    *
    * @param ids 主键集合
    * @return 影响条数
    */
    Integer deleteIdIn(List<Long> ids);

    /**
    * 批量更新
    *
    * @param ${lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    Integer updateBatch(List<${className}> ${lowerCaseFirstWordClassName}s);-->
}
