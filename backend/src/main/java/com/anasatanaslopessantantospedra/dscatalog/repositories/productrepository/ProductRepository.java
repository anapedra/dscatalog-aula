package com.anasatanaslopessantantospedra.dscatalog.repositories.productrepository;

import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
