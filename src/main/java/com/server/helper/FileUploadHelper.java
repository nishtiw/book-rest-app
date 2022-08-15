package com.server.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//This is a Helper Class
@Component
public class FileUploadHelper {
//    Static path
//    public final String UPLOAD_DIR="F:\\IntellijProjects\\bookrest\\src\\main\\resources\\static\\images";
//    Dynamic path
    public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        boolean f = false;
        try {
//            OLD APPROACH
//            InputStream is = multipartFile.getInputStream(); //Read image
//            byte data[] = new byte[is.available()]; //is.available() will return size of data and accordingly array will be created
//            is.read(data);
//
////            write uploaded file to path
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());
//            fos.write(data);
//            fos.flush();
//            fos.close();
//            f = true;

//            using nio package - NEW APPROACH
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
