package net.milanvit.sfgpetclinic.service.jpa;

import net.milanvit.sfgpetclinic.model.Owner;
import net.milanvit.sfgpetclinic.repository.OwnerRepository;
import net.milanvit.sfgpetclinic.repository.PetRepository;
import net.milanvit.sfgpetclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {
    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerServiceJPA service;
    private final String lastName = "Smith";
    private Owner returnOwner;
    private List<Owner> returnOwners;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(lastName).build();
        returnOwners = new ArrayList<>();
        returnOwners.add(returnOwner);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findAllByLastNameContaining(any())).thenReturn(returnOwners);

        List<Owner> owners = service.findAllByLastNameLike(lastName);
        boolean containsSmith = owners.stream()
            .map(Owner::getLastName)
            .anyMatch(surname -> surname.equalsIgnoreCase(lastName));

        assertFalse(owners.isEmpty());
        assertTrue(containsSmith);
        verify(ownerRepository).findAllByLastNameContaining(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = new HashSet<>();

        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        List<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnOwner.getId());

        verify(ownerRepository).deleteById(anyLong());
    }
}
