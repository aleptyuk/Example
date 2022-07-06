package example.logger;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;

@Slf4j
public class AllureReportUtils {
    private AllureReportUtils() {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachPng(String description, byte[] bytes) {
        return bytes;
    }

    @Attachment(value = "LOG", type = "text/html")
    public static byte[] attachFile(File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            log.error("Failed to load file", e);
        }
        return new byte[0];
    }
}
