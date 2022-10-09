package com.github.ilyasyoy.springmutableproperties;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class, properties = {"test.map.base=base"})
class SpringMutablePropertiesApplicationTests {

    @Test
    void contextLoads(@Autowired Properties properties) {
        assertNotNull(properties);
        assertNotNull(properties.getMap());
        assertEquals(1, properties.getMap().size());
        assertEquals("base", properties.getMap().get("base"));
    }

    @Nested
    @SpringBootTest(classes = Application.class, properties = {"test.map.first=first"})
    class FirstTest {
        @Test
        void propertiesCheck(@Autowired Properties properties) {
            assertNotNull(properties);
            assertNotNull(properties.getMap());
            assertEquals(1, properties.getMap().size());
            assertEquals("first", properties.getMap().get("first"));
        }
    }

    @Nested
    @SpringBootTest(classes = Application.class, properties = {"test.map.second=second"})
    class SecondTest {
        @Test
        void propertiesCheck(@Autowired Properties properties) {
            assertNotNull(properties);
            assertNotNull(properties.getMap());
            assertEquals(1, properties.getMap().size());
            assertEquals("second", properties.getMap().get("second"));
        }
    }
}

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Getter
@Setter
@ConfigurationProperties(prefix = "test")
class Properties {
    private Map<String, String> map = new HashMap<>();
}