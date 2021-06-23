package at.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

    public static File getFileFromResource(String fileName){
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    public static String getFileCheckSum(File file){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(file.toPath()));
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public static String readFileContent(String fileName){
        String content = null;
        try {
            content = Files.readString(getFileFromResource(fileName).toPath());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void main(String[] args) {
        readFileContent("test.txt");
    }
}
