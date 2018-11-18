package net.milanvit.sfgpetclinic.bootstrap;

import net.milanvit.sfgpetclinic.model.Owner;
import net.milanvit.sfgpetclinic.model.Pet;
import net.milanvit.sfgpetclinic.model.PetType;
import net.milanvit.sfgpetclinic.model.Vet;
import net.milanvit.sfgpetclinic.service.OwnerService;
import net.milanvit.sfgpetclinic.service.PetTypeService;
import net.milanvit.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
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

        // VET
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet1);
        vetService.save(vet2);
    }
}
