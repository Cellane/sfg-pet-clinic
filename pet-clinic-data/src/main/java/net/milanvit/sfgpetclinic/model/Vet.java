package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Vet extends Person {
    private Set<Specialty> specialties = new HashSet<>();
}
