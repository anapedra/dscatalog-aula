package com.anasatanaslopessantantospedra.dscatalog.service.validetion;


import com.anasatanaslopessantantospedra.dscatalog.DTO.UserInsertDTO;
import com.anasatanaslopessantantospedra.dscatalog.controller.exceptions.FieldMessage;
import com.anasatanaslopessantantospedra.dscatalog.model.User;
import com.anasatanaslopessantantospedra.dscatalog.repositories.userrepository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    private final UserRepository userRepository;
    public UserInsertValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        User user=userRepository.findByEmail(dto.getEmail());
        if (user != null){
          list.add(new FieldMessage("email","Email invalido!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}

