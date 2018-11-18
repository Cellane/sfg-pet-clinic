package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.milanvit.sfgpetclinic.BaseEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Visit extends BaseEntity {
    private LocalDate date;
    private String description;
    private Pet pet;
}
