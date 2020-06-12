package com.gemini8.app.config;

import com.gemini8.app.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";

        http.
                authorizeRequests()
                .antMatchers("/index").hasAnyAuthority("ASTRONOMER","OBSERVER")
                .antMatchers("/createPlan").hasAuthority("ASTRONOMER")
                .antMatchers("/addLen").hasAuthority("ASTRONOMER")
                .antMatchers("/addFilter").hasAuthority("ASTRONOMER")
                .antMatchers("/addEquipment").hasAuthority("ASTRONOMER")
                .antMatchers("/dataProc").hasAuthority("ASTRONOMER")
                .antMatchers("/operate").hasAuthority("ASTRONOMER")
                .antMatchers("/submitPlan").hasAuthority("ASTRONOMER")
                .antMatchers("/validatePlan").hasAuthority("OBSERVER")
                .antMatchers(loginPage).permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage(loginPage)
                //.loginPage("/")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/index",true)
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage).and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/h2-console/**", "/error");
    }

}