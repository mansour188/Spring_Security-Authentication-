package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService  implements UserDetailsService {
    private final ApplictionUserDao applictionUserDao;
    @Autowired

    public ApplicationUserDetailsService(@Qualifier("fake") ApplictionUserDao applictionUserDao) {
        this.applictionUserDao = applictionUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applictionUserDao.
                selectApplicationUserByusername(username)
                .orElseThrow(
                        () ->new UsernameNotFoundException(String.format("user %s not found ", username)));
    }
}
