package ${rootNamespace};

<#if meta.dateTypeExists>
import java.util.Date;
</#if>
import lombok.Getter;
import lombok.Setter;

/**
 * ${meta.tableComment}
 *
 * @author ${author?default("叶超")}
 * @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
 */
@Getter
@Setter
public class ${meta.className}Response {

	private static final long serialVersionUID = 1L;

<#list meta.columns as col>
	private ${col.modelFieldType} ${col.fieldName};// ${col.columnComment}
</#list>
}
