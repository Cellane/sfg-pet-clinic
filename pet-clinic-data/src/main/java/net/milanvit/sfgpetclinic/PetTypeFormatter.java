package net.milanvit.sfgpetclinic;

import net.milanvit.sfgpetclinic.model.PetType;
import net.milanvit.sfgpetclinic.service.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        return petTypeService.findAll()
            .stream()
            .filter(petType -> petType.getName().equals(text))
            .findFirst()
            .orElseThrow(() -> new ParseException("Type not found: " + text, 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
