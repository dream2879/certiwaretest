package com.certiware.backend;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.certiware.backend.config.JwtFilter;
import com.certiware.backend.config.RoleFilter;
import com.certiware.backend.service.MainService;


@SpringBootApplication
@MapperScan(value={"com.certiware.backend.mapper"})
public class CertiwareApplication {	
	
	@Bean
	@Autowired
	public RoleFilter roleFilter(MainService mainService) throws Exception {
		RoleFilter roleFilter = new RoleFilter();
		roleFilter.setSelectMenuModels(mainService);
		return roleFilter;		
	}
	
	
	@Bean	
	public FilterRegistrationBean jwtFilter(RoleFilter roleFilter) throws Exception {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter(roleFilter));
		//registrationBean.addUrlPatterns("/rest/*");
		registrationBean.addUrlPatterns("/*");
		
		return registrationBean;
	}
	
	/**
	 * import org.apache.ibatis.session.SqlSessionFactory;
	 * import javax.sql.DataSource;
	 * import org.mybatis.spring.SqlSessionFactoryBean;
	 */	
	@Bean	
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		return sessionFactory.getObject();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(CertiwareApplication.class, args);
		//ApplicationContext app = SpringApplication.run(CertiwareApplication.class, args);	
		//RoleFilter roleFilter = (RoleFilter) app.getBean(RoleFilter.class);
	}
}
