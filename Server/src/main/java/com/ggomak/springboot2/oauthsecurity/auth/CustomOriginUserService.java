package com.ggomak.springboot2.oauthsecurity.auth;

import com.ggomak.springboot2.oauthsecurity.auth.dto.OriginAttributes;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOriginUserService implements UserDetailsService {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailAndSocialType(email, SocialType.ORIGIN);

        if (!user.isPresent()) {    // 유저정보가 없을 시(회원 가입이 안되어 있을 시) Exception 발생
            throw new UsernameNotFoundException("FAIL");
        }

        OriginAttributes originUser = new OriginAttributes();
        originUser.setUsername(user.get().getEmail());
        originUser.setPassword(user.get().getPassword());
        originUser.setAuthorities(getAuthorities(email));
        originUser.setEnabled(true);
        originUser.setAccountNonExpired(true);
        originUser.setAccountNonLocked(true);
        originUser.setCredentialsNonExpired(true);

        httpSession.setAttribute("user", new SessionUser(user.get())); // 세션 등록

        return originUser;
    }

    // Custom 로그인(자체 로그인)시 권한(ROLE) 부여
    public Collection<GrantedAuthority> getAuthorities(String email) {
        //List<Authority> authList = authoritiesRepository.findByUsername(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        //for (Authority authority : authList) {
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //}
        return authorities;
    }
}
