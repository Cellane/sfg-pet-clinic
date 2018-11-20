package net.milanvit.sfgpetclinic.controller;

import net.milanvit.sfgpetclinic.model.Owner;
import net.milanvit.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEWS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String VIEW_FIND_OWNERS = "owners/findOwners";
    private static final String VIEW_OWNERS_LIST = "owners/ownersList";
    private static final String REDIRECT_OWNER = "redirect:/owners/";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/fi" +
        "nd")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return VIEW_FIND_OWNERS;
    }

    @GetMapping("")
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");

            return VIEW_FIND_OWNERS;
        } else if (results.size() == 1) {
            owner = results.iterator().next();

            return REDIRECT_OWNER + owner.getId();
        } else {
            model.addAttribute("results", results);

            return VIEW_OWNERS_LIST;
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");

        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());

        return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
        }

        Owner savedOwner = ownerService.save(owner);

        return REDIRECT_OWNER + savedOwner.getId();
    }

    @GetMapping("/{id}/edit")
    public String initUpdateOwnerForm(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));

        return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{id}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
        }

        owner.setId(id);
        Owner savedOwner = ownerService.save(owner);

        return REDIRECT_OWNER + savedOwner.getId();
    }
}
