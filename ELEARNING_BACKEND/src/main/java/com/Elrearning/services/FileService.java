package com.Elrearning.services;

import com.Elrearning.models.FileEntity;
import com.Elrearning.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {


    @Autowired
    private final FileRepository fileRepository ;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void savefile (FileEntity file ){
        fileRepository.save(file ) ;

    }
    public boolean checkifexist (String name ) {

     return    fileRepository.findByName(name)!=null;
    }

}
