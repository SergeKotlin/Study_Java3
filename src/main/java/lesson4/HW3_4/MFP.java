package lesson4.HW3_4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static lesson4.HW3_4.MFP.cmd.*;

public class MFP {
    static DocumentToFile newDoc1 = new DocumentToFile();
    static DocumentToFile newDoc2 = new DocumentToFile();
//    static boolean isPrinting = false;
//    static boolean isScaning = false;

    public static void main(String[] args) {
        newDoc1.setDocName("Док 1");
        newDoc2.setDocName("Док 2");
        startMFPThread(1, newDoc1, PRINTDOC);
        startMFPThread(2, newDoc2, SCANDOC);
        startMFPThread(3, newDoc2, PRINTDOC);

        // Без потоков методы
//        scaningDoc(newDoc1);
//        scaningDoc(newDoc2);

//        printDoc(newDoc1);
//        printDoc(newDoc2);
    }

    protected static void scaningDoc(DocumentToFile doc) {
        synchronized(doc) {
            System.out.println("На скан подан документ " + doc.getDocName());
            for (int i = 0; i < 5; i++) {
                System.out.println("Отсканирована страница " + (i + 1) + " (" + doc.getDocName() + ")");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected static void printDoc(DocumentToFile doc) {
        synchronized (doc) {
            System.out.println("На печать подан документ " + doc.getDocName());
//            while (!isPrinting) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Отпечатана страница " + (i + 1) + " (" + doc.getDocName() + ")");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//            }
        }
    }

    protected static void startMFPThread(int threadName, DocumentToFile doc, cmd command) {
        var thread = new Thread(() -> {
            Thread.currentThread().setName("Thread_" + threadName);
            String nameOfCurrentThread = Thread.currentThread().getName();
            System.out.println("Поток " + nameOfCurrentThread + " начал действие");
            try {
                Thread.sleep(100);
                // Действие объекта
                switch (command) {
                    case PRINTDOC:
                        printDoc(doc);
                        break;
                    case SCANDOC:
                        scaningDoc(doc);
                        break;
//                    case PRINT_SCANDOC:
//                        DocumentToFile doc2 = new DocumentToFile();
//                        doc2 = doc;
//                        scaningDoc();
//                        printDoc();
//                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

//    enum cmd { PRINTDOC, SCANDOC, PRINT_SCANDOC }
    enum cmd { PRINTDOC, SCANDOC }
}


class DocumentToFile {
    private String docName;
    static private int repeatCount = 10;

    static String[] docText = {"THE WORLD MAY END IN 2021 BUT THIS SHOW WON'T",
                        "WHEN I SLEPT IN CLASS, IT WAS NPT TO HELP LEO DICAP..",
                        "I AM NOT SMARTER THAN THE PRESIDENT",
                        "GENETICS IS NOT AN EXCUSE",
                        "MAKING MILHOUSE CRY IS NOT A SCIENCE PROJECT",
                        "I WILL NOT INSTIGATE REVOLUTION",
                        "I WILL FINISH WHAT I STA...",
                        "THEY ARE LAUGHING AT ME, NOT WITH ME",
                        "I WILL NOT XEROX MY BUTT",
                        "FUNNY NOISES ARE NOT FUNNY"};

    static File file = new File("src/main/java/lesson4/HW3_4/resourcesLib/file.txt");

    protected void setDocName(String docName) {
        this.docName = docName;
    }

    protected static String[] getDocText() {
        return docText;
    }

    protected String getDocName() {
        return docName;
    }

    protected static File getFile() {
        return file;
    }

    protected static void startWriterThread(int threadName) {
        var thread = new Thread(() -> {
            Thread.currentThread().setName("Thread_" + threadName);
            String nameOfCurrentThread = Thread.currentThread().getName();
            System.out.println("Поток " + nameOfCurrentThread + " начал действие");
            try {
                Thread.sleep(100);
                // Действие объекта
                writeToFile(DocumentToFile.getFile(), DocumentToFile.getDocText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    protected static void writeToFile(File file, String[] docText) {
        try (FileWriter writer = new FileWriter(file, true)) {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                    int indexRandom = randomIndex();
                    writer.write(docText[indexRandom]+"\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    protected static int randomIndex() {
        int indexRandom = Math.abs( (int)(repeatCount*Math.random()) - 1 );
        return indexRandom;
    }
}

/** ✓2. Написать небольшой метод, в котором 3 потока построчно пишут данные в файл
 * (по 10 записей с периодом в 20 мс).
 *
 ✓3. Написать класс МФУ, на котором возможно одновременно выполнять печать и сканирование документов,
 * но нельзя одновременно печатать или сканировать два документа.
 * При печати в консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы»,
 * при сканировании – аналогично «Отсканировано...».
 * Вывод в консоль с периодом в 50 мс.
 * **/
//Serega, sure
