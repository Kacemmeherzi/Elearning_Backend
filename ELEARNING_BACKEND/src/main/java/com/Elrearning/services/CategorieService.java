package com.Elrearning.services;

import com.Elrearning.models.Category;
import com.Elrearning.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private  final CategoryRepository categoryRepository ;

    public CategorieService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
public boolean checkifexist (Category category){

return categoryRepository.existsByLabel(category.getLabel());

}

    public Category addcategory (Category category) {
        categoryRepository.save(category) ;
        return category;
//TODO : ADD IMAGE

}
public void deletecategory (int id ) {
    categoryRepository.deleteById(id );
}

public  boolean checkbyid (int id ) {return categoryRepository.existsById(id);}
    public void updatecategory (Category category , int id ){
        Category currentcategory = categoryRepository.findById(id).get();


    }
    public Category getbyid (int id ){

     return  categoryRepository.findById(id).get() ;
    }

    public List<Category> getall() {
         return  categoryRepository.findAll();
    }
}
