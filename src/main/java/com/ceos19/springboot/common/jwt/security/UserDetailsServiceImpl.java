package com.ceos19.springboot.common.jwt.security;

import com.ceos19.springboot.common.ErrorType;
import com.ceos19.springboot.user.entity.User;
import com.ceos19.springboot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 권한을 가져오는 UserDetailsImpl을 실제로 실행하고 서비스를 작동하는 것
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(ErrorType.NOT_FOUND_USER.getMessage()));
        // username에 해당하는 것이 존재한다면 이를 바탕으로 user를 UserDetailsImpl을 생성해준다
        return new UserDetailsImpl(user, user.getUsername());
    }
}
