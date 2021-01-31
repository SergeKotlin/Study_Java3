package lesson6.lesson;

import java.io.IOException;
import java.util.logging.*;

public class LessonTestLog {

    protected static final Logger logger = Logger.getLogger("");

    public static void main(String[] args) {
        logger.setLevel(Level.ALL); // Выбираем уровень показа логирования
        logger.getHandlers()[0].setLevel(Level.ALL);
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + "\t" + record.getMessage() + "\t" + record.getMillis() + "\n";
            }
        });

        logger.getHandlers()[0].setFilter(record -> record.getMessage().startsWith("Checkpoint"));

        try {
            Handler handler = new FileHandler("src/main/resources/logs/logs",true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.log(Level.SEVERE, "Checkpoint S");
        logger.log(Level.INFO, "Checkpoint I");
        logger.warning("W");
        logger.warning("Checkpoint W");
    }
    
    // Levels of logging:
    //OFF
    //SEVERE
    //WARNING
    //INFO
    //CONFIG
    //FINE(3)
    //ALL
}
