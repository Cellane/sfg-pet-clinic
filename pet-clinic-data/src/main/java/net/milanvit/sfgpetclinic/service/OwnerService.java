package net.milanvit.sfgpetclinic.service;

import net.milanvit.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    List<Owner> findAllByLastNameLike(String lastName);
}
