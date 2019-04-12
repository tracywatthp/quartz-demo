package com.tinxi.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLException;

@ConfigurationProperties("spring.datasource")
public class CommonDruidDataSource extends DruidDataSource {

	public CommonDruidDataSource() {
		this.setMinEvictableIdleTimeMillis(300000);
		this.setTestOnBorrow(true);
		this.setTestOnReturn(false);
		this.setValidationQuery("SELECT 1");
		this.setTestWhileIdle(true);
		this.setMaxWait(10000);
		this.setMinIdle(1);
		this.setRemoveAbandonedTimeout(300);
		this.setRemoveAbandoned(true);
		this.setLogAbandoned(true);
		this.setTimeBetweenEvictionRunsMillis(60000);
//        try {
//            this.setFilters("stat,wall,config");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        Properties properties = new Properties();
//        properties.setProperty("druid.stat.mergeSql", "true");
//        properties.setProperty("druid.stat.slowSqlMillis", "1000");
//        properties.setProperty("config.decrypt", "false");
//        this.setConnectProperties(properties);
//        this.setUseGlobalDataSourceStat(true);
		this.setMaxPoolPreparedStatementPerConnectionSize(100);

		//对支持游标的数据库性能提升巨大，比如说oracle。在mysql5.5下建议关闭
		this.setPoolPreparedStatements(true);
	}

	@PostConstruct
	@Override
	public void init() throws SQLException {
		super.init();
	}

	@PreDestroy
	@Override
	public void close() {
		super.close();
	}
}
