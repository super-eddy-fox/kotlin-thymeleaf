package com.example.thymeleaf.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class WebSecurityConfig(private val dataSource: DataSource,private val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/","/css/**","/account/register","/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/account/login")
                .permitAll()
                .and()
            .logout()
                .permitAll()
    }

    @Autowired
    @Throws(java.lang.Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .withDefaultSchema()
//            .withUser(User.withUsername("user")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER"))

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            .usersByUsernameQuery("select user_name,password,enabled "
                    + "from user "
                    + "where user_name = ?")
            .authoritiesByUsernameQuery("select u.user_name,r.name "
                    + "from user_role ur inner join user u on ur.user_id = u.id "
                    + "inner join role r on ur.role_id = r.id "
                    + "where u.user_name = ?");
    }




}