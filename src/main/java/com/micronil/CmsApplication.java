package com.micronil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration

public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

//	@Autowired
//	private EntityManager entityManager;
//
//	@Bean
//	public SessionFactory getSessionFactory() {
//		if (entityManager.unwrap(SessionFactory.class) == null) {
//			throw new  NullPointerException("factory is not a hibernate factory");
//		}
//		return entityManager.unwrap(SessionFactory.class);
//	}
}
