package com.example.duan.RestController;

import com.example.duan.Service.FirebaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadFirebaseRestcontroller {

    @Autowired
    private FirebaseFileService firebaseFileService;

    @PostMapping("/uploadfile")
    public ResponseEntity create(@RequestParam(name = "file") MultipartFile file) {
        String downloadUrl = null;
        try {
            downloadUrl = firebaseFileService.save(file);
            // do whatever you want with that
        } catch (Exception e) {
            //  throw internal error;
        }
        return ResponseEntity.ok(downloadUrl);
    }

    @DeleteMapping("/deletefile")
    public void delete(@RequestParam(name = "fileName") String fileName) throws Exception {
        firebaseFileService.deleteFile(fileName);
    }
}
