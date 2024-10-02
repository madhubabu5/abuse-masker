package com.mychatapp.abusemasker.util;

import com.mychatapp.abusemasker.SpringBootAbuseMaskerApplication;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
@NoArgsConstructor
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    public String getFileAsString(String fileName) {
        try {
            InputStream inputStream = SpringBootAbuseMaskerApplication.class.getClassLoader()
                    .getResourceAsStream(fileName);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("ERROR :: Unable to read the file , cause: {} , trace : {}", e, e.getStackTrace());
            throw new RuntimeException("Unable to read the file due to " + e.getMessage());
        }
    }
}
