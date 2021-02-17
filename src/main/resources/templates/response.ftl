package ${javaPackage};

<#if meta.dateTypeExists>
import java.util.Date;
</#if>
import lombok.Getter;
import lombok.Setter;

/**
 * ${meta.tableComment}
 *
 * @author lamy
 * @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
 */
@Getter
@Setter
public class ${meta.className}Request {

	private static final long serialVersionUID = 1L;

<#list meta.columns as col>
	private ${col.modelFieldType} ${col.fieldName};// ${col.columnComment}
</#list>
}
