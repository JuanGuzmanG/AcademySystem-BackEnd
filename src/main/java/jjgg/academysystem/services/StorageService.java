package jjgg.academysystem.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface StorageService {
    void init() throws IOException;

    String store(MultipartFile file);

    Resource loadAsResource(String filename);

    void delete(String filename) throws IOException;
}
