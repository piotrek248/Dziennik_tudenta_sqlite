import java.sql.*;
import java.util.List;

public class DBConnection {
    private final String DBENGINE = "org.sqlite.JDBC";
    private final String DBURL = "jdbc:sqlite:uczniowie.db";

    private Connection connection;
    private Statement statement;



    public DBConnection(){
        try {
            Class.forName(DBConnection.this.DBENGINE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DBURL);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        createTable();
    }

    private final String createTableUczniowie = "CREATE TABLE IF NOT EXISTS uczniowie (id_ucznia INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", imie varchar(100)" +
            ", nazwisko varchar(100));";
    private final String createTableOceny = "CREATE TABLE IF NOT EXISTS oceny (id_wpisu INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", id_ucznia INTEGER" +
            ", ocena INTEGER);";

    public void createTable(){
        try {
            statement.execute(createTableUczniowie);
            statement.execute(createTableOceny);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertUczniowie(String imie, String nazwisko){
        String statementUczniowie = "INSERT INTO uczniowie (id_ucznia, imie, nazwisko) VALUES (NULL,"
                + "\""+ imie + "\"" + "," + "\"" + nazwisko + "\"" +") ";

        try {
            statement.execute(statementUczniowie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertOceny(int id, int ocena){
        String statementOceny = "INSERT INTO oceny VALUES (NULL, +"+ id+","+ocena+")";

        try {
            statement.execute(statementOceny);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectUczniowiebyImie(String imie){
        String querryUczniowie = "SELECT * FROM uczniowie WHERE imie="+ "\"" + imie + "\"" +"";
        List<String> result = null;
        ResultSet data = null;
        try {
            data = statement.executeQuery(querryUczniowie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println(data.getString("imie"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectUczniowiebyNazwisko(String nazwisko){
        String querryUczniowie = "SELECT * FROM uczniowie WHERE imie="+nazwisko+"";
        try {
            statement.execute(querryUczniowie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectOceny(int ocena){
        String querryUczniowie = "SELECT * FROM oceny WHERE ocena="+ocena+"";
        try {
            statement.execute(querryUczniowie);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
