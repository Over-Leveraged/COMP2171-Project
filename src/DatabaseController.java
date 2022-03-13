import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
	static final String DB_URL = "jdbc:mysql://localhost";
	static final String USER = "root";
	static final String PASS = "";
	//static final String QUERY = "SELECT * FROM Property";

	public static void main(String[] args) throws SQLException {
		try {
			//Current jdbc driver (Connector/J 8.0.28)
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Open a connection
		try{
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE DATABASE IF NOT EXISTS `GUARDSDB`");
			stmt.executeUpdate("USE `GUARDSDB`");
			
			//Table structure for table `addrec`
			stmt.execute("CREATE TABLE IF NOT EXISTS `addrec`("
					+ "`id` int(11) NOT NULL,"
					+ "`fname` varchar(250) DEFAULT NULL,"
					+ "`lname` varchar(250) DEFAULT NULL,"
					+ "`company` varchar(250) DEFAULT NULL,"
					+ "`position` varchar(250) DEFAULT NULL,"
					+ "`service_lenght` varchar(250) DEFAULT NULL,"
					+ "`avsec_dates` date DEFAULT NULL,"
					+ "`avsec_grades` varchar(250) DEFAULT NULL,"
					+ "`medical_doc` varchar(255) DEFAULT NULL,"
					+ "`medical_doc_path` varchar(255) DEFAULT NULL,"
					+ "`medical_doc_expire` date DEFAULT NULL,"
					+ "`psra_doc` blob DEFAULT NULL,"
					+ "`psra_doc_path` varchar(255) DEFAULT NULL,"
					+ "`psra_doc_expire` date DEFAULT NULL,"
					+ "`police_rec_doc` blob DEFAULT NULL,"
					+ "`police_rec_doc_path` varchar(255) DEFAULT NULL,"
					+ "`police_rec_doc_expire` date DEFAULT NULL,"
					+ "`recommenedBy` varchar(250) DEFAULT NULL,"
					+ "`createdAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),"
					+ "PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
			
			//Table structure for table `training`
			stmt.execute("CREATE TABLE IF NOT EXISTS `training`("
					+ "`id` int(11) NOT NULL,"
					+ "`fname` varchar(250) DEFAULT NULL,"
					+ "`lname` varchar(250) DEFAULT NULL,"
					+ "`date` date NOT NULL DEFAULT current_timestamp(),"
					+ "`reason` text NOT NULL,"
					+ "`message` text NOT NULL,"
					+ "PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
			
			//Table structure for table `users`
			stmt.execute("CREATE TABLE IF NOT EXISTS `users`("
					+ "  `id` int(11) NOT NULL,"
					+ "  `username` varchar(250) NOT NULL,"
					+ "  `email` varchar(250) NOT NULL,"
					+ "  `password` varchar(250) NOT NULL,"
					+ "PRIMARY KEY(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
			
			//AUTO_INCREMENT for table `addrec`
			stmt.executeUpdate("ALTER TABLE `addrec` "
					+ "MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;");
			
			//AUTO_INCREMENT for table `training`
			stmt.executeUpdate("ALTER TABLE `training` "
					+ "MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;");
			
			//AUTO_INCREMENT for table `users`
			stmt.executeUpdate("ALTER TABLE `users` "
					+ "MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;");
			

			//INSERT Data for table `addrec`
			stmt.executeUpdate("INSERT IGNORE INTO `addrec` (`id`, `fname`, `lname`, `company`, `position`, `service_lenght`, `avsec_dates`, `avsec_grades`, `medical_doc`, `medical_doc_path`, `medical_doc_expire`, `psra_doc`, `psra_doc_path`, `psra_doc_expire`, `police_rec_doc`, `police_rec_doc_path`, `police_rec_doc_expire`, `recommenedBy`, `createdAt`) VALUES"
					+ "(8, 'Akiel', 'Walsh', 'tazzuda', 'ceo', '737', '2021-11-23', '1', 'info2180-lecture-7.pdf', 'upload/info2180-lecture-7.pdf', '2021-11-17', 0x696e666f323138302d6c6563747572652d352e706466, 'upload/info2180-lecture-6.pdf', '2021-11-19', 0x696e666f323138302d6c6563747572652d362e706466, 'upload/info2180-lecture-5.pdf', '2021-11-15', '2021-11-27', '2021-11-24 21:29:47'),"
					+ "(9, 'tahjay', 'thompson', 'tazzuda', 'ceo', '737', '2021-11-23', '1', 'info2180-lecture-7.pdf', 'upload/info2180-lecture-7.pdf', '2021-11-17', 0x696e666f323138302d6c6563747572652d352e706466, 'upload/info2180-lecture-6.pdf', '2021-11-19', 0x696e666f323138302d6c6563747572652d362e706466, 'upload/info2180-lecture-5.pdf', '2021-11-15', '2021-11-27', '2021-11-24 21:29:58');");

			
			//INSERT Data for table `users`
			stmt.executeUpdate("INSERT IGNORE INTO `users` (`id`, `username`, `email`, `password`) VALUES"
					+ "(1, 'awalsh', 'awalsh@test.com', '482c811da5d5b4bc6d497ffa98491e38'),"
					+ "(2, 'tthompson', 'tthompson@test.com', '482c811da5d5b4bc6d497ffa98491e38');");
			

			//stmt.executeUpdate("COMMIT");
			
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
