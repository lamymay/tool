package ${rootNamespace};

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * ${tableComment}
 *
 * @author ${author?default("")}
 * @since ${(createTime ? string("yyyy-MM-dd HH:mm:ss"))!}
 */
@Getter
@Setter
public class ${className}Request implements Serializable {

	private static final long serialVersionUID = 1L;

<#list columns as col>
	private ${col.modelFieldType} ${col.fieldName};// ${col.columnComment}
</#list>
}
