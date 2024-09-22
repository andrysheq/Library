//package com.example.library.aspects;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//
//@Aspect
//@Component
//@Log4j2
//@RequiredArgsConstructor
//public class JdbcConnectionAspect {
//    private final DataSource dataSource;
//
//    @Before("execution(* com.example.library.repos..*(..))")
//    public void logJdbcConnection() {
//        try (Connection connection = dataSource.getConnection()) {
//            String url = connection.getMetaData().getURL();
//            log.info("Executing query on database with URL: {}", url);
//        } catch (Exception e) {
//            log.error("Error while logging JDBC connection", e);
//        }
//    }
//}
//
