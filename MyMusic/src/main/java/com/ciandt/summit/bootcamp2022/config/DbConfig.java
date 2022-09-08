package com.ciandt.summit.bootcamp2022.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class DbConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:MyMusic.db");
        dataSource.setUsername(env.getProperty(""));
        dataSource.setPassword(env.getProperty(""));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.ciandt.summit.bootcamp2022.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        String hibernateAuto = "hibernate.hbm2ddl.auto";
        String hibernateDialect = "hibernate.dialect";
        String hibernateSql = "hibernate.show_sql";
        if (env.getProperty(hibernateAuto) != null) {
            hibernateProperties.setProperty(hibernateAuto, env.getProperty(hibernateAuto));
        }
        if (env.getProperty(hibernateDialect) != null) {
            hibernateProperties.setProperty(hibernateDialect, env.getProperty(hibernateDialect));
        }
        if (env.getProperty(hibernateSql) != null) {
            hibernateProperties.setProperty(hibernateSql, env.getProperty(hibernateSql));
        }
        return hibernateProperties;
    }

}
