package lesson2;

import java.sql.*;

public class Main {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String[] args) {
        try {
            connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //execute()
        //executeQuery() - SELECT
        //executeUpdate() - UPDATE, DELETE, INSERT

        try {
            selectStudents();
            System.out.println(selectUser("user1", "1111"));
            System.out.println(selectUser("user2", "1111"));
            System.out.println(selectUser("user3", "3333"));

//            updateUsername("user3", "Gendalf_White");

//            createUsers();

//            prepareStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            disconnect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void prepareStatement() throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO auth (login, password, username) VALUES " + "(?, ?, ?)");
        for (int i = 4; i < 100; i++) {
            pstmt.setString(1, "user" + i);
            pstmt.setString(2, "pass" + i);
            pstmt.setString(3, "username" + i);

            pstmt.addBatch();
        }

        pstmt.executeBatch();
    }

    private static void createUsers() throws SQLException {
        long startTime = System.currentTimeMillis();

        connection.setAutoCommit(false);
        for (int i = 4; i < 100; i++) {
            stmt.addBatch(String.format("INSERT INTO auth (login, password, username) VALUES ('user%d', '%d', " + "'username%d')", i, i * 100, i));
        }
        stmt.executeBatch();
        connection.setAutoCommit(true);

        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void updateUsername(String login, String newUsername) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE auth SET username = '%s' WHERE login = '%s'", newUsername, login));
    }

    private static String selectUser(String login, String password) throws SQLException {
        rs = stmt.executeQuery(String.format("SELECT password, username FROM auth WHERE login = '%s'", login));
        String passDB = rs.getString("password");
        String usernameDB = rs.getString("username");
        String username = null;
        if (passDB != null) {
            username = (passDB.equals(password)) ? usernameDB : null;
        }
        return username;

    }

    private static void selectStudents() throws SQLException {
        rs = stmt.executeQuery("SELECT name, age FROM students WHERE age BETWEEN 25 AND 27");

        while (rs.next()) {
            System.out.printf("Name: %7s age: %d%n", rs.getString("name"), rs.getInt("age"));
        }
    }

    private static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/mainDB.db");
        stmt = connection.createStatement();

    }

    private static void disconnect() throws SQLException {
        connection.close();
    }

}
