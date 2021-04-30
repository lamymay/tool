package ${rootNamespace};

import lombok.extern.slf4j.Slf4j;
import ${classFullName.modelNamespace};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

/**
* ${tableComment}服务
*
* @author ${author?default("")}
* @since ${(createTime ? string("yyyy-MM-dd HH:mm:ss"))!}
*/
@Slf4j
@Service
public class ${className}ServiceImpl implements ${className}Service {

@Resource
private ${className}Mapper ${lowerCaseFirstWordClassName}Mapper;

@Override
public Long save(${className} ${lowerCaseFirstWordClassName}) {
return ${lowerCaseFirstWordClassName}Mapper.save(${lowerCaseFirstWordClassName}) == 1 ? ${lowerCaseFirstWordClassName}.getId() : null;
}

@Override
public int delete(Long id) {
return ${lowerCaseFirstWordClassName}Mapper.delete(id);
}

@Override
public int update(${className} ${lowerCaseFirstWordClassName}) {
return ${lowerCaseFirstWordClassName}Mapper.update(${lowerCaseFirstWordClassName});
}

@Override
public ${className} getById(Long id) {
return ${lowerCaseFirstWordClassName}Mapper.get(id);
}

<#--    @Override-->
<#--    public List<${className}> list() {-->
<#--        return ${lowerCaseFirstWordClassName}Mapper.list();-->
<#--    }-->

@Override
public List<${className}> list(${className}Request ${lowerCaseFirstWordClassName}Request) {
return ${lowerCaseFirstWordClassName}Mapper.list();
}

@Override
public List<${className}> listPage(${className}Request ${lowerCaseFirstWordClassName}Request) {
return listPage(${lowerCaseFirstWordClassName}Request);
}

<#--    @Override-->
<#--    public Integer saveBatch(List<${className}> ${lowerCaseFirstWordClassName}s) {-->
<#--        return ${lowerCaseFirstWordClassName}Mapper.saveBatch(${lowerCaseFirstWordClassName}s);-->
<#--    }-->

<#--    @Override-->
<#--    public Integer deleteIdIn(List<Long> ids) {-->
<#--        return ${lowerCaseFirstWordClassName}Mapper.deleteIdIn(ids);-->
<#--    }-->

<#--    @Override-->
<#--    public Integer updateBatch(List<${className}> ${lowerCaseFirstWordClassName}s) {-->
<#--        return ${lowerCaseFirstWordClassName}Mapper.updateBatch(${lowerCaseFirstWordClassName}s);-->
<#--    }-->
}
