package com.bobocode;

import java.time.LocalDate;

public class Account {
    private String email;
    private LocalDate birthday;

    public Account(String email, LocalDate birthday) {
        this.email = email;
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
