package com.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
@EnableCassandraRepositories(basePackages = {"com.api"})
public class CassandraConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class);

	@Autowired
    private Environment environment;
    
	@Bean
    public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		LOGGER.info( String.format("Connecting cassandra DB with hostName:%s, Port:%s.", environment
				.getProperty("cassandra.contactpoints"), environment
						.getProperty("cassandra.port")));
		
		cluster.setContactPoints(environment
				.getProperty("cassandra.contactpoints"));
		cluster.setPort(Integer.parseInt(environment
				.getProperty("cassandra.port")));
		return cluster;
    }
    
	@Bean
    public CassandraMappingContext mappingContext() {
        return new BasicCassandraMappingContext();
    }
    
	@Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }
    
	@Bean
    public CassandraSessionFactoryBean session() throws Exception {
		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster().getObject());
		LOGGER.info( String.format("Connecting cassandra DB with hostName:%s, Port:%s and keyspace=%s.", environment
				.getProperty("cassandra.contactpoints"), environment
						.getProperty("cassandra.port"), environment.getProperty("cassandra.keyspace")));
		
		session.setKeyspaceName(environment.getProperty("cassandra.keyspace"));
		session.setConverter(converter());
		session.setSchemaAction(SchemaAction.NONE);
		return session;
    }
    
	@Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}