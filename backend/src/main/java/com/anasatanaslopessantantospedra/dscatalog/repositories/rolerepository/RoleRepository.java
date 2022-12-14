package com.anasatanaslopessantantospedra.dscatalog.repositories.rolerepository;

import com.anasatanaslopessantantospedra.dscatalog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
