package com.laptopstore.ecommerce.util.anotaion.validation.user;

import com.laptopstore.ecommerce.dto.user.UpdateAccountDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class UpdateAccountValidator implements ConstraintValidator<UpdateAccountConstraint, UpdateAccountDto> {
    @Override
    public boolean isValid(UpdateAccountDto value, ConstraintValidatorContext context) {
        boolean isValid = true;

        List<String> allowFileTypes = new ArrayList<>(List.of("image/jpg", "image/jpeg", "image/png", "image/gif", "image/webp"));

        if(value.getFirstName() == null || value.getFirstName().length() < 2) {
            context.buildConstraintViolationWithTemplate("First Name must be at least 2 characters")
                    .addPropertyNode("firstName")
                    .addConstraintViolation();

            isValid = false;
        }

        if(value.getLastName() == null || value.getLastName().length() < 2) {
            context.buildConstraintViolationWithTemplate("Last Name must be at least 2 characters")
                    .addPropertyNode("lastName")
                    .addConstraintViolation();
        }

        if(value.getPhone() == null || !value.getPhone().matches("0\\d{9,14}")) {
            context.buildConstraintViolationWithTemplate("Invalid phone number")
                    .addPropertyNode("phone")
                    .addConstraintViolation();
            isValid = false;
        }

        if(value.getAvatar() != null && !value.getAvatar().isEmpty() && !allowFileTypes.contains(value.getAvatar().getContentType())){
            context.buildConstraintViolationWithTemplate("Invalid image type")
                    .addPropertyNode("avatar")
                    .addConstraintViolation();
            isValid = false;
        }

        // validate avatar and address below

        return isValid;
    }
}
