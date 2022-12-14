package com.anasatanaslopessantantospedra.dscatalog.DTO;

import com.anasatanaslopessantantospedra.dscatalog.model.Role;
import com.anasatanaslopessantantospedra.dscatalog.model.User;
import org.springframework.context.support.BeanDefinitionDsl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

 public class RoleDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private Long id;
    private String authority;
    private Set<User> users=new HashSet<>();

     public RoleDTO(Long id, String authority) {
         this.id = id;
         this.authority = authority;
     }


    public RoleDTO(){

    }

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof RoleDTO)) return false;
         RoleDTO roleDTO = (RoleDTO) o;
         return Objects.equals(getId(), roleDTO.getId());
     }

     @Override
     public int hashCode() {
         return Objects.hash(getId());
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public String getAuthority() {
         return authority;
     }

     public void setAuthority(String authority) {
         this.authority = authority;
     }

     public Set<User> getUsers() {
         return users;
     }


 }
