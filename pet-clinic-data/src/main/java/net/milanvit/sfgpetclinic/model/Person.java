package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import net.milanvit.sfgpetclinic.BaseEntity;

@Data
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
