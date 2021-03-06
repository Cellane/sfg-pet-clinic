package net.milanvit.sfgpetclinic.service.jpa;

import net.milanvit.sfgpetclinic.model.Pet;
import net.milanvit.sfgpetclinic.repository.PetRepository;
import net.milanvit.sfgpetclinic.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class PetServiceJPA implements PetService {
    private final PetRepository petRepository;

    public PetServiceJPA(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();

        petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
