package imt.projetrentree.projet.annotations;

import imt.projetrentree.projet.annotations.EnumValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

    private List<String> validValues;

    @Override
    public void initialize(EnumValid constraint) {
        validValues = Arrays.stream(constraint.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null case
        }

        if (value instanceof List) {
            List<?> valueList = (List<?>) value;
            for (Object val : valueList) {
                if (!(val instanceof Enum) || !validValues.contains(((Enum<?>) val).name())) {
                    return false;
                }
            }
            return true;
        } else if (value instanceof Enum) {
            return validValues.contains(((Enum<?>) value).name());
        } else {
            throw new IllegalArgumentException("Unsupported type. EnumValid can only validate Enum and List<Enum> types.");
        }
    }
}