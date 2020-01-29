package pl.tybuszesdev.springsecuity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.Elements.HTTP;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        {
            UserDetails moderator = User.withDefaultPasswordEncoder()     //LOGIN AND PASSWORD IN PLAIN TEXT !!! BE CAREFULL
                    .username("user")                                //USER ACOOUNT
                    .password("user1")
                    .roles("MODERATOR")
                    .build();

            UserDetails admin = User.withDefaultPasswordEncoder()     //LOGIN AND PASSWORD IN PLAIN TEXT !!! BE CAREFULL
                    .username("admin")                                //ADMIN ACOOUNT
                    .password("admin1")
                    .roles("ADMIN")
                    .build();
        return new InMemoryUserDetailsManager(moderator, admin);

    }


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {                                          //METHOD TO FILTER REQUESTS FROM USER
        http.httpBasic().and().authorizeRequests()                                                          //NEED INTERACTION ON REQUEST ALLOW APPS TO LOGIN USING login AND password
                .antMatchers(HttpMethod.GET,"/api").permitAll()                                 //TAKE PART OF URL AND CHECK SECURITY, ALL CAN GET DATA
                .antMatchers(HttpMethod.POST,"/api").hasAnyRole("MODERATOR","ADMIN")     //MODERATORS AND ADMINS CAN POST DATA
                .antMatchers(HttpMethod.DELETE,"/api").hasRole("ADMIN")                         //ONLY ADMINS CAN DELETE DATA
                .and()
                .formLogin().permitAll()                                                                     //loginForm from SpringSecurity
                .and()
                .logout().permitAll()                                                                       //made logout on path /logout for every user
                .and()
                .csrf().disable();                                                                          //disable CSRF to allow postman
    }
}
