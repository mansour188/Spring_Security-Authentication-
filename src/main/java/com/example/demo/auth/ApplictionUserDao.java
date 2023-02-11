package com.example.demo.auth;

import java.util.Optional;

public interface ApplictionUserDao {
    Optional<ApplicationUser> selectApplicationUserByusername(String username);

}
