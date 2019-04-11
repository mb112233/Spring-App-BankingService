package utcn.labs.sd.bankingservice.core.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Banking Service security configurations password is encoded with {@link BCryptPasswordEncoder}
 */
@Configuration
@EnableWebSecurity
public class EndpointsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;

    @Value("${credentials.employee.username}")
    private String employeeUsername;

    @Value("${credentials.employee.password}")
    private String employeePassword;

    @Value("${credentials.employee.role}")
    private String employeeRole;

    @Value("${credentials.admin.username}")
    private String adminUsername;

    @Value("${credentials.admin.password}")
    private String adminPassword;

    @Value("${credentials.admin.role}")
    private String adminRole;

    @SuppressWarnings("deprecation")
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       /* PasswordEncoder encoder = new BCryptPasswordEncoder();
        InMemoryUserDetailsManagerConfigurer authConfigurer = auth.inMemoryAuthentication()
                .passwordEncoder(encoder);
        authConfigurer.withUser(employeeUsername)
                .password(encoder.encode(this.employeePassword))
                .roles(employeeRole);
        authConfigurer.withUser(adminUsername)
                .password(encoder.encode(this.adminPassword))
                .roles(adminRole);
   */
    	
    	auth.jdbcAuthentication().passwordEncoder(new Pbkdf2PasswordEncoder()).dataSource(dataSource)
    	.usersByUsernameQuery("select username, password, true from user_table where username = ?")
        .authoritiesByUsernameQuery("select username, typee from user_table where username = ?")
        .rolePrefix("ROLE_");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/bank/employee/**").hasRole("EMPLOYEE")
                .antMatchers("/bank/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }
}
