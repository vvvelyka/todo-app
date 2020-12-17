//package com.kpi.project.todoapp.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    DataSource dataSource;
//
//    @Autowired
//    public WebSecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select email, password, enabled"
//                        + " from users where email=?")
//                .authoritiesByUsernameQuery("select u.email, r.role_name "
//                        + "from roles r " +
//                        "inner join user_role ur on r.role_id == ur.role_id" +
//                        "inner join users u on ur.user_id == u.user_id" +
//                        "where u.email=?");
//        //.passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//
//        http.authorizeRequests()
//                .antMatchers("/", "/signup", "/login", "/error", "/success").permitAll() // The pages does not require login
//                //.antMatchers("/todolist", "/todoform", "/edittodo").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
//                //.antMatchers("/admin").hasRole("ROLE_ADMIN")
//                //.and().exceptionHandling().accessDeniedPage("/error")
//                //.anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password")
//                //.loginProcessingUrl("/j_spring_security_check").permitAll()
//                //.loginProcessingUrl("/j_spring_security_check")
//                .defaultSuccessUrl("/success")// default-target-url
//                .failureUrl("/error");// authentication-failure-url,
//                //.and().logout().logoutSuccessUrl("/logout");
//                //.usernameParameter("email");// overrides Spring's default
//        //.passwordParameter("password");// overrides Spring's default
//
//
////        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
////                .and()
////                .httpBasic(); // Authenticate users with HTTP basic authentication
//    }
//
//    // @Autowired
//    // UserDetailsServiceImp userDetailsService;
//
//    // @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        return bCryptPasswordEncoder;
////    }
//
//    //  @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////        // Setting Service to find User in the database.
////        // And Setting PasswordEncoder
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////
////    }
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.csrf().disable();
////
////        // The pages does not require login
////        http.authorizeRequests().antMatchers("/", "/login", "/logout", "/signup").permitAll();
////
////        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
////        // If no login, it will redirect to /login page.
////        http.authorizeRequests().antMatchers("/todolist", "/addtodo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
////
////        // For ADMIN only.
////        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
////
////        // When the user has logged in as XX.
////        // But access a page that requires role YY,
////        // AccessDeniedException will be thrown.
////        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
////
////        // Config for Login Form
////        http.authorizeRequests().and().formLogin()//
////                // Submit URL of login page.
////                .loginProcessingUrl("/j_spring_security_check") // Submit URL
////                .loginPage("/login")//
////                .defaultSuccessUrl("/todolist")//
////                .failureUrl("/login?error=true")//
////                .usernameParameter("username")//
////                .passwordParameter("password")
////                // Config for Logout Page
////                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
////
////    }
//
//}
//
