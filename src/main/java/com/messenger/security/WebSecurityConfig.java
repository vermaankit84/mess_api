package com.messenger.security;

import com.messenger.bean.UserDetails;
import com.messenger.service.UserService;
import com.messenger.types.UserType;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = Logger.getLogger(WebSecurityConfig.class);
    @Autowired
    private UserService userService = null;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, "/create*").hasRole(UserType.ADMIN.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/update*").hasRole(UserType.ADMIN.name())
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/get*").permitAll().and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/update*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/delete*").hasRole(UserType.WRITE.name())
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        final List<UserDetails> userDetailsList = userService.getUserDetails();
        logger.info("User Details obtained [ " + userDetailsList + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        for (final UserDetails u : userDetailsList) {
            auth.inMemoryAuthentication().withUser(u.getUserName()).password(u.getUserPass()).roles(u.getUserType().toString());
        }
    }
}
