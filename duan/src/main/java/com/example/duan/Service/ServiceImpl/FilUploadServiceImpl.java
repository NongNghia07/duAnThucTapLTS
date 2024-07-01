package com.example.duan.Service.ServiceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.duan.Service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FilUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile[] file) throws IOException {
        for (int i = 0; i < file.length; i++) {
        cloudinary.uploader().upload(file[i].getBytes(),
                ObjectUtils.asMap("resource_type", "auto",
                        "folder", "Java-Test",
                        "use_filename", true, "unique_filename", true));
        }
        return "";
    }
}
