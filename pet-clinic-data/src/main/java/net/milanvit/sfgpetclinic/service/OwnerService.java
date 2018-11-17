package net.milanvit.sfgpetclinic.service;

import net.milanvit.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
