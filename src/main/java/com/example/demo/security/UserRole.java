package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Sets.newHashSet(ApplicationPermession.STUDENT_READ, ApplicationPermession.COURSE_READ, ApplicationPermession.COURSE_WRITE, ApplicationPermession.STUDENT_WRITE)),
    ADMINTRAINE(Sets.newHashSet(ApplicationPermession.STUDENT_READ, ApplicationPermession.COURSE_READ)),
    STUDENT(Sets.newHashSet()) ;



    private final Set<ApplicationPermession> permessions;
    UserRole(Set<ApplicationPermession> permessions) {
        this.permessions = permessions;
    }
    public Set<ApplicationPermession> getPermissions(){
        return permessions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        Set<SimpleGrantedAuthority> permessions= getPermissions().stream()
                .map(permession->new SimpleGrantedAuthority(permession.getPermission()))
                .collect(Collectors.toSet());

        permessions.add(new SimpleGrantedAuthority(this.name()));
        return permessions;
    }
}
