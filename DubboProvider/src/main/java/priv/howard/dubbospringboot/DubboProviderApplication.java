package priv.howard.dubbospringboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//可以手动指定扫描注解的包
@EnableDubbo(scanBasePackages = "priv.howard.dubbospringboot.service")
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
