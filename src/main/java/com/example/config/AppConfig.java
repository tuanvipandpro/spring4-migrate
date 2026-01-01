package com.example.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.model.User;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@ComponentScans(value = {
      @ComponentScan("com.example.dao"),
      @ComponentScan("com.example.service"),
      @ComponentScan("com.example.view")
})
@org.springframework.context.annotation.Import({ SecurityConfig.class })
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource getDataSource() {
      org.springframework.jndi.JndiObjectFactoryBean bean = new org.springframework.jndi.JndiObjectFactoryBean();
      bean.setJndiName(env.getProperty("jndi.name"));
      bean.setProxyInterface(DataSource.class);
      bean.setLookupOnStartup(false);
      try {
         bean.afterPropertiesSet();
      } catch (Exception e) {
         throw new RuntimeException("Failed to lookup JNDI DataSource", e);
      }
      return (DataSource) bean.getObject();
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(getDataSource());

      Properties props = new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

      factoryBean.setHibernateProperties(props);
      factoryBean.setAnnotatedClasses(User.class);
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }
}
