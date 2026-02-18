package com.example.hotelbooking.service.security;

import com.example.hotelbooking.domain.User;
import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.mapper.UserMapper;
import com.example.hotelbooking.repository.domain.UserRepository;
import com.example.hotelbooking.web.security.SecurityUserPrincipal;
import com.example.hotelbooking.service.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain)
                .map(SecurityUserPrincipal::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Пользователь с таким username не найден.")
                );
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsername(username);
            return new SecurityUserPrincipal(user);
        } catch (EntityNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getLocalizedMessage());
        }

    }

}