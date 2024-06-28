package edu.festu.ivankuznetsov.springsamplebo94xpri.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val userDetailsService: UserDetailsService, private val authFilter: JwtAuthFilter) {

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService?>(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }
    //https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl
    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        val build = http
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .cors{
                it.disable()
            }
            .csrf{
                it.disable()
            }
            .httpBasic(withDefaults())
            .formLogin(withDefaults())
            .rememberMe{
                it.tokenValiditySeconds(86400)
            }
            .authorizeHttpRequests{
                it.antMatchers("/public/**").permitAll()
                    .antMatchers("/generateToken").permitAll()


                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/orders").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/tea_boba").hasRole("USER")


                    .antMatchers("/tea_boba/createOrder").hasRole("USER")
                    .antMatchers("/tea_boba").hasRole("USER")
                    .antMatchers("/tea_boba/create/{teaId}").hasRole("USER")
                    .antMatchers("/tea_boba/create").hasRole("USER")
                    .antMatchers("/tea_boba/delete/{id}").hasRole("USER")


                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/tea/edit/{teaId}").hasRole("ADMIN")
                    .antMatchers("/tea/edit").hasRole("ADMIN")
                    .antMatchers("/tea/createTea").hasRole("ADMIN")
                    .antMatchers("/tea/delete/{teaId}").hasRole("ADMIN")

                    .antMatchers("/base/create").hasRole("ADMIN")
                    .antMatchers("/base/delete/{id_base}").hasRole("ADMIN")

                    .antMatchers("/boba/create").hasRole("ADMIN")
                    .antMatchers("/boba/delete/{id_boba}").hasRole("ADMIN")
                    .antMatchers("/boba/edit/{id_boba}").hasRole("ADMIN")



                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/tea")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/tea")
                    .permitAll()
            }
            .build()
        return build
    }


    companion object{
        @Bean
        fun passwordEncoder(): PasswordEncoder {
            return BCryptPasswordEncoder()
        }

        @Bean
        @Throws(java.lang.Exception::class)
        fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
            return config.getAuthenticationManager()
        }

    }

}

/*.csrf().disable()
                  .authorizeRequests()
                  .antMatchers("/**").hasAnyRole("USER")
                  .and().formLogin().loginPage("/login")
                  .defaultSuccessUrl("/dashboard",true)
                  .permitAll()
                  .and().logout();*/