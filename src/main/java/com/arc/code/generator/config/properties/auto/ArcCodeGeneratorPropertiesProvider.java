package com.arc.code.generator.config.properties.auto;


import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自动读取配置文件中配置，『抽取』
 *
 * @author lamymay/lamy/may/x/XL/叶超
 * @since: 2019/5/8 22:19
 */
@Getter
@ToString
@ConfigurationProperties(prefix = "arc.generator")
public class ArcCodeGeneratorPropertiesProvider {

    private DataSourceProperties dataSource = new DataSourceProperties();
    private DatabaseProperties database = new DatabaseProperties();
    private ProjectProperties project = new ProjectProperties();


    public DataSourceProperties getDataSourceProperties() {
        return this.dataSource;
    }

    public DatabaseProperties getDatabaseProperties() {
        return this.database;
    }

    public ProjectProperties getProjectProperties() {
        return this.project;
    }

}
