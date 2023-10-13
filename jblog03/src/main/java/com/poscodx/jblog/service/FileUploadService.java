package com.poscodx.jblog.service;

import com.poscodx.jblog.exception.FileUploadServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;


@Service
public class FileUploadService {
    private static String SAVE_PATH = "/Users/nabi/Study/poscodx2023/00tools/jblog-uploads";
    private static String URL_PATH = "/assets/upload-images" ;

    public static String restore(MultipartFile file) {

        String url = null;
        String extName;
        try {
            File uploadDirectory = new File(SAVE_PATH);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            if (file.isEmpty()) {
                return url;
            }

            String originalFilename = file.getOriginalFilename();
            extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String saveFilename = generateSaveFilename(extName);
            Long fileSize = file.getSize();

            System.out.println("#####" + originalFilename);
            System.out.println(saveFilename);
            System.out.println("#####" + fileSize);
            System.out.println(file.getOriginalFilename());

            byte[] data = file.getBytes();
            OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
            os.write(data);
            os.close();

            url = URL_PATH + "/" + saveFilename;

        } catch (IOException ex) {
            throw new FileUploadServiceException(ex.toString());
        }
        return url;
    }

    private static String generateSaveFilename (String extName){
        String filename = "";

        Calendar calendar = Calendar.getInstance();
        filename += calendar.get(Calendar.YEAR);
        filename += calendar.get(Calendar.MONTH);
        filename += calendar.get(Calendar.DATE);
        filename += calendar.get(Calendar.HOUR);
        filename += calendar.get(Calendar.MINUTE);
        filename += calendar.get(Calendar.SECOND);
        filename += calendar.get(Calendar.MILLISECOND);
        filename += ("." + extName);
        System.out.println(filename + "<----filename");

        return filename;
    }
}
