package lesson2.HW3_2;

//import jdk.swing.interop.SwingInterOpUtils;

// Все иимпорты д.б из sql.
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mainus {

    private static Connection connection;
    private static Statement jasonStatham;
    public static ResultSet rs;

    public static void main(String[] args) {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //execute() Главный метод отправки запросов
        //executeQuery() - SELECT (что-то получает и БД)
        //executeUpdate() - UPDATE, DELETE, INSERT  (ничего не возвращает. Просто отправляем запрос и делаем что-то на Базе)
        //<- сюда же READDATABASE, DROPBASE (DROP DATABASE)

        /*Runnable workSessionWithUser = new Runnable() {
            public void run() {
                System.out.println("Ваш приветствует наш магазин.\n(для окончания сеанса отправьте /quit)");
                start();
            }
        };
        Thread thread = new Thread(workSessionWithUser);
        thread.start();
        thread.interrupt();*/

        try {
            createProductTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            selectProductOfTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            disconnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*private static void start() {
        boolean session = true;
        while (session) {
            Scanner scan = new Scanner(System.in);
            String exit = scan.next();
//            if (exit.length() > 1) {
            if (exit.equals("/quit")) {
                System.out.println("[sd[f[s");
                session = false;
            }
        }
    }*/
    //-- Не смог просто реализовать логику. С одной стороны, вся программа должна быть в цикле while.
    // С другой - здесь же нужен новый свободный поток для ожидания exit в консоли в любой момент.
    // Ну и стретьей - из-за вложенности Runnable - мы уже в этом потоке) Вместе с exit. Что по сути эквивалентно,
    // если бы оставались в main

    private static void createProductTable() throws SQLException {
        // Волшебное "if not exists" не забываем! ("если не существует")
        jasonStatham.executeUpdate("CREATE TABLE if not exists products (" +
                    "ID CHAR(5), name VARCHAR(20), cost INTEGER" +
                ");" );

        jasonStatham.executeUpdate("DELETE FROM products");

        String[][] assortment = new String[][]{{"belt", "3500"}, {"choker", "2500"}, {"lash", "5000"}, {"body", "8000"}};


        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            String[] item = assortment[(int)(Math.random()*assortment.length)];
            jasonStatham.addBatch(String.format("INSERT INTO products (ID, name, cost) VALUES ('article%d', '%s', " + "%d)", i, item[0], Integer.parseInt(item[1])));
        }
        jasonStatham.executeBatch();
        connection.setAutoCommit(true);

    }

    private static void selectProductOfTable() throws SQLException {

        //Написать консольное приложение, которое позволяет узнать цену товара по его имени,
        // либо вывести сообщение «Такого товара нет», если товар не обнаружен в базе.
//      --для теста  jasonStatham.executeUpdate("DELETE FROM products");


        // findingProduct();
    // (Без сканера) String nameItem = "belt";
    // rs = jasonStatham.executeQuery(String.format("SELECT name, cost FROM products WHERE name = '%s'", nameItem));

        questionForUser();
    // (Со сканером)
        searchingForUserOneNameFilter();

        // updatePrtoduct();
    // (Без сканера) String itemName = "belt";
    // String newCostOfItem = "4000";

    // (Со сканером)
        // <- некогда делать мульти-question. Знаю, хорошо бы.
        updatePrtoduct();

        // filterByCost();
    // (Без сканера) rs = jasonStatham.executeQuery(String.format("SELECT name, cost FROM products WHERE cost BETWEEN '0' AND '5999'"));

    // (Со сканером)
        filterByCost();
    }

    private static void questionForUser() throws SQLException {
        System.out.println();
        System.out.println("Поиск продукта");
        System.out.println("Что мы ищем? Имя продукта введите");

        rs = jasonStatham.executeQuery(String.format("SELECT DISTINCT name FROM products;"));
        System.out.print("(возможные варианты: ");
        while (rs.next()) {
            System.out.printf("%s ", rs.getString("name"));
        }
        System.out.print(")\n");
    }

    private static void searchingForUserOneNameFilter() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String nameItem= scan.next();
        rs = jasonStatham.executeQuery(String.format("SELECT name, cost FROM products WHERE name = '%s'", nameItem));
        if (!rs.isBeforeFirst()) {
            System.out.println("Такого товара нет " + nameItem);
        } else {
            while (rs.next()) {
                System.out.printf("Name: %7s Cost: %d%n", rs.getString("name"), rs.getInt("cost"));
            }
        }
    }

    private static void updatePrtoduct() throws SQLException {
        questionAboutNameToUpdate();
        Scanner scanNameUpdate = new Scanner(System.in);
        String itemName= scanNameUpdate.next();

        questionAboutCostToUpdate();
        Scanner scanCostUpdate = new Scanner(System.in);
        String newCostOfItem= scanNameUpdate.next();

        jasonStatham.executeUpdate(String.format("UPDATE products SET cost = '%s' WHERE name = '%s'", newCostOfItem, itemName));
    }

    private static void questionAboutNameToUpdate() throws SQLException {
        System.out.println();
        System.out.println("Обновление базы товаров");
        System.out.println("Что меняем? Укажите имя продукта");

        rs = jasonStatham.executeQuery(String.format("SELECT DISTINCT name FROM products;"));
        System.out.print("(возможные варианты: ");
        while (rs.next()) {
            System.out.printf("%s ", rs.getString("name"));
        }
        System.out.print(")\n");
    }

    private static void questionAboutCostToUpdate() throws SQLException {
        System.out.println("Укажите новую цену");
    }

    private static void filterByCost() throws SQLException {
        String costFrom = questionAboutMinimumCost();

        String costTo = questionAboutMaximumCost();

        rs = jasonStatham.executeQuery(String.format("SELECT name, cost FROM products WHERE cost BETWEEN '%s' AND '%s'", costFrom, costTo));
        while (rs.next()) {
            System.out.println();
            System.out.printf("Name: %7s Cost: %d%n", rs.getString("name"), rs.getInt("cost"));
        }
    }

    private static String questionAboutMinimumCost() {
        System.out.println();
        System.out.println("Фильтр по названию/цене");
        System.out.println("Какая цена нам нужна? Укажите начало интервала, включительно");
        Scanner scanCostPeriodFrom = new Scanner(System.in);
        return scanCostPeriodFrom.next();
    }

    private static String questionAboutMaximumCost() {
        System.out.println("Теперь укажите максимум, включительно");
        Scanner scanCostPeriodTo = new Scanner(System.in);
        String costTo = scanCostPeriodTo.next();
        return costTo;
    }

    private static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        // Для Баз Данных с аутентификацией: connection = DriverManager.getConnection("jdbc:sqlLite:src/main/java/resources/mainDB.db", "root", "");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/mainDB.db");
        jasonStatham = connection.createStatement();
        // stmt
    }

    private static void disconnect() throws SQLException {
        // Обязательно тушить базу уходя. Как свет
        connection.close();
    }
}

/** ✓ 1. Подключиться к БД
 ✓ 2. Создать таблицу товаров (good_id, good_name, good_price) запрсом из Java приложения.
 ✓ 3. При запуске приложения очистить таблицу и заполнить 10000 товаров
 -косоль пока не сделана✓ 4. Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо вывести сообщение «Такого товара нет», если товар не обнаружен в базе.
 ✓ 5. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
 ✓ 6. Вывести товары в заданном ценовом диапазоне.
    Вам понадобятся: SELECT, DELETE, WHERE, UPDATE, LIKE, BETWEEN
 **/
//Serega, sure