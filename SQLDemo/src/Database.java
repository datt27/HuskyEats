import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	// constructor for database object
	public Database(Connection conn, Statement stmt) throws SQLException {
		createNewDatabase(conn, stmt);
	}
	
	private void createNewDatabase(Connection conn, Statement stmt) throws SQLException {

		stmt.executeUpdate("CREATE DATABASE test;");
		stmt.executeUpdate("USE test;");
		stmt.executeUpdate("CREATE TABLE Customer ("
				+ "FirstName VARCHAR(100),"
				+ "LastName VARCHAR(100),"
				+ "CustomerID INTEGER NOT NULL,"
				+ "CONSTRAINT CustomerPK PRIMARY KEY(CustomerID));");
		stmt.executeUpdate("CREATE TABLE CustomerOrder ("
				+ "CustomerID INTEGER NOT NULL,"
				+ "OrderID INTEGER NOT NULL,"
				+ "OrderTotal REAL,"
				+ "CONSTRAINT OrderPK PRIMARY KEY(OrderID),"
				+ "CONSTRAINT CustomerOrderFK FOREIGN KEY(CustomerID) "
				+ "REFERENCES Customer(CustomerID));");
		stmt.executeUpdate("CREATE TABLE CustomerAddress ("
				+ "CustomerID INTEGER NOT NULL,"
				+ "Address VARCHAR(200),"
				+ "CONSTRAINT CustomerFK FOREIGN KEY(CustomerID)"
				+ "REFERENCES Customer(CustomerID));");
		stmt.executeUpdate("INSERT INTO Customer (FirstName, LastName, CustomerID) VALUES"
				+ "('Clyde', 'Cyrodiil', 1),"
				+ "('Tiffany', 'Tamriel', 2),"
				+ "('Manny', 'Morrowind', 3),"
				+ "('Danny', 'Danggerfall', 4);");
		
		stmt.executeUpdate("INSERT INTO CustomerOrder (CustomerID, OrderID, OrderTotal) VALUES"
				+ "(1, 100, 10),"
				+ "(1, 101, 9),"
				+ "(2, 102, 10),"
				+ "(3, 103, 12.30);");
		stmt.executeUpdate("INSERT INTO CustomerAddress (CustomerID, Address) VALUES"
				+ "(1, '123 Main St.');");
	}
	
	public void runQueries(Statement stmt, Connection conn) throws SQLException {
		stmt.executeUpdate("USE test");
		ResultSet rs = stmt.executeQuery("SELECT FirstName as first, LastName as last "
				+ "FROM Customer JOIN CustomerOrder ON "
				+ "Customer.CustomerID=CustomerOrder.CustomerID WHERE "
				+ "CustomerOrder.OrderTotal = 10;");
		rs.next();
		System.out.print(rs.getString("first"));
		System.out.print(" ");
		System.out.println(rs.getString("last"));
		rs.next();
		System.out.print(rs.getString("first"));
		System.out.print(" ");
		System.out.println(rs.getString("last"));
	}

}
