package com.anasatanaslopessantantospedra.dscatalog.repositories.userrepository;

import com.anasatanaslopessantantospedra.dscatalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
