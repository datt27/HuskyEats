import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String dbURL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true";
		try {
			Connection conn = DriverManager.getConnection(dbURL);
			Statement stmt = conn.createStatement();
			Database db = new Database(conn,stmt);
			stmt = conn.createStatement();
			db.runQueries(stmt, conn);

		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
