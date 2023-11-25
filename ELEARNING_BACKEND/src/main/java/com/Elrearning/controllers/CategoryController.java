package com.Elrearning.controllers;

import com.Elrearning.LocalStorageConfig.FileStorageService;
import com.Elrearning.models.Category;
import com.Elrearning.models.CategotyDTO;
import com.Elrearning.models.FileEntity;
import com.Elrearning.services.CategorieService;
import com.Elrearning.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    @Autowired
    private final FileService fileService ;
    @Autowired
    private final FileStorageService storageService ;

    public CategoryController(CategorieService categorieService,  FileService fileService, FileStorageService storageService) {
        this.categorieService = categorieService;

        this.fileService = fileService;
        this.storageService = storageService;
    }

    @GetMapping("/getallcategories")
    public List<Category> getllcategories () {
        return  categorieService.getall();
    }
    @PostMapping(value = "/addcategory",consumes = "multipart/form-data")
    public ResponseEntity<?> addcategory (@ModelAttribute  CategotyDTO categotyDTO) throws IOException {
        Category category = new Category( categotyDTO.getLabel(), categotyDTO.getDescription() );
        if (!categorieService.checkifexist(category)){
             FileEntity image = storageService.storeImage(categotyDTO.getFile());

            category.setFileEntity(image);

            categorieService.addcategory(category) ;
            return new ResponseEntity<>("ADDED", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("ALREADY EXIST",HttpStatus.NOT_ACCEPTABLE);
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
        return null ;
    }
    @GetMapping("/getimage/{name}")
    public ResponseEntity<?> getcategoryimage (@PathVariable String name) throws IOException {


try {
   byte[] image = storageService.loadImageAsBytes(name) ;
   if (image != null) {
       return  new ResponseEntity<>(image,HttpStatus.OK);

   }
} catch ( Exception e )  {
    return ResponseEntity.notFound().build();
}


        return null;
    }
}
