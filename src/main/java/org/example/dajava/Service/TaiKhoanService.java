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

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TaiKhoanService implements UserDetailsService {
    @Autowired
    ITaiKhoanRepository taiKhoanReponsitory;
    @Autowired
    IChucVuRepository chucVuReponsitory;
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

    public TaiKhoan findTKByEmail(String email) {
        return taiKhoanReponsitory.findTKByEmail(email);
    }

    public Optional<TaiKhoan> findByEmail(String email) throws UsernameNotFoundException {
        return taiKhoanReponsitory.findByEmail(email);
    }
}
