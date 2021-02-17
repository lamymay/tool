package ${javaPackage};

import lombok.extern.slf4j.Slf4j;
import ${modelNamespace} .${meta.className} ;
import ${requestNamespace}.${meta.className}.request.Request;
import ${mapperNamespace}.${meta.className}Mapper;
import ${serviceNamespace}.${meta.className}Service;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
@Slf4j
public class ${meta.className}ServiceImpl implements ${meta.className}Service {

    @Resource
    private ${meta.className}Mapper ${meta.lowerCaseFirstWordClassName}Mapper;

    @Override
    public Long save(${meta.className} ${meta.lowerCaseFirstWordClassName}) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.save(${meta.lowerCaseFirstWordClassName}) == 1 ? ${meta.lowerCaseFirstWordClassName}.getId() : null;
    }

    @Override
    public int delete(Long id) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.delete(id);
    }

    @Override
    public int update(${meta.className} ${meta.lowerCaseFirstWordClassName}) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.update(${meta.lowerCaseFirstWordClassName});
    }

    @Override
    public ${meta.className} get(Long id) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.get(id);
    }

    @Override
    public List<${meta.className}> list() {
        return ${meta.lowerCaseFirstWordClassName}Mapper.list();
    }

    @Override
    public List<${meta.className}> list(${meta.className}Request ${meta.lowerCaseFirstWordClassName}Request) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.list();
    }

    @Override
    public List<${meta.className}> listPage(${meta.className}Request ${meta.lowerCaseFirstWordClassName}Request) {
    return listPage(${meta.lowerCaseFirstWordClassName}Request);
    }

    @Override
    public Integer saveBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.saveBatch(${meta.lowerCaseFirstWordClassName}s);
    }

    @Override
    public Integer deleteIdIn(List<Long> ids) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.deleteIdIn(ids);
    }

    @Override
    public Integer updateBatch(List<${meta.className}> ${meta.lowerCaseFirstWordClassName}s) {
        return ${meta.lowerCaseFirstWordClassName}Mapper.updateBatch(${meta.lowerCaseFirstWordClassName}s);
    }
}
