package net.milanvit.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.milanvit.sfgpetclinic.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;
}
