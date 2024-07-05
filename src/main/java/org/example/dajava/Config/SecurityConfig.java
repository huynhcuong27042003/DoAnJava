package org.example.dajava.Config;


import jakarta.validation.constraints.NotNull;
import org.example.dajava.Service.TaiKhoanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Đánh dấu lớp này là một lớp cấu hình cho Spring Context.
@EnableWebSecurity // Kích hoạt tính năng bảo mật web của Spring Security.
public class SecurityConfig{
    private TaiKhoanService taikhoanService;
    @Bean // Đánh dấu phương thức trả về một bean được quản lý bởi Spring Context.
    public UserDetailsService userDetailsService() {
        return new TaiKhoanService(); // Cung cấp dịch vụ xử lý chi tiết người dùng.
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider(); // Tạo nhà cung cấp xác thực.
        auth.setUserDetailsService(userDetailsService()); // Thiết lập dịch vụ chi tiết người dùng.
        auth.setPasswordEncoder(passwordEncoder()); // Thiết lập cơ chế mã hóa mật khẩu.
        return auth; // Trả về nhà cung cấp xác thực.
    }
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/","/images/**","/register","/login", "/error")
                        .permitAll()
                        // Cho phép truy cập không cần xác thực.
                        .requestMatchers("/products/edit/**")
                        .hasAnyAuthority("ADMIN") // Chỉ cho phép ADMIN truy cập.
                        .requestMatchers("/api/**")
                        .permitAll() // API mở cho mọi người dùng.
                        .anyRequest().authenticated() // Bất kỳ yêu cầu nào khác cần xác thực.
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // Trang chuyển hướng sau khi đăng xuất.
                        .deleteCookies("JSESSIONID") // Xóa cookie.
                        .invalidateHttpSession(true) // Hủy phiên làm việc.
                        .clearAuthentication(true) // Xóa xác thực.
                        .permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/") // Trang chính chứa modal đăng nhập
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/?error=true")
                        .usernameParameter("email") // Tên trường tùy chỉnh
                        .passwordParameter("MatKhau")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("hutech")
                        .rememberMeCookieName("hutech")
                        .tokenValiditySeconds(24 * 60 * 60) // Thời gian nhớ đăng nhập.
                        .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403") // Trang báo lỗi khi truy cập không được phép.
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1) // Giới hạn số phiên đăng nhập.
                        .expiredUrl("/login") // Trang khi phiên hết hạn.
                )
                .httpBasic(httpBasic -> httpBasic
                        .realmName("hutech") // Tên miền cho xác thực cơ bản.
                )
                .build(); // Xây dựng và trả về chuỗi lọc bảo mật.
    }

}