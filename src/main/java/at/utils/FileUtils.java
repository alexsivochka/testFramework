package at.utils;

import lombok.extern.log4j.Log4j;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j
public class FileUtils {

    public static File getFileFromResource(String fileName){
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    public static String getFileCheckSum(File file){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(file.toPath()));
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFileContent(String fileName){
        String content = null;
        try {
            content = Files.readString(getFileFromResource(fileName).toPath());
            log.info(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void main(String[] args) {
        readFileContent("test.txt");
    }
}
