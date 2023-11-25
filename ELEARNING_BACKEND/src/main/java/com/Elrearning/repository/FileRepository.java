package com.Elrearning.repository;

import com.Elrearning.models.Category;
import com.Elrearning.models.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface FileRepository extends JpaRepository<FileEntity,Integer> {


    public FileEntity  findByName (String name) ;
}
