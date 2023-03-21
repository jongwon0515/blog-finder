package com.blogfinder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = { "com.blogfinder" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableConfigurationProperties
@EnableCaching
@EnableAsync
public class MudulesApplicationTests {
}