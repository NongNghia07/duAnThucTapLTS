package com.example.duan.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseFileService {

    @Value("${firebase.bucket-name}")
    private String bucketName;

    private Storage storage;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
//            ClassPathResource serviceAccount = new ClassPathResource("./serviceAccountKey.json");
            FileInputStream serviceAccount =
                    new FileInputStream("./serviceAccountKey.json");
            storage = StorageOptions.newBuilder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount)).
                    setProjectId("duanthuctap-lts").build().getService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String save(MultipartFile file) {
        try {
            String imageName = generateFileName(file.getOriginalFilename());
            Map<String, String> map = new HashMap<>();
            map.put("firebaseStorageDownloadTokens", imageName);
            BlobId blobId = BlobId.of("duanthuctap-lts.appspot.com", imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setMetadata(map)
                    .setContentType(file.getContentType())
                    .build();
            storage.create(blobInfo, file.getInputStream());

            return getDownloadUrl(blobInfo.getBlobId());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

    private String getDownloadUrl(BlobId blobId) {
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                blobId.getBucket(), blobId.getName().replace("/", "%2F"));
    }

    public boolean deleteFile(String fileName) {
        Storage stor = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("duanthuctap-lts.appspot.com", fileName);
        return stor.delete(blobId);
    }
}