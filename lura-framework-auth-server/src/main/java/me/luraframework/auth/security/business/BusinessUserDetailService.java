package me.luraframework.auth.security.business;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessUserDetailService implements UserDetailsService {

    private final BusinessRepository businessRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return businessRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
    }
}
