package com.certiware.backend;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.certiware.backend.config.JwtFilter;

@SpringBootApplication
@MapperScan(value={"com.certiware.backend.mapper"})
public class CertiwareApplication {
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");
		//registrationBean.addUrlPatterns("/*");
		
		return registrationBean;
	}
	

	@Bean
	/**
	 * import org.apache.ibatis.session.SqlSessionFactory;
	 * import javax.sql.DataSource;
	 * import org.mybatis.spring.SqlSessionFactoryBean;
	 */	
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}

	public static void main(String[] args) {
		SpringApplication.run(CertiwareApplication.class, args);
	}
}
