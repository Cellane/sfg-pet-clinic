package net.milanvit.sfgpetclinic.service.jpa;

import net.milanvit.sfgpetclinic.model.Specialty;
import net.milanvit.sfgpetclinic.repository.SpecialtyRepository;
import net.milanvit.sfgpetclinic.service.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("jpa")
public class SpecialtyServiceJPA implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceJPA(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();

        specialtyRepository.findAll().forEach(specialties::add);

        return specialties;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
