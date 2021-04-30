package ${rootNamespace};

import lombok.extern.slf4j.Slf4j;
import ${classFullName.modelNamespace};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
<#if dateTypeExists>
    import java.util.Date;
</#if>

/**
* ${tableComment}接口
*
* @author ${author?default("")}
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
@Slf4j
public class ${className}Controller {

    @Autowired
    private ${className}Service ${lowerCaseFirstWordClassName}Service;

    /**
    * 保存一条数据并返回数据的主键
    *
    * @param ${lowerCaseFirstWordClassName} 实体
    * @return 主键 PK
    */
    @PostMapping("/save")
    @ResponseBody
    org.springframework.http.ResponseEntity<Long> save(@RequestBody ${className} ${lowerCaseFirstWordClassName}){
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.save(${lowerCaseFirstWordClassName}));
    }

    /**
    * 通过主键删除一条数据
    *
    * @param id 主键
    * @return 影响数据条数
    */
    @PostMapping("/delete")
    @ResponseBody
    org.springframework.http.ResponseEntity<Boolean> delete(Long id){
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.delete(id));
    }

    /**
    * 更新一条数据
    *
    * @param ${lowerCaseFirstWordClassName}
    * @return 影响数据条数
    */
    @PostMapping("/update")
    @ResponseBody
    org.springframework.http.ResponseEntity<Boolean> update(@RequestBody ${className} ${lowerCaseFirstWordClassName}) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.update(${lowerCaseFirstWordClassName}));
    }

    /**
    * 通过主键查询一条数据
    *
    * @param id 主键
    * @return 返回一条数据
    */
    @PostMapping("/get")
    @ResponseBody
     org.springframework.http.ResponseEntity<${className}> getById(Long id) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.getById(id));
    }

    /**
    * 条件查询数据列表
    *
    * @return 数据集合
    */
    @PostMapping("/list")
    @ResponseBody
    org.springframework.http.ResponseEntity<List<${className}>> list(${className} query) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.list(query));
    }

    /**
    * 分页条件查询数据列表
    *
    * @param request
    * @return 数据集合
    */
    @PostMapping("/page")
    @ResponseBody
    org.springframework.http.ResponseEntity<List<${className}>> listPage(@RequestBody ${className} query) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.listPage(query));
    }

    // todo 处理是否可选输出批量接口?
    /**
    * 批量插入
    *
    * @param ${lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    @PostMapping("/save-batch")
    @ResponseBody
    org.springframework.http.ResponseEntity<Integer> saveBatch(List<${className}> ${lowerCaseFirstWordClassName}s) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.saveBatch(${lowerCaseFirstWordClassName}s));
    }

    /**
    * 批量删除
    *
    * @param ids 主键集合
    * @return 影响条数
    */
    @PostMapping("/delete-id-in")
    @ResponseBody
    org.springframework.http.ResponseEntity<Integer> deleteIdIn(List<Long> ids) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.deleteIdIn(ids));
    }

    /**
    * 批量更新
    *
    * @param ${lowerCaseFirstWordClassName}s 数据集合
    * @return 影响条数
    */
    @PostMapping("/update-batch")
    @ResponseBody
    org.springframework.http.ResponseEntity<Integer> updateBatch(List<${className}> ${lowerCaseFirstWordClassName}s) {
        return org.springframework.http.ResponseEntity.ok(${lowerCaseFirstWordClassName}Service.updateBatch(${lowerCaseFirstWordClassName}s));
    }

}
