package tw.yukina.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.AotProxyHint;
import org.springframework.nativex.hint.ProxyBits;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import tw.yukina.agent.controller.Agent;

@SpringBootApplication
@AotProxyHint(targetClass= Agent.class, proxyFeatures = ProxyBits.IS_STATIC)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
