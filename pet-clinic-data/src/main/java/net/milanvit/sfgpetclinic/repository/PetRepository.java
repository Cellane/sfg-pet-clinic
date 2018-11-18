package net.milanvit.sfgpetclinic.repository;

import net.milanvit.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
