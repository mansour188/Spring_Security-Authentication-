package com.example.demo.student.security;

public enum ApplicationPermession {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    public String getPermession() {
        return permession;
    }

    private final String permession;
    ApplicationPermession(String permession) {
        this.permession=permession;
    }
}
