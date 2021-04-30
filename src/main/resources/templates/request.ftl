package ${rootNamespace};

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * ${tableComment}
 *
 * @author ${author?default("may")}
 * @since ${(createTime?string("yyyy-MM-dd HH:mm:ss"))!}
 */
@Getter
@Setter
public class ${className}Response implements Serializable {

	private static final long serialVersionUID = 1L;

<#list columns as col>
	private ${col.modelFieldType} ${col.fieldName};// ${col.columnComment}
</#list>
}
