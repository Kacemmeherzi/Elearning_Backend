package com.Elrearning.repository;

import com.Elrearning.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    public  Boolean existsByLabel (String label ) ;
    public Boolean existsById (int id ) ;

}
