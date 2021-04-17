package ${javaPackage};

import lombok.extern.slf4j.Slf4j;
import ${modelNamespace} .${meta.className} ;
import com.arc.test.model.request.${meta.className}Request;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
<#if meta.dateTypeExists>
    import java.util.Date;
</#if>

/**
* ${meta.tableComment}接口
*
* @author ${author?default("叶超")}
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
@Slf4j
public class ${meta.className}Controller {

    @Autowired
    private ${meta.className}Service ${meta.lowerCaseFirstWordClassName}Service;

    /**
    * 保存一条数据并返回数据的主键
    *
    * @param ${meta.lowerCaseFirstWordClassName} 实体
    * @return 主键 PK
    */
    ResponseVo<Long> save(${meta.className} ${meta.lowerCaseFirstWordClassName}){
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.save(${meta.lowerCaseFirstWordClassName}));
    }

    /**
    * 通过主键删除一条数据
    *
    * @param id 主键
    * @return 影响数据条数
    */
    ResponseVo<int> delete(Long id){
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.delete(id));
    }

    /**
    * 更新一条数据
    *
    * @param ${meta.lowerCaseFirstWordClassName}
    * @return 影响数据条数
    */
     ResponseVo<int> update(${meta.className} ${meta.lowerCaseFirstWordClassName}) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.update(${meta.lowerCaseFirstWordClassName}));
    }

    /**
    * 通过主键查询一条数据
    *
    * @param id 主键
    * @return 返回一条数据
    */
     ResponseVo<${meta.className}> get(Long id) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.get(id));
    }

    /**
    * 无条件查询全部数据
    *
    * @return 全部数据
    */
    ResponseVo<List<${meta.className}>> list() {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.list());
    }

    /**
    * 条件查询数据列表
    *
    * @return 数据集合
    */
     ResponseVo<List<${meta.className}>> list(${meta.className}Request request) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.list(request));
    }

    /**
    * 分页条件查询数据列表
    *
    * @param request
    * @return 数据集合
    */
    ResponseVo<List<${meta.className}>> listPage(${meta.className}Request request) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.listPage(request));
    }

    /**
    * 批量插入
    *
    * @param ${meta.lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    ResponseVo<Integer> saveBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.saveBatch(${meta.lowerCaseFirstWordClassName}s));
    }

    /**
    * 批量删除
    *
    * @param ids 主键集合
    * @return 影响条数
    */
    ResponseVo<Integer> deleteIdIn(List<Long> ids) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.deleteIdIn(ids));
    }

    /**
    * 批量更新
    *
    * @param ${meta.lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    ResponseVo<Integer> updateBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s) {
        return ResponseVo.success(${meta.lowerCaseFirstWordClassName}Service.updateBatch(${meta.lowerCaseFirstWordClassName}s));
    }

}
