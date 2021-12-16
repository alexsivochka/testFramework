package at.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAttachment {

    private CustomAttachment() {}

    /** Добавить в отчет Аллюр призвольное описание шага */
    @Step("{stepName}")
    public static void addStepToTheReport(String stepName)  {
        log.info(stepName);
    }

    /** Добавить в отчет Аллюр файл по имени */
    public static void addAttachmentAllure(String nameFile) {
        Path content = Paths.get(nameFile);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment(nameFile, is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Добавить в отчет Аллюр Page Object в виде json*/
    @Attachment(value = "Attachment", type = "application/json")
    public static byte[] getDocTestData(Object obj) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(obj).getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
