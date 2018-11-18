package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.milanvit.sfgpetclinic.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetType extends BaseEntity {
    private String name;
}
