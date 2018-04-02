package com.micronil;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import javax.persistence.EntityManagerFactory;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@EnableAutoConfiguration
public class HibernateConfig {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf){
        HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
        fact.setEntityManagerFactory(emf);
        return fact;
    }
}
