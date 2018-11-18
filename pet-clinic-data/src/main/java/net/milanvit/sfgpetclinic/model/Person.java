package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.milanvit.sfgpetclinic.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
