package net.milanvit.sfgpetclinic.bootstrap;

import net.milanvit.sfgpetclinic.model.*;
import net.milanvit.sfgpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        // PET TYPE
        PetType dog = new PetType();
        PetType cat = new PetType();

        dog.setName("Dog");
        cat.setName("Cat");

        PetType savedDog = petTypeService.save(dog);
        PetType savedCat = petTypeService.save(cat);

        // OWNER
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Bickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Bickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231235");

        // PET
        Pet mikesPet = new Pet();
        Pet fionasCat = new Pet();

        mikesPet.setName("Rosco");
        mikesPet.setPetType(savedDog);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(mikesPet);

        fionasCat.setName("Just a cat");
        fionasCat.setPetType(savedCat);
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasCat);

        ownerService.save(owner1);
        ownerService.save(owner2);

        // VISIT
        Visit visit1 = new Visit();

        visit1.setPet(fionasCat);
        visit1.setDate(LocalDate.now());
        visit1.setDescription("Sneezy Kitty");

        visitService.save(visit1);

        // SPECIALTY
        Specialty radiology = new Specialty();
        Specialty surgery = new Specialty();
        Specialty dentistry = new Specialty();

        radiology.setDescription("radiology");
        surgery.setDescription("surgery");
        dentistry.setDescription("dentistry");

        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty savedDentistry = specialtyService.save(dentistry);

        // VET
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet1);
        vetService.save(vet2);
    }
}
