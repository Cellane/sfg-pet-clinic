package net.milanvit.sfgpetclinic.controller;

import net.milanvit.sfgpetclinic.model.Pet;
import net.milanvit.sfgpetclinic.model.Visit;
import net.milanvit.sfgpetclinic.service.PetService;
import net.milanvit.sfgpetclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController controller;

    private MockMvc mockMvc;
    private Pet pet;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        pet = Pet.builder().id(1L).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("pet"))
            .andExpect(model().attributeExists("visit"))
            .andExpect(view().name(VisitController.VIEW_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(visitService.save(any())).thenReturn(Visit.builder().build());

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/owners/1"));
        verify(visitService).save(any());
    }
}
