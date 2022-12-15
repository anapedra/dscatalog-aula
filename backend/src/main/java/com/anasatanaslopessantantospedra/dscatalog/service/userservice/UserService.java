package com.anasatanaslopessantantospedra.dscatalog.service.userservice;

import com.anasatanaslopessantantospedra.dscatalog.DTO.RoleDTO;
import com.anasatanaslopessantantospedra.dscatalog.DTO.UserDTO;
import com.anasatanaslopessantantospedra.dscatalog.DTO.UserInsertDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Role;
import com.anasatanaslopessantantospedra.dscatalog.model.User;
import com.anasatanaslopessantantospedra.dscatalog.repositories.rolerepository.RoleRepository;
import com.anasatanaslopessantantospedra.dscatalog.repositories.userrepository.UserRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.DataBaseException;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable){
        Page<User> list=userRepository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }
    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> obj=userRepository.findById(id);
        User entity=obj.orElseThrow(
                ()-> new ResorceNotFoundException("Id "+id+" not found"));
        return new UserDTO(entity);
    }
    @Transactional

    public UserDTO insert(UserInsertDTO dto) {

            var user=new User();
            copyDtoToEntity(dto,user);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user=userRepository.save(user);
            return new UserDTO(user);

            
    }
    @Transactional
    public UserDTO upDate(Long id,UserDTO userDTO){
        try {
            var user= userRepository.getOne(id);
            copyDtoToEntity(userDTO,user);
            user=userRepository.save(user);
            return new UserDTO(user);
        }
        catch (EntityNotFoundException e){
            throw new ResorceNotFoundException("Id " + id + " not found :(");
        }

    }
    @Transactional
    public void deleteById(Long id){

        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResorceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }

    private void copyDtoToEntity(UserDTO userDTO,User user){
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());

        user.getRoles().clear();
        for (RoleDTO roleDTO : userDTO.getRoles()){
            Role role=roleRepository.getOne(roleDTO.getId());
            user.getRoles().add(role);
        }
    }


}
