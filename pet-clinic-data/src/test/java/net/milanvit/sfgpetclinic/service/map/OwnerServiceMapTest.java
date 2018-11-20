package net.milanvit.sfgpetclinic.service.map;

import net.milanvit.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    private OwnerServiceMap service;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        service = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        service.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        assertEquals(1, service.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = service.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = service.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner2 = new Owner();
        Owner savedOwner = service.save(owner2);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        service.delete(service.findById(ownerId));

        assertEquals(0, service.findAll().size());
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);

        assertEquals(0, service.findAll().size());
    }

    @Test
    void findByLastName() {
        List<Owner> owners = service.findAllByLastNameLike(lastName);
        boolean containsSmith = owners.stream()
            .map(Owner::getLastName)
            .anyMatch(surname -> surname.equalsIgnoreCase(lastName));

        assertFalse(owners.isEmpty());
        assertTrue(containsSmith);
    }

    @Test
    void findByLastNameNotFound() {
        List<Owner> owners = service.findAllByLastNameLike("foo");

        assertTrue(owners.isEmpty());
    }
}
