package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.UserRole.*;

@Repository("fake")
public class ApplicationUserDaoService implements  ApplictionUserDao{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Optional<ApplicationUser> selectApplicationUserByusername(String username) {
        return getApplicationUsers().stream()
                .filter((user)->user.getUsername().equals(username))
                .findFirst();
    }

    public List<ApplicationUser>  getApplicationUsers(){
       List<ApplicationUser> applicationUsers = Lists.newArrayList(
               new ApplicationUser("user1",
                                             passwordEncoder.encode("password1"),
                                           STUDENT.getAuthority(),
                                           true,
                                           true,
                                           true,
                                           true),
               new ApplicationUser("user2",
                       passwordEncoder.encode("password1"),
                       ADMIN.getAuthority(),
                       true,
                       true,
                       true,
                       true),
               new ApplicationUser("user3",
                       passwordEncoder.encode("password1"),
                       ADMINTRAINE.getAuthority(),
                       true,
                       true,
                       true,
                       true)


       );
               return applicationUsers;
    }
}
