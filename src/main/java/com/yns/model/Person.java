package com.yns.model;

public record Person(String name,
                     String surname,
                     Gender gender,
                     int age,
                     Education education,
                     String address) {
}
