package net.milanvit.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Person {
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();
}
