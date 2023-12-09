package com.Elrearning.LocalStorageConfig;

import com.Elrearning.models.FileEntity;
import com.Elrearning.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {


    @Autowired
    private  final  StorageConfig fileStorageConfig;
@Autowired
private final FileService fileService ;

    public FileStorageService(StorageConfig fileStorageConfig, FileService fileEntity) {
        this.fileStorageConfig = fileStorageConfig;
        this.fileService = fileEntity;
    }


    public FileEntity storeImage(MultipartFile imageFile) throws IOException {
        // Validate that the file is an image
        if (!isImage(imageFile)) {
            throw new IllegalArgumentException("File is not an image");
        }

        // Normalize image file name


        String uniqueId = UUID.randomUUID().toString();


        String originalFileName = imageFile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String filename = uniqueId + fileExtension;

        // Resolve the image file path within the category directory
        Path imagePath = Paths.get(fileStorageConfig.getUploadDir()).resolve(filename).normalize();



        Files.copy(imageFile.getInputStream(), imagePath);
        FileEntity image = new FileEntity("image",filename);
        fileService.savefile(image);
        return image;
    }
    public String saveVid (MultipartFile vediofile) throws IOException {
        // Normalize
        String uniqueId = UUID.randomUUID().toString();

        String originalFileName = vediofile.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String filename = uniqueId + fileExtension;

        // Resolve the image file path within the category directory
        Path imagePath = Paths.get(fileStorageConfig.getUploadDir()).resolve(filename).normalize();



        Files.copy(vediofile.getInputStream(), imagePath);
        FileEntity vedio = new FileEntity("vedio",filename);
        fileService.savefile(vedio);
return filename ;
    }

    public byte[] loadImageAsBytes( String imageName) throws IOException {
        // Construct the image file path within the category directory
        Path imagePath = Paths.get(fileStorageConfig.getUploadDir()).resolve(imageName).normalize();

        // Load the image file as a byte array
        return Files.readAllBytes(imagePath);
    }

    private boolean isImage(MultipartFile file) {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }
    public Resource loadVideoAsResource(String videoFileName) {
        try {
            Path vidpath = Paths.get(fileStorageConfig.getUploadDir()).resolve(videoFileName).normalize();
            Resource resource = new UrlResource(vidpath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Video not found: " + videoFileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Video not found: " + videoFileName, ex);
        }
    }
}
