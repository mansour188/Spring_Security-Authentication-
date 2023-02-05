package com.example.demo.student.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.student.security.ApplicationPermession.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(STUDENT_READ,COURSE_READ,COURSE_WRITE,STUDENT_WRITE)),
    ADMINTRAINE(Sets.newHashSet(STUDENT_READ,COURSE_READ)),
    STUDENT(Sets.newHashSet()) ;



    private final Set<ApplicationPermession> permessions;
    UserRole(Set<ApplicationPermession> permessions) {
        this.permessions = permessions;
    }


}
