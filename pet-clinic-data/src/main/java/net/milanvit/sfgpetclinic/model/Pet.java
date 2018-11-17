package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import net.milanvit.sfgpetclinic.BaseEntity;

import java.time.LocalDate;

@Data
public class Pet extends BaseEntity {
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
}
