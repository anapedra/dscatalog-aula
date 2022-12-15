package com.anasatanaslopessantantospedra.dscatalog.controller.usercontroller;

import com.anasatanaslopessantantospedra.dscatalog.DTO.UserDTO;
import com.anasatanaslopessantantospedra.dscatalog.DTO.UserInsertDTO;
import com.anasatanaslopessantantospedra.dscatalog.service.userservice.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
        Page<UserDTO>list=userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO userDTO=userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
    @PostMapping
    public ResponseEntity<UserDTO > insert(@RequestBody UserInsertDTO dto){
        UserDTO  userDTO=userService.insert(dto);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO>upDateCateriry(@PathVariable Long id,@RequestBody  UserDTO userDTO){
        userDTO=userService.upDate(id, userDTO);
        return ResponseEntity.ok().body(userDTO);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> daletCategory(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
