server:
  port: 8004
#  servlet:
#    context-path: /demo

spring:
  profiles:
    active: dev


mybatis:
  mapper-locations: classpath:mapping/*Mapper.xml
  type-aliases-package: com.arc.code.model.domain.demo


log:
  dir: ./log