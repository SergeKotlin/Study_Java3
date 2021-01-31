package lesson6.lesson;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LessonLog4j {

    public static void main(String[] args) {

        // Детский конфигуратор)))
//        BasicConfigurator.configure();
        PropertyConfigurator.configure("src/main/resources/logs/configs/log4j.properties");
        Logger admin = Logger.getLogger("admin");

        admin.info("This is info");
        admin.warn("This is warn");
        admin.error("This is error");
        admin.fatal("This is fatal");

        Logger file = Logger.getLogger("file");

        file.info("This is info");
        file.warn("This is warn");
        file.error("This is error");
        file.fatal("This is fatal");

    }
}
