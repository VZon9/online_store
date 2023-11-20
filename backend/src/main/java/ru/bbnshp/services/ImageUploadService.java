package ru.bbnshp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    private final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";

    public void uploadImage(MultipartFile image, String fileName) throws IOException {
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName + ".png");
        try {
            Files.write(fileNameAndPath, image.getBytes());
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }
}
