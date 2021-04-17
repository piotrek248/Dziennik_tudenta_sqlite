public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();

        dbConnection.insertUczniowie("Piotr", "Surowski");
        dbConnection.selectUczniowiebyImie("Piotr");
    }
}
