package com.Elrearning.controllers;

import com.Elrearning.LocalStorageConfig.FileStorageService;
import com.Elrearning.models.Category;
import com.Elrearning.models.CategotyDTO;
import com.Elrearning.services.CategorieService;
import com.Elrearning.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private  final CategorieService categorieService ;


    public CategoryController(CategorieService categorieService ) {
        this.categorieService = categorieService;


    }

    @GetMapping("/getallcategories")
    public List<Category> getllcategories () {
        return  categorieService.getall();
    }
    @PostMapping(value = "/addcategory")
    public ResponseEntity<?> addcategory (@ModelAttribute  CategotyDTO categotyDTO) throws IOException {
        Category category = new Category( categotyDTO.getLabel(), categotyDTO.getDescription() );
        if (!categorieService.checkifexist(category)){


            categorieService.addcategory(category) ;
            return new ResponseEntity<>("ADDED", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("ALREADY EXIST YEE BRO ALEH HAKKA ",HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<?> deletecategory(@PathVariable int id ) {
        if (categorieService.checkbyid(id)){
            categorieService.deletecategory(id);
            return  new ResponseEntity<>("DELETED", HttpStatus.OK);
        }else return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);

    }
    @PutMapping("/updatecategory/{id}")
    public ResponseEntity<?> updatecategory (@PathVariable int id ,@RequestBody CategotyDTO categotyDTO) {
        //TODO
        return  null  ;
    }

}
