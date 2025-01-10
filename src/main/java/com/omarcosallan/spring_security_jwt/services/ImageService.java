package com.omarcosallan.spring_security_jwt.services;

import com.omarcosallan.spring_security_jwt.domain.User;
import com.omarcosallan.spring_security_jwt.exceptions.FileStorageException;
import com.omarcosallan.spring_security_jwt.exceptions.InvalidFileException;
import com.omarcosallan.spring_security_jwt.exceptions.ObjectNotFoundException;
import com.omarcosallan.spring_security_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private UserRepository userRepository;

    public URI uploadProfilePicture(MultipartFile file) {
        validateFile(file);

        String sanitizedFilename = file.getOriginalFilename()
                .replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        String filename = UUID.randomUUID() + "-" + sanitizedFilename;

        try {
            uploadToS3(file, filename);

            URL fileUrl = getFileUrlFromS3(filename);
            updateUserAvatar(fileUrl.toString());

            return fileUrl.toURI();
        } catch (IOException e) {
            throw new FileStorageException("Error reading file data: " + e.getMessage());
        } catch (Exception e) {
            throw new FileStorageException("Failed to upload file: " + e.getMessage());
        }
    }

    private void uploadToS3(MultipartFile file, String filename) throws IOException {
        PutObjectRequest putObj = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        s3Client.putObject(putObj, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    private URL getFileUrlFromS3(String filename) {
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        return s3Client.utilities().getUrl(request);
    }

    private void updateUserAvatar(String fileUrl) {
        User user = userRepository.findByEmail(Objects.requireNonNull(UserService.authenticated()).getUsername())
                .orElseThrow(() -> new ObjectNotFoundException("User is not found."));
        user.setAvatarUrl(fileUrl);
        userRepository.save(user);
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("File is null or empty.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new InvalidFileException("Invalid file type. Only image files are allowed.");
        }
    }
}
