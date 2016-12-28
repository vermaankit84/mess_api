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
        http.httpBasic()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createBuffer/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateBuffer/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/deleteBuffer/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createDivision/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateDivision/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/deleteDivision/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createSender/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateSender/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/deleteSender/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createTemplate/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateTemplate/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/deleteTemplate/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createVendor/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateVendor/*").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/deleteVendors/").hasRole(UserType.WRITE.name())
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/get*").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/createUser/*").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/updateUser/*").hasRole(UserType.WRITE.name())
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        final List<UserDetails> userDetailsList = userService.getUserDetails();
        logger.info("User Details obtained [ " + userDetailsList + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        for (final UserDetails u : userDetailsList) {
            auth.inMemoryAuthentication().withUser(u.getUserName()).password(u.getUserPass()).roles(u.getUserType().name());
        }
    }
}
