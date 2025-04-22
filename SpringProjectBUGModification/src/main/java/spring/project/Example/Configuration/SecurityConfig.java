package spring.project.Example.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

//import lombok.SneakyThrows;
import spring.project.Example.Service.RoleService;
import spring.project.Example.Service.UserService;
import spring.project.Example.filter.AppFilter;
//import spring.project.Example.filter.AppFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserService userservice;

@Autowired
		private AppFilter filter;
		
		@Autowired
		private UserService userDtlsSvc;
		
		/*@Bean
		public PasswordEncoder pwdEncoder() {
			return new BCryptPasswordEncoder();
		}*/
		
		@Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
	        //authenticationProvider.setPasswordEncoder(pwdEncoder());
	        authenticationProvider.setUserDetailsService(userDtlsSvc);
	        return authenticationProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	                .csrf(csrf -> csrf.disable()) 
	                .authorizeHttpRequests(authz -> authz
	                    .requestMatchers("/api/login","/v3/api-docs/**",
	                            "/swagger-ui/**",
	                            "/swagger-ui.html").permitAll() 
	                    .requestMatchers("/admin/**").authenticated()) 
	                .sessionManagement(session -> session
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
	                .authenticationProvider(authenticationProvider()) 
	                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) 
	                .build();
	    }

	}

