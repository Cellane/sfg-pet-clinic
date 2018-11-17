package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import net.milanvit.sfgpetclinic.BaseEntity;

@Data
public class PetType extends BaseEntity {
    private String name;
}
