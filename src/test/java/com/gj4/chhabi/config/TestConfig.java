package com.gj4.chhabi.config;

import com.gj4.chhabi.spring.ContextConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@Configuration
@ComponentScan(basePackages = {"com.gj4.chhabi"})
@Import({
        ContextConfig.class,
})
public class TestConfig {
}
