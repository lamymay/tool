
#server:
#  port: 8004
#  servlet:
#    ContextPath must start with '/' and not end with '/'
#    context-path: /code

spring:
  servlet:
    multipart:
      max-file-size: 102400MB
      max-request-size: 102400MB
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: admin
    hikari:
      max-lifetime: 1765000
      maximum-pool-size: 15



mybatis:
  mapper-locations: classpath:mapper/*Mapper.xml
  type-aliases-package: com.arc.code.model.domain.owner


