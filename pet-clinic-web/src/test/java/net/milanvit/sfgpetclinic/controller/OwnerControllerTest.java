package net.milanvit.sfgpetclinic.controller;

import net.milanvit.sfgpetclinic.model.Owner;
import net.milanvit.sfgpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @InjectMocks
    OwnerController controller;

    MockMvc mockMvc;
    List<Owner> owners;

    @Mock
    private OwnerService ownerService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        owners = new ArrayList<>();

        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/findOwners"))
            .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void findSingleOwner() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners.subList(0, 1));

        mockMvc.perform(get("/owners"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/owners/1"));
    }

    @Test
    void findMultipleOwners() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownersList"))
            .andExpect(model().attribute("results", hasSize(2)));
    }

    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownerDetails"))
            .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }
}
