package com.bobocode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Accounts {
    public static List<Account> generateAccountList(int n) {
        return list;
    }

    private static final List<Account> list = new ArrayList<>() {
        {
            add(new Account("abigailferguson@mail.com", LocalDate.of(1989, 05 , 19)));
            add(new Account("faith.myers@yahoo.com", LocalDate.of(1950 , 01 , 22)));
            add(new Account("sofia.riley@mail.com", LocalDate.of(2007 , 01 , 02)));
            add(new Account("nguyen@gmail.com", LocalDate.of(1961 , 03 , 18)));
            add(new Account("clemons@mail.com", LocalDate.of(1967 , 03 , 24)));
            add(new Account("ferrell@mail.com", LocalDate.of(1989 , 03 , 12)));
            add(new Account("pope@mail.com", LocalDate.of(1930 , 12 , 28)));
            add(new Account("lamb@gmail.com", LocalDate.of(2014 , 12 , 16)));
            add(new Account("samantha.baker@gmail.com", LocalDate.of(2001 , 04 , 03)));
            add(new Account("stokes@yahoo.com", LocalDate.of(1965 , 01 , 01)));
        }
    };
}
