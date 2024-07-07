package org.example.dajava.Service;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.example.dajava.Model.TaiKhoan;
import org.example.dajava.Repository.IChucVuRepository;
import org.example.dajava.Repository.ITaiKhoanRepository;
import org.example.dajava.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID; // Import statement for UUID
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TaiKhoanService implements UserDetailsService {
    @Autowired
    ITaiKhoanRepository taiKhoanReponsitory;
    @Autowired
    IChucVuRepository chucVuReponsitory;

    private static final Logger logger = LoggerFactory.getLogger(TaiKhoanService.class);


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save (@NotNull TaiKhoan taiKhoan) {
        taiKhoan.setMatKhau(new BCryptPasswordEncoder().encode(taiKhoan.getMatKhau()));
        taiKhoanReponsitory.save(taiKhoan);
    }
    //Gán chức vị mặc định là USER
    public void setDefaultChucVu(String email) {
        taiKhoanReponsitory.findByEmail(email).ifPresentOrElse(user -> {
            user.getRoles().add(chucVuReponsitory.findChucVuByIDChucVu(Role.USER.value));
            taiKhoanReponsitory.save(user);
        }, () -> {
            throw new UsernameNotFoundException("Người dùng không tồn tại");
        });
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var taikhoan = taiKhoanReponsitory.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Không tìm thấy email"));
        return org.springframework.security.core.userdetails.User
                .withUsername(taikhoan.getEmail())
                .password(taikhoan.getMatKhau())
                .authorities(taikhoan.getAuthorities())
                .accountExpired(!taikhoan.isAccountNonExpired())
                .accountLocked(!taikhoan.isAccountNonLocked())
                .credentialsExpired(!taikhoan.isCredentialsNonExpired())
                .disabled(!taikhoan.isEnabled())
                .build();

    }

    public Optional<TaiKhoan> findByEmail(String email) throws UsernameNotFoundException {
        return taiKhoanReponsitory.findByEmail(email);
    }

    public void updatePassword(String email, String newPassword) {
        taiKhoanReponsitory.findByEmail(email).ifPresentOrElse(taiKhoan -> {
            taiKhoan.setMatKhau(passwordEncoder.encode(newPassword));
            taiKhoanReponsitory.save(taiKhoan);
            logger.info("Password updated successfully for user: {}", email);
        }, () -> {
            throw new UsernameNotFoundException("Người dùng không tồn tại");
        });
    }

    public void sendPasswordReset(String email) {
        var taiKhoan = taiKhoanReponsitory.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy email"));

        String resetToken = generateResetToken();
        taiKhoan.setResetToken(resetToken); // Set the reset token in TaiKhoan entity
        taiKhoanReponsitory.save(taiKhoan);

        sendPasswordResetEmail(taiKhoan.getEmail(), resetToken);
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    private void sendPasswordResetEmail(String email, String resetToken) {
        logger.info("Password reset token for {}: {}", email, resetToken);
        // Implement your email sending logic here (e.g., using JavaMail or an email service)
    }

    public void resetPassword(String email, String resetToken, String newPassword) {
        var taiKhoan = taiKhoanReponsitory.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy email"));

        if (!resetToken.equals(taiKhoan.getResetToken())) {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }

        taiKhoan.setMatKhau(passwordEncoder.encode(newPassword));
        taiKhoan.setResetToken(null);
        taiKhoanReponsitory.save(taiKhoan);

        logger.info("Password reset successfully for user: {}", email);
    }





}
