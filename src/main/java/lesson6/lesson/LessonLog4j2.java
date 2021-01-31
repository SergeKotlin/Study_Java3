package lesson6.lesson;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LessonLog4j2 {

    public static void main(String[] args) {

        Logger logger = (Logger) LogManager.getLogger();


//        Property.createProperty(); Тимофей забыл как подключит вторую версию!
        logger.info("1");
        logger.warn("2");
        logger.error("3");

        try {
            Files.readAllBytes(Paths.get("src/1"));
        } catch (IOException e) {
            logger.error("error!", e);
        }
    }
}
