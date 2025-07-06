package jjgg.academysystem.services; // Asegúrate de que el paquete sea correcto

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Primary // Le indica a Spring que prefiera esta implementación de StorageService
public class AzureBlobStorageService implements StorageService {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.blob-container-name}")
    private String containerName;

    private BlobContainerClient containerClient;

    // El @PostConstruct se reemplaza por una inicialización perezosa para el cliente
    private BlobContainerClient getContainerClient() {
        if (containerClient == null) {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                    .connectionString(connectionString)
                    .buildClient();
            containerClient = blobServiceClient.getBlobContainerClient(containerName);
        }
        return containerClient;
    }

    @Override
    public void init() throws IOException {
        // No es necesario para Azure Blob Storage, el contenedor se crea en el portal.
        System.out.println("Azure Blob Storage initialized. Container: " + containerName);
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Cannot store empty file");
            }
            // Generar un nombre de archivo único para evitar colisiones
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String uniqueFilename = UUID.randomUUID().toString() + "." + extension;

            BlobClient blobClient = getContainerClient().getBlobClient(uniqueFilename);

            try (InputStream inputStream = file.getInputStream()) {
                blobClient.upload(inputStream, file.getSize(), true); // El 'true' sobreescribe si ya existe
            }

            // Devolvemos la URL completa y pública del blob
            return blobClient.getBlobUrl();

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public void delete(String fileUrl) throws IOException {
        if (fileUrl == null || fileUrl.isBlank()) {
            return;
        }
        try {
            // Extraemos el nombre del archivo de la URL
            String filename = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
            BlobClient blobClient = getContainerClient().getBlobClient(filename);
            blobClient.deleteIfExists();
        } catch (Exception e) {
            // Puedes loggear el error, pero no relanzarlo para no interrumpir el flujo si la eliminación falla
            System.err.println("Failed to delete blob: " + fileUrl + " - " + e.getMessage());
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        // Esta función es menos relevante ahora que usamos URLs públicas,
        // pero se puede implementar si necesitas descargar el archivo en el backend.
        // Por simplicidad, la dejaremos sin implementación completa.
        throw new UnsupportedOperationException("loadAsResource is not implemented for Azure Blob Storage in this example.");
    }
}