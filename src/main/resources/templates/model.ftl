package ${javaPackage};

<#if meta.dateTypeExists>
import java.util.Date;
</#if>
import lombok.Getter;
import lombok.Setter;

/**
 * ${meta.tableComment}
 *
 * @author ${meta.author?default("lamy")}
 * @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
 */
@Getter
@Setter
<#--public class ${meta.className} extends BaseModel {-->
public class ${meta.className} {

	private static final long serialVersionUID = 1L;

<#list meta.columns as col>

    <#if true>
     /**
    *  ${col.columnComment}
    */
    </#if>
    private ${col.modelFieldType} ${col.fieldName};<#if false>// ${col.columnComment}</#if>
</#list>
}
