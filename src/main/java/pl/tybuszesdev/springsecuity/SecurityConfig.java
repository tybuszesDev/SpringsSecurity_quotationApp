package pl.tybuszesdev.springsecuity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        {
            UserDetails user = User.withDefaultPasswordEncoder()     //LOGIN AND PASSWORD IN PLAIN TEXT !!! BE CAREFULL
                    .username("user")                                //USER ACOOUNT
                    .password("user1")
                    .roles("USER")
                    .build();

            UserDetails admin = User.withDefaultPasswordEncoder()     //LOGIN AND PASSWORD IN PLAIN TEXT !!! BE CAREFULL
                    .username("admin")                                //ADMIN ACOOUNT
                    .password("admin1")
                    .roles("ADMIN")
                    .build();
        return new InMemoryUserDetailsManager(user, admin);

    }


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {                //METHOD TO FILTER REQUESTS FROM USER
        http.authorizeRequests()                                                 //NEED INTERACTION ON REQUEST
                .antMatchers("/hello")                                 //TAKE PART OF URL AND CHECK SECURITY
                .permitAll().anyRequest().hasRole("ADMIN")                        //"/hello" FOR EVERY USER (permitALL) BUT ANY OTHER REQUEST NEED ROLE "admin"
                .and()
                .formLogin().permitAll();                                         //loginForm from SpringSecurity
    }
}
