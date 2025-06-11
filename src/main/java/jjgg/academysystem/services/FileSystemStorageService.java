package jjgg.academysystem.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    @PostConstruct
    @Override
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try{
            if(file.isEmpty()) {
                throw new IllegalArgumentException("Cannot store empty file");
            }
            String Originalfilename = file.getOriginalFilename();

            String extension = StringUtils.getFilenameExtension(Originalfilename);

            String uniqueFilename = UUID.randomUUID().toString();
            if(extension != null && !extension.isEmpty()) {
                uniqueFilename += "." + extension;
            }

            Path destinationFile = rootLocation.resolve(Paths.get(uniqueFilename))
                .normalize().toAbsolutePath();

            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return uniqueFilename;
        } catch (IOException e) {
            throw new RuntimeException("Field to store file",e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists()||resource.isReadable()){
                return resource;
            } else{
                throw new RuntimeException("Could not read file: " + filename);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: ",e);
        }
    }
}
