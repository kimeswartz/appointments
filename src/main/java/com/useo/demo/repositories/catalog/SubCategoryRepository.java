package com.useo.demo.repositories.catalog;

import com.useo.demo.entities.catalog.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long>{
    Optional<SubCategory> findByName(String name);
}
