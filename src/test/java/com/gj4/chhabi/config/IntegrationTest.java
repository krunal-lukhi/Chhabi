package com.gj4.chhabi.config;

import com.gj4.chhabi.ChhabiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Krunal Lukhi
 * @since 21/07/24
 */
@SpringJUnitConfig@SpringBootTest(
        classes = ChhabiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(classes = {TestConfig.class})
public class IntegrationTest extends AbstractJUnit4SpringContextTests {
}
