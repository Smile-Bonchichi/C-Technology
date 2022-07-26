package kg.smile.ctechnology.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.login, t.password, t.is_active FROM users t WHERE t.login = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.login, r.role_name " +
                                "FROM users_roles ur " +
                                "INNER JOIN users u " +
                                "   on ur.user_id = u.id " +
                                "INNER JOIN roles r " +
                                "   on ur.roles_id = r.id " +
                                "WHERE u.login = ? AND u.is_active = 1"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/login").permitAll()

                .antMatchers(HttpMethod.GET, "/api/logout").authenticated()

                .antMatchers(HttpMethod.GET, "/api/payment").authenticated()

                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
