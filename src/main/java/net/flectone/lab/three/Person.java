package net.flectone.lab.three;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Person {

    private final String name;
    private final String email;
    private final Date birthDate;

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", BirthDate: " + birthDate + ", Age: " + getAge();
    }

    public int getBirthYear() {
        return birthDate.getYear() + 1900;
    }

    // not full correctly, but ok
    public int getAge() {
        return new Date().getYear() - birthDate.getYear();
    }
}
