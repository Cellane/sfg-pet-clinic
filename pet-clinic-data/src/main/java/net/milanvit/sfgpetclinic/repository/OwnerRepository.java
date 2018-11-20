package net.milanvit.sfgpetclinic.repository;

import net.milanvit.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findAllByLastNameLike(String lastName);

    List<Owner> findAllByLastNameContaining(String lastName);
}
