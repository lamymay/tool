package ${rootNamespace};

<#--<#if meta.meta.dateTypeExists>-->
<#--import java.util.Date;-->
<#--</#if>-->
import lombok.Getter;
import lombok.Setter;

/**
* ${tableComment}
*
* @author ${author?default("叶超")}
* @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
*/
@Getter
@Setter
<#--public class ${meta.className} extends BaseModel {-->
public class ${className} implements Serializable {

private static final long serialVersionUID = 1L;

<#list columns as col>
    <#if true>
    /**
    *  ${col.columnComment}
    */
    </#if>
    private ${col.modelFieldType} ${col.fieldName};<#if false>// ${col.columnComment}</#if>
</#list>
}


