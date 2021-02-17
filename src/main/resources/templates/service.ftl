package ${javaPackage};

import lombok.extern.slf4j.Slf4j;
import ${modelNamespace} .${meta.className} ;
import com.arc.test.model.request.${meta.className}Request;
import java.util.List;
<#if meta.dateTypeExists>
    import java.util.Date;
</#if>
/**
* ${meta.tableComment}服务
*
* @author lamy
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
public interface ${meta.className}Service {

    /**
    * 保存一条数据并返回数据的主键
    *
    * @param ${meta.lowerCaseFirstWordClassName} 实体
    * @return 主键 PK
    */
    Long save(${meta.className} ${meta.lowerCaseFirstWordClassName});

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
    * @param ${meta.lowerCaseFirstWordClassName}
    * @return 影响数据条数
    */
    int update(${meta.className} ${meta.lowerCaseFirstWordClassName});

    /**
    * 通过主键查询一条数据
    *
    * @param id 主键
    * @return 返回一条数据
    */
    ${meta.className} get(Long id);

    /**
    * 无条件查询全部数据
    *
    * @return 全部数据
    */
    List<${meta.className}> list();

    /**
    * 条件查询数据列表
    *
    * @return 数据集合
    */
    List<${meta.className}> list(${meta.className}Request ${meta.lowerCaseFirstWordClassName}Request);

    /**
    * 分页条件查询数据列表
    *
    * @param ${meta.lowerCaseFirstWordClassName}Request
    * @return 数据集合
    */
    List<${meta.className}> listPage(${meta.className}Request ${meta.lowerCaseFirstWordClassName}Request);

    /**
    * 批量插入
    *
    * @param ${meta.lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    Integer saveBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s);

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
    * @param ${meta.lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    Integer updateBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s);
}
