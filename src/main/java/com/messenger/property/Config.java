package com.messenger.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class Config {
    @Autowired
    private Environment environment = null;

    public String getValue(final String key) {
        return environment.getProperty(key);
    }
}
