package net.milanvit.sfgpetclinic.bootstrap;

import net.milanvit.sfgpetclinic.model.Owner;
import net.milanvit.sfgpetclinic.model.Vet;
import net.milanvit.sfgpetclinic.service.OwnerService;
import net.milanvit.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner1);
        ownerService.save(owner2);

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