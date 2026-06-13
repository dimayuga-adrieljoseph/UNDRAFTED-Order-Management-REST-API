package snippet;

public class Snippet {
	spring.datasource.url=jdbc:mysql://localhost:3306/app_dev
	spring.datasource.username=root
	spring.datasource.password=
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.format_sql=true
	
	# MyBatis config
	mybatis.mapper-locations=classpath:mappers/*.xml
	mybatis.type-aliases-package=com.pup.taguig.app.model
	
	
	# Root log level
	logging.level.root=DEBUG
	
	# Spring logs
	#logging.level.org.springframework=DEBUG
	
	# MyBatis logs
	logging.level.org.apache.ibatis=DEBUG
	logging.level.java.sql=DEBUG
	
	mybatis.configuration.log-impl=org.apache.ibatis.logging.log4j2.Log4j2Impl
}

