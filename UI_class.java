import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.text.ParseException;

public class UI_class {

	private static Connection connection; // used to hold the jdbc connection to
											// the DB
	private static Statement statement; // used to create an instance of the
										// connection
	private static PreparedStatement prepStatement; // used to create a prepared
	private static PreparedStatement prepStatement2;												// statement, that will be
													// later reused
	private static ResultSet resultSet; // used to hold the result of your query
	private static ResultSet resultSet1,resultSet2,resultSet3;	
	static Queue<String> ff1=new LinkedList<String>();
	static Queue<String> ff2=new LinkedList<String>();
	static Queue<String> cc2=new LinkedList<String>();
	static Queue<String> aatime=new LinkedList<String>();
	static Queue<String> ddtime=new LinkedList<String>();
	static Queue<String> rf=new LinkedList<String>();
	static Queue<String> rd=new LinkedList<String>();
	
	private static String query; // this will hold the query we are using

	public static void ConnectDB() {
		String username, password;
		username = "mcs116"; // This is your username in oracle
		password = "4055543"; // This is your password in oracle

		try {
			// Register the oracle driver.
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			// This is the location of the database. This is the database in
			// oracle
			// provided to the class
			String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";

			// create a connection to DB on class3.cs.pitt.edu
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void task1_starter() throws IOException, SQLException, ParseException {
		System.out.println("Do you really want to erase the database? Enter Y for Yes N for No");
		Scanner in = new Scanner(System.in);
		String y = in.nextLine();
		if (y.equalsIgnoreCase("y")) {
			Admin_task1();
		} else {
			System.out.println("---");
			Display();

		}
	}

	public static void Display() throws IOException, SQLException, ParseException {
		int isAdmin;
		String y = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 1 for admin and 2 for user:");
		isAdmin = in.nextInt();
		if (isAdmin == 1) {
			System.out.println();
			System.out.println("1: Erase the database\n" + "2: Load airline information\n"
					+ "3: Load schedule information\n" + "4: Load pricing information\n" + "5: Load plane information\n"
					+ "6: Generate passenger manifest for specific flight on given day");
			int task = in.nextInt();
			switch (task) {
			case 1:
				task1_starter();
				break;
			case 2:
				Admin_task2();
				break;
			case 3:
				Admin_task3();
				break;
			case 4:
				Admin_task4();
				break;
			case 5:
				Admin_task5();
				break;
			case 6:
				Admin_task6();
				break;

			}
		} else {
			System.out.println();
			System.out.println("1: Add customer\n" + "2: Show customer info, given customer name\n"
					+ "3: Find price for flights between two cities\n" + "4: Find all routes between two cities\n"
					+ "5: Find all routes between two cities of a given airline\n"
					+ "6: Find all routes with available seats between two cities on given day\n"
					+ "7: For a given airline, find all routes with available seats between two cities on given day\n"
					+ "8: Add reservation\n" + "9: Show reservation info, given reservation number\n"
					+ "10: Buy ticket from existing reservation");

			int task = in.nextInt();
			switch (task) {
			case 1:
				Cust_task1();
				break;
			case 2:
				Cust_task2();
				break;
			case 3:
				Cust_task3();
				break;
			case 4:
				Cust_task4();
				break;
			case 5:
				Cust_task5();
				break;
			case 6:
				Cust_task6();
				break;
			case 7:
				Cust_task7();
				break;
			case 8:
				try {
					Cust_task8();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9:
				Cust_task9();
				break;
			case 10:
				Cust_task10();
				break;

			}

		}

	}

	public static void Example0() {

		int counter = 1;

		try {
			statement = connection.createStatement(); // create an instance
			String selectQuery = "delete from reservation_detail"; // sample
																	// query

			resultSet = statement.executeQuery(selectQuery); // run the query on
																// the DB table
			// System.out.println(resultSet.toString());

			// System.out.println("result set "+resultSet.next());
			while (resultSet.next()) {
				// System.out.println("in");
				System.out.println("Record " + counter + ": " + resultSet.getString(1) + ", " +

						resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getLong(4));
				counter++;
			}

		} catch (Exception Ex) {

			Ex.printStackTrace();

		} finally {
			try {
				if (statement != null)
					statement.close();
				// if (prepStatement != null) prepStatement.close();
			} catch (SQLException e) {
				System.out.println("Cannot close Statement. Machine error: " + e.toString());
				e.printStackTrace();
			}
		}
	}

	public static void Admin_task1() {
		System.out.println("inside task 1");

		int counter = 1;

		try {
			statement = connection.createStatement(); // create an instance
			String selectQuery1 = "delete from reservation_detail"; // sample
																	// query
			String selectQuery2 = "delete from reservation";
			String selectQuery3 = "delete from Price";
			String selectQuery4 = "delete from Plane";
			String selectQuery5 = "delete from Flight";
			String selectQuery6 = "delete from Airline";
			String selectQuery7 = "delete from Cdate";
			String selectQuery8 = "delete from Customer";
			resultSet = statement.executeQuery(selectQuery1);
			resultSet = statement.executeQuery(selectQuery2);
			resultSet = statement.executeQuery(selectQuery3);

			resultSet = statement.executeQuery(selectQuery5);
			resultSet = statement.executeQuery(selectQuery4);
			resultSet = statement.executeQuery(selectQuery6);
			resultSet = statement.executeQuery(selectQuery7);
			resultSet = statement.executeQuery(selectQuery8);

		} catch (Exception Ex) {

			Ex.printStackTrace();

		} finally {
			try {
				if (statement != null)
					statement.close();
				// if (prepStatement != null) prepStatement.close();
			} catch (SQLException e) {
				System.out.println("Cannot close Statement. Machine error: " + e.toString());
				e.printStackTrace();
			}
		}
	}

	public static void Admin_task2() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		System.out.println("Enter File name(eg. filename.csv)");
		String filename = in.nextLine();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		query = "insert into Airline values (?,?,?,?)";
		prepStatement = connection.prepareStatement(query);
		for (String line = br.readLine(); line != null; line = br.readLine()) {

			temp = line.split(",");
			System.out.println("got " + temp[0] + " - " + temp[1] + " - " + temp[2]);

			prepStatement.setString(1, temp[0]);
			prepStatement.setString(2, temp[1]);
			prepStatement.setString(3, temp[2]);
			// prepStatement.setString(4, temp[3]);
			prepStatement.setInt(4, Integer.parseInt(temp[4]));

			prepStatement.executeUpdate();

		}
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}

	}

	public static void Admin_task3() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		System.out.println("Enter File name(eg. filename.csv)");
		String filename = in.nextLine();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		query = "insert into flight values (?,?,?,?,?,?,?,?)";
		prepStatement = connection.prepareStatement(query);
		for (String line = br.readLine(); line != null; line = br.readLine()) {

			temp = line.split(",");
			System.out
					.println("got " + temp[0] + " - " + temp[1] + " - " + temp[2] + " - " + temp[3] + " - " + temp[7]);

			prepStatement.setString(1, temp[0]);
			prepStatement.setString(2, temp[1]);
			prepStatement.setString(3, temp[2]);
			prepStatement.setString(4, temp[3]);
			prepStatement.setString(5, temp[4]);
			prepStatement.setString(6, temp[5]);
			prepStatement.setString(7, temp[6]);
			prepStatement.setString(8, temp[7]);

			try {
				prepStatement.executeUpdate();
				System.out.println("done");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
				if (prepStatement != null)
					prepStatement.close();
			} catch (SQLException e) {
				System.out.println("Cannot close Statement. Machine error: " + e.toString());
			}

		}

	}

	public static void Admin_task4() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		System.out.println("Enter L to load file or C to change price");
		String lc = in.nextLine();
		if (lc.equalsIgnoreCase("L")) {
			System.out.println("Enter File name(eg. filename.csv)");

			String filename = in.nextLine();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			query = "insert into price values (?,?,?,?,?)";
			prepStatement = connection.prepareStatement(query);
			for (String line = br.readLine(); line != null; line = br.readLine()) {

				temp = line.split(",");
				System.out.println(
						"got " + temp[0] + " - " + temp[1] + " - " + temp[2] + " - " + temp[3] + " - " + temp[4]);

				prepStatement.setString(1, temp[0]);
				prepStatement.setString(2, temp[1]);
				prepStatement.setString(3, temp[2]);
				prepStatement.setInt(4, Integer.parseInt(temp[3]));
				prepStatement.setInt(5, Integer.parseInt(temp[4]));

				try {
					prepStatement.executeUpdate();
					System.out.println("done");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else {
			statement = connection.createStatement();
			System.out.println("Please Enter Departure city Eg. PIT");
			String d_city = in.nextLine();
			System.out.println("Please Enter Arrival city Eg.JFK ");
			String a_city = in.nextLine();
			System.out.println("Please Enter Low Price");
			int L_price = in.nextInt();
			System.out.println("Please Enter High Price");
			int H_price = in.nextInt();
			query = "update Price set Low_price = " + L_price + ",High_price = " + H_price
					+ " where Departure_city = \'" + d_city + "\' and Arrival_city =\'" + a_city + "\'";
			System.out.println(query);
			int result = statement.executeUpdate(query);
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				System.out.println("Cannot close Statement. Machine error: " + e.toString());
			}

		}
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}

	}

	public static void Admin_task5() throws IOException, SQLException, ParseException {
		connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
	    connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		Scanner in = new Scanner(System.in);
		String temp[];
		System.out.println("Enter File name(eg. filename.csv)");
		String filename = in.nextLine();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		query = "insert into Plane values (?,?,?,?,?,?)";
		prepStatement = connection.prepareStatement(query);
		for (String line = br.readLine(); line != null; line = br.readLine()) {

			temp = line.split(",");
			System.out
					.println("got " + temp[0] + " - " + temp[1] + " - " + temp[2] + " - " + temp[3] + " - " + temp[4]);
			String date_temp[];
			date_temp = temp[3].split("/");
			System.out.println(date_temp[0] + date_temp[1] + date_temp[2]);

			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
			// This is how you format a date so that you can use the setDate
			// format below
			java.sql.Date date_reg = new java.sql.Date(
					df.parse(date_temp[2] + "" + date_temp[0] + "" + date_temp[1]).getTime());

			prepStatement.setString(1, temp[0]);
			prepStatement.setString(2, temp[1]);
			prepStatement.setInt(3, Integer.parseInt(temp[2]));
			prepStatement.setDate(4, date_reg);
			prepStatement.setInt(5, Integer.parseInt(temp[4]));
			prepStatement.setString(6, temp[5]);

			try {
				prepStatement.executeUpdate();
				System.out.println("done");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		connection.commit();
	}

	public static void Admin_task6() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		statement = connection.createStatement();
		int counter = 1;
		String temp[];
		System.out.println("Enter Flight Number");
		String flight_num = in.nextLine();
		System.out.println("Enter Flight Date (yyyymmdd)");
		String flight_date = in.nextLine();
		System.out.println("Enter Flight time (hh24:mi:ss)");
		String flight_time = in.nextLine();

		query = "select salutation , first_name,last_name from Customer c where c.cid in( select cid from Reservation natural join (select distinct reservation_number from Reservation_detail where flight_number = \'"
				+ flight_num + "\' and flight_date =to_date('" + flight_date + " " + flight_time
				+ "','YYYYMMDD hh24:mi:ss')))";

		System.out.println(query);
		resultSet = statement.executeQuery(query); // run the query on the DB
													// table
		// System.out.println(resultSet.toString());

		// System.out.println("result set "+resultSet.next());
		while (resultSet.next()) {
			// System.out.println("in");
			System.out.println("Record " + counter + ": " + resultSet.getString(1) + ", " +

					resultSet.getString(2) + ", " + resultSet.getString(3));
			counter++;
		}
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}

	}

	public static void Cust_task1() throws IOException, SQLException, ParseException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int id = 0;

		System.out.println("Enter Salutation : ");
		String sal = in.nextLine();
		System.out.println("Enter First Name : ");
		String fname = in.nextLine();
		System.out.println("Enter Last Name : ");
		String lname = in.nextLine();
		String query1 = "select cid from Customer where first_name=\'" + fname + "\' and last_name=\'" + lname + "\'";
		System.out.println("query 1 : " + query1);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(query1);
		// System.out.println("---->> "+resultSet.next());
		if (resultSet.next()) {
			System.out.println(fname + " " + lname + " already exist in the database");
			Cust_task1();
		} else {
			System.out.println("Enter Credit card number : ");
			String ccnum = in.nextLine();
			System.out.println("Enter Credit card Expire date (yyyyMM): ");
			String ccdate = in.nextLine();
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
			// This is how you format a date so that you can use the setDate
			// format below
			java.sql.Date date_reg = new java.sql.Date(df.parse(ccdate + "" + 29).getTime());

			System.out.println("Enter Street name : ");
			String street = in.nextLine();
			System.out.println("Enter City : ");
			String city = in.nextLine();
			System.out.println("Enter State : ");
			String state = in.nextLine();
			System.out.println("Enter Phone Number : ");
			String phone = in.nextLine();
			System.out.println("Enter Email : ");
			String email = in.nextLine();
			System.out.println("Enter Frequest Flyer id(Enter null if not applicable ) : ");
			String freq = in.nextLine();
			if (freq.equalsIgnoreCase("null"))
				freq = freq.toUpperCase();
			String query2 = "select * from Customer order by cid desc";
			resultSet = statement.executeQuery(query2);
			if (resultSet.next())
				id = Integer.parseInt(resultSet.getString(1)) + 1;
			query = "insert into Customer values(?,?,?,?,?,?,?,?,?,?,?,?)";
			prepStatement = connection.prepareStatement(query);
			prepStatement.setInt(1, id);
			prepStatement.setString(2, sal);
			prepStatement.setString(3, fname);
			prepStatement.setString(4, lname);
			prepStatement.setString(5, ccnum);
			prepStatement.setDate(6, date_reg);
			prepStatement.setString(7, street);
			prepStatement.setString(8, city);
			prepStatement.setString(9, state);
			prepStatement.setString(10, phone);
			prepStatement.setString(11, email);
			prepStatement.setString(12, freq);
			prepStatement.executeUpdate();

		}

	}

	public static void Cust_task2() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		System.out.println("Enter First Name");
		String fname = in.nextLine();
		System.out.println("Enter Last Name");
		String lname = in.nextLine();

		// run the query on the DB table
		// System.out.println(resultSet.toString());

		// System.out.println("result set "+resultSet.next());
		if (resultSet.next()) {
			// System.out.println("in");
			System.out.println("Record " + ":\n " + "Id : " + resultSet.getString(1) + "\n " + "Name :"
					+ resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + "\n "
					+ "Credit Card Number : " + resultSet.getString(5) + "\n " + "Expiry Date : "
					+ resultSet.getString(6) + "\n " + "Street : " + resultSet.getString(7) + "\n " + "City : "
					+ resultSet.getString(8) + "\n " + "State : " + resultSet.getString(9) + "\n " + "Phone : "
					+ resultSet.getString(10) + "\n " + "Email : " + resultSet.getString(11) + "\n "
					+ "Frequent Flyer id : " + resultSet.getString(12) + "\n ");

		}

		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}

	}

	public static void Cust_task3() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int h1 = 0, l1 = 0, h2 = 0, l2 = 0;
		System.out.println("Enter Departure_city");
		String dcity = in.nextLine();
		System.out.println("Enter Arrival_City");
		String acity = in.nextLine();
		System.out.println();
		statement = connection.createStatement(); // create an instance
		String selectQuery = "select High_price , Low_price from price where departure_city = '" + dcity
				+ "' and Arrival_city = '" + acity + "'"; // sample query
		// 2
		System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		if (resultSet.next()) {
			System.out.println("One Way Trip from " + dcity + " To " + acity + " :");
			h1 = resultSet.getInt(1);
			System.out.println("HIGH PRICE " + h1);
			l1 = resultSet.getInt(2);
			System.out.println("LOW PRICE " + l1);
		}
		selectQuery = "select High_price , Low_price from price where departure_city = '" + acity
				+ "' and Arrival_city = '" + dcity + "'"; // sample query
		// System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		if (resultSet.next()) {
			System.out.println("One Way Trip from " + acity + " To " + dcity + " :");
			h2 = resultSet.getInt(1);
			System.out.println("HIGH PRICE " + h2);
			l2 = resultSet.getInt(2);
			System.out.println("LOW PRICE " + l2);
			System.out.println("Round Trip Cost from " + dcity + " to " + acity + " :");
			System.out.println("HIGH PRICE " + (h1 + h2));
			System.out.println("LOW PRICE " + (l1 + l2));
		}
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}

	public static void Cust_task4() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int h1 = 0, l1 = 0, h2 = 0, l2 = 0,a1time=0,a2time=0,d1time=0,d2time=0;
		int counter = 0;
		String f1=null,f2=null,c2=null,q1,q2,q,sched1 = null,sched2=null;
		System.out.println("Enter Departure_city");
		String dcity = in.nextLine();
		System.out.println("Enter Arrival_City");
		String acity = in.nextLine();
		System.out.println();
		statement = connection.createStatement(); // create an instance
		String selectQuery = "select * from Flight where departure_city = '" + dcity + "' and Arival_city = '" + acity
				+ "'"; // sample query
		// 2
		// System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		while (resultSet.next()) {
			System.out.println("Direct Trip from " + dcity + " To " + acity + " :");

			System.out.println("	Flight Number	Departure_city	Arrival_city	Departure_time	Arrival_time	");
			System.out.println("Record " + counter + ": " + resultSet.getString(1) + "		" +

					resultSet.getString(4) + "		" + resultSet.getString(5) + "		" + resultSet.getString(6)
					+ "		" + resultSet.getString(7));
			counter++;

		}
		//---------------------------1 leg ------------------------------------------------
		

		String Query = "select * from Flight_connection where city1 = '" + dcity + "' and city3 = '" + acity + "'"; // sample
		// query
// 2
		System.out.println(Query);
		resultSet1 = statement.executeQuery(Query);
		while (resultSet1.next()) {

			ff1.add(resultSet1.getString(1));
			ff2.add(resultSet1.getString(2));
			cc2.add(resultSet1.getString(4));
			// System.out.println(f1 + " " + f2 + " " + c2);
		}

		while (!ff1.isEmpty() && !ff2.isEmpty() && !cc2.isEmpty()) {

			f1 = ff1.poll();
			f2 = ff2.poll();
			c2 = cc2.poll();
			q1 = "select Arrival_time,departure_time , Weekly_schedule from flight where flight_number='"

					+ f1 + "'";
			resultSet2 = statement.executeQuery(q1);
			if (resultSet2.next()) {
				// System.out.println(resultSet.getString(1)+" $ "
				// +resultSet.getString(2)+" $ "+resultSet.getString(3));
				a1time = Integer.parseInt(resultSet2.getString(1));
				d1time = Integer.parseInt(resultSet2.getString(2));
				sched1 = resultSet2.getString(3);
				
				System.out.println(a1time + " " + d1time + " " + sched1 );
			}
			q2 = "select Arrival_time, departure_time , Weekly_schedule from flight where flight_number='"
					+ f2 + "'";
			resultSet3 = statement.executeQuery(q2);
			if (resultSet3.next()) {
				a2time = Integer.parseInt(resultSet3.getString(1));
				d2time = Integer.parseInt(resultSet3.getString(2));
				sched2 = resultSet3.getString(3);
				
				System.out.println(a2time + " " + d2time + " " + sched2 );
			}
			char[] a = sched1.toLowerCase().toCharArray();
			char[] b = sched2.toLowerCase().toCharArray();
			for (int i = 0; i < 7; i++) {
				System.out.println("a1time " + a1time + " d2time " + d2time + " diff is " + (d2time - a1time));
				if (a[i] == b[i] && a[i] != '-' && d2time - a1time > 100) {
					System.out.println(f1  + "		" + dcity + "		" + c2 + "		" + a1time
							+ "		" + d1time);
					System.out.println(f2 +  "		" + c2 + "		" + acity + "		" + a2time
							+ "		" + d2time);
					break;
				}
			}
		}

		
		connection.commit();
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}
	
	
	public static void Cust_task6() throws IOException, SQLException, ParseException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int a1time=0,a2time=0,d1time=0,d2time=0,at=0,dt=0,NoR=0,NoR1=0,NoR2=0,capacity = 0,capacity1 = 0,capacity2 = 0;
		int counter = 0;
		String f1=null,f2=null,c2=null,q1,q2,q,sched1 = null,sched2=null;
		System.out.println("Enter Departure city");
		String dcity = in.nextLine();
		System.out.println("Enter Arrival City");
		String acity = in.nextLine();
		System.out.println("Enter Flight Date (YYYYMMDD)");
		String date = in.nextLine();
		ResultSet rs;
		System.out.println();
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		// This is how you format a date so that you can use the setDate
		// format below
		java.sql.Date date_reg = new java.sql.Date(df.parse(date).getTime());

		CallableStatement cStmt = connection.prepareCall("{? = call getBookedTickets(?,?)}");
		statement = connection.createStatement(); // create an instance
		String selectQuery = "select * from Flight where departure_city = '" + dcity + "' and Arival_city = '" + acity
				+ "'"; 
	
		 System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		while (resultSet.next()) {
			ff1.add(resultSet.getString(1));
			ddtime.add(resultSet.getString(6));
			aatime.add(resultSet.getString(7));

		}
		System.out.println("Direct Trip from " + dcity + " To " + acity + " :");
		while(!(ff1.isEmpty() && ddtime.isEmpty() && aatime.isEmpty())){
			f1 = ff1.poll();
			dt = Integer.parseInt(ddtime.poll());
			at = Integer.parseInt(aatime.poll());
			cStmt.setString(2, f1);
			cStmt.setDate(3, date_reg);
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.execute();
			q1= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f1+"') natural join Plane)";
			System.out.println();
			NoR = cStmt.getInt(1);
			
			resultSet1 = statement.executeQuery(q1);
			if(resultSet1.next())
			capacity = resultSet1.getInt(1);
			System.out.println("capacity : "+capacity);
			if (NoR<capacity) {
				 	
				 	
				System.out.println("Nor is : "+NoR);
				

				System.out.println("	Flight Number	Departure_city	Arrival_city	Departure_time	Arrival_time	Available Seats");
				System.out.println("Record " + counter + ": " + f1 + "		" +

						dcity + "		" + acity + "		" + dt
						+ "		" + at+"		"+(capacity-NoR));
				counter++;

				
			}
			
		
		}
		System.out.println("------------------------------------------------------------------------------------------");
		//---------------------------1 leg ------------------------------------------------
		

		String Query = "select * from Flight_connection where city1 = '" + dcity + "' and city3 = '" + acity + "'"; // sample
		// query
// 2
		System.out.println(Query);
		resultSet1 = statement.executeQuery(Query);
		while (resultSet1.next()) {

			ff1.add(resultSet1.getString(1));
			ff2.add(resultSet1.getString(2));
			cc2.add(resultSet1.getString(4));
			// System.out.println(f1 + " " + f2 + " " + c2);
		}

		while (!ff1.isEmpty() && !ff2.isEmpty() && !cc2.isEmpty()) {

			f1 = ff1.poll();
			f2 = ff2.poll();
			c2 = cc2.poll();
			q1 = "select Arrival_time,departure_time , Weekly_schedule from flight where flight_number='"

					+ f1 + "'";
			resultSet2 = statement.executeQuery(q1);
			if (resultSet2.next()) {
				// System.out.println(resultSet.getString(1)+" $ "
				// +resultSet.getString(2)+" $ "+resultSet.getString(3));
				a1time = Integer.parseInt(resultSet2.getString(1));
				d1time = Integer.parseInt(resultSet2.getString(2));
				sched1 = resultSet2.getString(3);
				
				System.out.println(a1time + " " + d1time + " " + sched1 );
			}
			q2 = "select Arrival_time, departure_time , Weekly_schedule from flight where flight_number='"
					+ f2 + "'";
			resultSet3 = statement.executeQuery(q2);
			if (resultSet3.next()) {
				a2time = Integer.parseInt(resultSet3.getString(1));
				d2time = Integer.parseInt(resultSet3.getString(2));
				sched2 = resultSet3.getString(3);
				
				System.out.println(a2time + " " + d2time + " " + sched2 );
			}
			char[] a = sched1.toLowerCase().toCharArray();
			char[] b = sched2.toLowerCase().toCharArray();
			for (int i = 0; i < 7; i++) {
				//System.out.println("------------------------+++++++++++++");
				//System.out.println("a1time " + a1time + " d2time " + d2time + " diff is " + (d2time - a1time));
				if (a[i] == b[i] && a[i] != '-' && d2time - a1time > 100) {
					System.out.println("conditions true for "+f1+" "+f2);
					cStmt.setString(2, f1);
					cStmt.setDate(3, date_reg);
					cStmt.registerOutParameter(1, Types.INTEGER);
					cStmt.execute();
					NoR1 = cStmt.getInt(1);
					q1= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f1+"') natural join Plane)";
					//System.out.println(q1);
					resultSet1 = statement.executeQuery(q1);
					if(resultSet1.next())
					capacity1 = resultSet1.getInt(1);
					System.out.println("capacity1 : "+capacity1);
					System.out.println("NOR1 "+NoR1);
					if(NoR1<capacity1)
					{
						//System.out.println("in for q2");
						q2= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f2+"') natural join Plane)";
						//System.out.println(q2);
						resultSet2 = statement.executeQuery(q2);
						if(resultSet2.next())
						capacity2 = resultSet2.getInt(1);
						System.out.println("capacity2 : "+capacity2);
						cStmt.setString(2, f2);
						cStmt.setDate(3, date_reg);
						cStmt.registerOutParameter(1, Types.INTEGER);
						cStmt.execute();
						NoR2 = cStmt.getInt(1);
						System.out.println("NOR2 "+NoR2);
						
						if(NoR2<capacity2)
						
						{
							System.out.println("Flight Number	Departure_city	Arrival_city	Departure_time	Arrival_time	Available Seats");
						
							System.out.println(f1  + "		" + dcity + "		" + c2 + "		" + a1time
								+ "		" + d1time+"		"+(capacity1 - NoR1));
							System.out.println(f2 +  "		" + c2 + "		" + acity + "		" + a2time
								+ "		" + d2time+"		"+(capacity2 - NoR2));
						break;
					}}
				}
			}
		}

		
		//connection.commit();
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}
	
	public static void Cust_task7() throws IOException, SQLException, ParseException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int a1time=0,a2time=0,d1time=0,d2time=0,at=0,dt=0,NoR=0,NoR1=0,NoR2=0,capacity = 0,capacity1 = 0,capacity2 = 0;
		int counter = 0;
		String f1=null,f2=null,c2=null,q1,q2,q,sched1 = null,sched2=null,aid1=null,aid2=null;
		System.out.println("Enter Departure city");
		String dcity = in.nextLine();
		System.out.println("Enter Arrival City");
		String acity = in.nextLine();
		System.out.println("Enter Flight Date (YYYYMMDD)");
		String date = in.nextLine();
		System.out.println("Enter Name of the Airline");
		String aname = in.nextLine();
		
		System.out.println();
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		// This is how you format a date so that you can use the setDate
		// format below
		java.sql.Date date_reg = new java.sql.Date(df.parse(date).getTime());

		CallableStatement cStmt = connection.prepareCall("{? = call getBookedTickets(?,?)}");
		statement = connection.createStatement(); // create an instance
		 q = "select airline_id from Airline where airline_name='" + aname + "'";
		resultSet = statement.executeQuery(q);
		String aid = null;
		if (resultSet.next())
			aid = resultSet.getString(1);
		String selectQuery = "select * from Flight where departure_city = '" + dcity + "' and Arival_city = '" + acity
				+ "' and airline_id='"+aid+"'"; 
	
		 System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		while (resultSet.next()) {
			ff1.add(resultSet.getString(1));
			ddtime.add(resultSet.getString(6));
			aatime.add(resultSet.getString(7));

		}
		System.out.println("Direct Trip from " + dcity + " To " + acity + " :");
		while(!(ff1.isEmpty() && ddtime.isEmpty() && aatime.isEmpty())){
			f1 = ff1.poll();
			dt = Integer.parseInt(ddtime.poll());
			at = Integer.parseInt(aatime.poll());
			cStmt.setString(2, f1);
			cStmt.setDate(3, date_reg);
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.execute();
			q1= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f1+"') natural join Plane)";
			System.out.println();
			NoR = cStmt.getInt(1);
			
			resultSet1 = statement.executeQuery(q1);
			if(resultSet1.next())
			capacity = resultSet1.getInt(1);
			System.out.println("capacity : "+capacity);
			if (NoR<capacity) {
				 	
				 	
				System.out.println("Nor is : "+NoR);
				

				System.out.println("	Flight Number	Departure_city	Arrival_city	Departure_time	Arrival_time	Available Seats		Airline Id");
				System.out.println("Record " + counter + ": " + f1 + "		" +

						dcity + "		" + acity + "		" + dt
						+ "		" + at+"		"+(capacity-NoR)+"		"+aid);
				counter++;

				
			}
			
		
		}
		System.out.println("------------------------------------------------------------------------------------------");
		//---------------------------1 leg ------------------------------------------------
		System.out.println("Flight With Legs :");

		String Query = "select * from Flight_connection where city1 = '" + dcity + "' and city3 = '" + acity + "'"; // sample
		// query
// 2
		System.out.println(Query);
		resultSet1 = statement.executeQuery(Query);
		while (resultSet1.next()) {

			ff1.add(resultSet1.getString(1));
			ff2.add(resultSet1.getString(2));
			cc2.add(resultSet1.getString(4));
			// System.out.println(f1 + " " + f2 + " " + c2);
		}
		
		while (!ff1.isEmpty() && !ff2.isEmpty() && !cc2.isEmpty()) {

			f1 = ff1.poll();
			f2 = ff2.poll();
			c2 = cc2.poll();
			q1 = "select Arrival_time,departure_time , Weekly_schedule,airline_id from flight where flight_number='"

					+ f1 + "'";
			resultSet2 = statement.executeQuery(q1);
			if (resultSet2.next()) {
				// System.out.println(resultSet.getString(1)+" $ "
				// +resultSet.getString(2)+" $ "+resultSet.getString(3));
				a1time = Integer.parseInt(resultSet2.getString(1));
				d1time = Integer.parseInt(resultSet2.getString(2));
				sched1 = resultSet2.getString(3);
				aid1 = resultSet2.getString(4);
				//System.out.println(a1time + " " + d1time + " " + sched1 );
			}
			q2 = "select Arrival_time, departure_time , Weekly_schedule,airline_id from flight where flight_number='"
					+ f2 + "'";
			resultSet3 = statement.executeQuery(q2);
			if (resultSet3.next()) {
				a2time = Integer.parseInt(resultSet3.getString(1));
				d2time = Integer.parseInt(resultSet3.getString(2));
				sched2 = resultSet3.getString(3);
				aid2 = resultSet3.getString(4);
				
				//System.out.println(a2time + " " + d2time + " " + sched2 );
			}
			char[] a = sched1.toLowerCase().toCharArray();
			char[] b = sched2.toLowerCase().toCharArray();
			for (int i = 0; i < 7; i++) {
				//System.out.println("------------------------+++++++++++++");
				//System.out.println("a1time " + a1time + " d2time " + d2time + " diff is " + (d2time - a1time));
				if (a[i] == b[i] && a[i] != '-' && aid.equals(aid1) && aid.equals(aid2) && d2time - a1time > 100) {
					System.out.println("conditions true for "+f1+" "+f2);
					cStmt.setString(2, f1);
					cStmt.setDate(3, date_reg);
					cStmt.registerOutParameter(1, Types.INTEGER);
					cStmt.execute();
					NoR1 = cStmt.getInt(1);
					q1= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f1+"') natural join Plane)";
					//System.out.println(q1);
					resultSet1 = statement.executeQuery(q1);
					if(resultSet1.next())
					capacity1 = resultSet1.getInt(1);
					System.out.println("capacity1 : "+capacity1);
					System.out.println("NOR1 "+NoR1);
					if(NoR1<capacity1)
					{
						//System.out.println("in for q2");
						q2= "select plane_capacity from ((select airline_id , plane_type from Flight where flight_number='"+f2+"') natural join Plane)";
						//System.out.println(q2);
						resultSet2 = statement.executeQuery(q2);
						if(resultSet2.next())
						capacity2 = resultSet2.getInt(1);
						System.out.println("capacity2 : "+capacity2);
						cStmt.setString(2, f2);
						cStmt.setDate(3, date_reg);
						cStmt.registerOutParameter(1, Types.INTEGER);
						cStmt.execute();
						NoR2 = cStmt.getInt(1);
						System.out.println("NOR2 "+NoR2);
						
						if(NoR2<capacity2)
						
						{
							System.out.println("Flight Number	Departure_city	Arrival_city	Departure_time	Arrival_time	Available Seats		Airline Id");
						
							System.out.println(f1  + "		" + dcity + "		" + c2 + "		" + a1time
								+ "		" + d1time+"		"+(capacity1 - NoR1)+"			"+aid);
							System.out.println(f2 +  "		" + c2 + "		" + acity + "		" + a2time
								+ "		" + d2time+"		"+(capacity2 - NoR2)+"			"+aid);
						break;
					}}
				}
			}
		}

		
		//connection.commit();
		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}

	public static void Cust_task8() throws IOException, SQLException, ParseException, InterruptedException {
		connection.setAutoCommit(false); //the default is true and every statement executed is considered a transaction.
	    connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		Scanner in = new Scanner(System.in);
		String tempf,f1 = null,d1,cid = null,ccnum=null;
		int rid=0,capacity=0,NoR,count=0;
		boolean flag=true;
		String fid[]=new String[4],fdate[]=new String[4];
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(date);
		String acity[]=new String [100];
		String dcity[]=new String [100];
		while (true)
		{
			System.out.println("Enter Flight Number");
			tempf = in.nextLine();
			
			if(tempf.equals("0"))
				break;
			count++;
			rf.add(tempf);
			System.out.println("Enter Flight Date (YYYYMMDD)");
			rd.add(in.nextLine());
			
			
		  }
		System.out.println("Is this a round trip journey (Y/N)");
		String rtj=in.nextLine().toLowerCase();
		CallableStatement cStmt = connection.prepareCall("{? = call getBookedTickets(?,?)}");
		String query,q1,q2,q;
		int index=0;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
		statement = connection.createStatement();
		query = "select reservation_number from reservation order by reservation_number desc";
		resultSet = statement.executeQuery(query);
		
		if(resultSet.next())
			rid = Integer.parseInt(resultSet.getString(1));
		rid=rid+1;
		System.out.println("rid  "+rid);
		q="select cid,credit_card_num from Customer order by cid desc";
		resultSet = statement.executeQuery(q);
		if(resultSet.next())
			{ cid = resultSet.getString(1);
			  ccnum = resultSet.getString(2);
			
			}
		while(!rf.isEmpty() && !rd.isEmpty()){
			f1=rf.poll();
			d1=rd.poll();
			
			
			java.sql.Date date_reg = new java.sql.Date(df.parse(d1).getTime());
			//java.sql.Date dater= new java.sql.Date(df.DATE_FIELD);
			//System.out.println(dater);
			q1= "select plane_capacity,departure_city,arival_city from ((select airline_id , plane_type ,departure_city,arival_city from Flight where flight_number='"+f1+"') natural join Plane)";
			resultSet1 = statement.executeQuery(q1);
			if(resultSet1.next())
				{
				capacity = resultSet1.getInt(1);
				dcity[index]=resultSet1.getString(2);
				acity[index]=resultSet1.getString(3);
				
				}
			cStmt.setString(2, f1);
			cStmt.setDate(3, date_reg);
			cStmt.registerOutParameter(1, Types.INTEGER);
			cStmt.execute();
			NoR = cStmt.getInt(1);
			if(NoR<capacity){
				fid[index]=f1;
				fdate[index]=d1;
				index++;
			}else{
				flag=false;
				System.out.println("The flight "+f1+ " has no seat available.");
				break;
			}
		
		
		}
		q = "insert into Reservation values (?,?,?,?,?,?,?,?)";
		prepStatement = connection.prepareStatement(q);
		
		
		q1 = "insert into Reservation_detail values (?,?,?,?)";
		
		if (flag){
			for(int i=0;i<index;i++)
			{ 
				Thread.sleep(7000);
				System.out.println("in flag" +rid);
				prepStatement.setString(1, rid+"");
				prepStatement.setString(2, cid);
				prepStatement.setInt(3, 200);
				prepStatement.setString(4, ccnum);
				prepStatement.setString(5, dcity[i]);
				prepStatement.setString(6, acity[i]);
				prepStatement.setDate(7, date);
				prepStatement.setString(8, "N");
				prepStatement.executeUpdate();
				System.out.println("rtj is "+rtj+" count "+count);
				Thread.sleep(7000);
				prepStatement2 = connection.prepareStatement(q1);
				if (rtj.equals("n") && count ==2)
				{
					
					
					System.out.println(" num 1");
					
					java.sql.Date date_reg = new java.sql.Date(df.parse(fdate[i]).getTime());
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 0);
					prepStatement2.executeUpdate();
					System.out.println(" num 2");
					Thread.sleep(7000);
					 date_reg = new java.sql.Date(df.parse(fdate[i+1]).getTime());
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i+1]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 1);
					prepStatement2.executeUpdate();
					System.out.println("Reservation Successful for :");
					System.out.println("Reservation Number		Flight ID		Flight Date		leg");
					for (int j=0;j<index;j++){
						System.out.println(rid+"				"+fid[j]+"		"+fdate.toString()+"		"+j);
					break;
				}
				}
			
				Date date_reg;
				if (rtj.equals("y") && count ==2)
				{
					date_reg = new java.sql.Date(df.parse(fdate[i]).getTime());
					System.out.println(rid+" "+fid[i]+" "+date_reg);
					Thread.sleep(5000);
					
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 0);
					prepStatement2.executeUpdate();
					if(i==2)
					{
						System.out.println("Reservation Successful for :");
						System.out.println("Reservation Number		Flight ID		Flight Date		leg");
						for (int k=0;i<index;k++){
							System.out.println(rid+i-1+"				"+fid[i]+"		"+date_reg+"		"+1);
						}
						break;
					}
						
					
					rid++;
					
				}
				if(count==1){
					System.out.println("in count 1");
					Thread.sleep(5000);
					date_reg = new java.sql.Date(df.parse(fdate[i]).getTime());
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 0);
					prepStatement2.executeUpdate();
					System.out.println("Reservation Successful for :");
					System.out.println("Reservation Number		Flight ID		Flight Date		leg");
					
						System.out.println(rid+"				"+fid[i]+"		"+date_reg+"		"+1);
					
					break;
				}
				if(count==4){
					System.out.println(" num 122");
					
				    date_reg = new java.sql.Date(df.parse(fdate[i]).getTime());
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 0);
					prepStatement2.executeUpdate();
					System.out.println(" num 2222");
					Thread.sleep(7000);
					 date_reg = new java.sql.Date(df.parse(fdate[i+1]).getTime());
					prepStatement2.setString(1, rid+"");
					prepStatement2.setString(2, fid[i+1]);
					prepStatement2.setDate(3, date_reg);
					prepStatement2.setInt(4, 1);
					prepStatement2.executeUpdate();
					if(i+1==4){
						System.out.println("Reservation Successful for :");
						System.out.println("Reservation Number		Flight ID		Flight Date		leg");
						for (int j=0;j<index;j++){
							System.out.println(rid+j-3+"				"+fid[j]+"		"+fdate.toString()+"		"+j%2);
						break;
					}
						
					}
					rid++;
					i=i+1;
					
				}
					
				}
				
			
		}
		
			connection.commit();
			/*if(flag){
			System.out.println("Reservation Successful for :");
			System.out.println("Reservation Number		Flight ID		Flight Date		leg");
			for (int i=0;i<index;i++){
				System.out.println(rid+"				"+fid[i]+"		"+fdate.toString()+"		"+i);
			}
			}*/
	}

	public static void Cust_task9() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		int h1 = 0, l1 = 0, h2 = 0, l2 = 0;
		int counter = 0;
		System.out.println("Enter Reservation Number");
		String rn = in.nextLine();

		statement = connection.createStatement(); // create an instance
		String selectQuery = "select Flight_number,Flight_date from Reservation_detail where reservation_number = '"
				+ rn + "'";
		// 2
		// System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		while (resultSet.next()) {
			System.out.println("Flight Number	Flight_date");

			System.out.println(resultSet.getString(1) + "		" +

					resultSet.getString(2));
			counter++;

		}
		connection.commit();

		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}

	public static void Cust_task5() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		String temp[];
		String q1,q2, sched1 = null, sched2 = null,f1=null,f2=null,c2=null;
		int counter = 0;
		int a1time = 0, d1time = 0, a2time = 0, d2time = 0;
		String aid1 = "", aid2 = "";
		String aid = null;
		System.out.println("Enter Departure_city");
		String dcity = in.nextLine();
		System.out.println("Enter Arrival_City");
		String acity = in.nextLine();
		System.out.println("Enter Name of the Airline");
		String aname = in.nextLine();
		//ResultSet resultSet1,resultSet2,resultSet3;
		System.out.println();
		statement = connection.createStatement();
		String q = "select airline_id from Airline where airline_name='" + aname + "'";
		resultSet = statement.executeQuery(q);
		if (resultSet.next())
			aid = resultSet.getString(1);
		// System.out.println("aid is "+aid);
		String selectQuery = "select * from Flight where departure_city = '" + dcity + "' and Arival_city = '" + acity
				+ "' and airline_id='" + aid + "'"; // sample query
		// 2
		// System.out.println(selectQuery);
		resultSet = statement.executeQuery(selectQuery);
		while (resultSet.next()) {
			System.out.println("Direct Trip from " + dcity + " To " + acity + " :");

			System.out.println(
					"	Flight Number	Airline_Id	Departure_city	Arrival_city	Departure_time	Arrival_time	");
			System.out.println("Record " + counter + ": " + resultSet.getString(1) + "		" + resultSet.getString(2)
					+ "		" + resultSet.getString(4) + "		" + resultSet.getString(5) + "		"
					+ resultSet.getString(6) + "		" + resultSet.getString(7));
			counter++;

		}
		//----------------with legs-------------------------------------
		String Query = "select * from Flight_connection where city1 = '" + dcity + "' and city3 = '" + acity + "'"; // sample
																													// query
		// 2
		System.out.println(Query);
		 resultSet1 = statement.executeQuery(Query);
		while (resultSet1.next()) {

			ff1.add(resultSet1.getString(1));
			ff2.add(resultSet1.getString(2));
			cc2.add(resultSet1.getString(4));
			//System.out.println(f1 + " " + f2 + " " + c2);
		}
		
		while (!ff1.isEmpty() && !ff2.isEmpty() && !cc2.isEmpty())
			{
			
			f1=ff1.poll();
			f2=ff2.poll();
			c2=cc2.poll();
			q1 = "select Arrival_time,departure_time , Weekly_schedule,airline_id from flight where flight_number='"
			
					+ f1 + "'";
			 resultSet2 = statement.executeQuery(q1);
			if (resultSet2.next()) {
				// System.out.println(resultSet.getString(1)+" $ "
				// +resultSet.getString(2)+" $ "+resultSet.getString(3));
				a1time = Integer.parseInt(resultSet2.getString(1));
				d1time = Integer.parseInt(resultSet2.getString(2));
				sched1 = resultSet2.getString(3);
				aid1 = resultSet2.getString(4);
				System.out.println(a1time + " " + d1time + " " + sched1 + " " + aid1);
			}
			q2 = "select Arrival_time, departure_time , Weekly_schedule,airline_id from flight where flight_number='"
					+ f2 + "'";
			resultSet3 = statement.executeQuery(q2);
			if (resultSet3.next()) {
				a2time = Integer.parseInt(resultSet3.getString(1));
				d2time = Integer.parseInt(resultSet3.getString(2));
				sched2 = resultSet3.getString(3);
				aid2 = resultSet3.getString(4);
				System.out.println(a2time + " " + d2time + " " + sched2 + " " + aid2);
			}
			char[] a = sched1.toLowerCase().toCharArray();
			char[] b = sched2.toLowerCase().toCharArray();
			for (int i = 0; i < 7; i++) {
				System.out.println("a1time "+a1time+" d2time "+d2time+" diff is "+(d2time-a1time));
				if (a[i] == b[i] && a[i] != '-' && aid.equals(aid1) && aid.equals(aid2) && d2time-a1time>100){ 
					System.out.println(f1 + "		" + aid + "		" + dcity + "		" + c2 + "		" + a1time
							+ "		" + d1time);
					System.out.println(f2 + "		" + aid + "		" + c2 + "		" + acity + "		" + a2time
							+ "		" + d2time);
					break;
				}
			}
			}
		connection.commit();

		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}

	

	public static void Cust_task10() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter Reservation Number ");
		String rn = in.nextLine();
		try {
			connection.setAutoCommit(false); // the default is true and every
												// statement executed is
												// considered a transaction.
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement = connection.createStatement();
			query = "update Reservation set Ticketed = 'Y' where Reservation_number ='" + rn + "'";
			int result = statement.executeUpdate(query);
			connection.commit();
			System.out.println("Ticket Purchase Successful!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (statement != null)
				statement.close();
			if (prepStatement != null)
				prepStatement.close();
		} catch (SQLException e) {
			System.out.println("Cannot close Statement. Machine error: " + e.toString());
		}
	}
	
	
	
	

	public static void main(String args[]) throws IOException, SQLException {

		// ConnectDB();
		String username, password;
		username = "mcs116"; // This is your username in oracle
		password = "4055543"; // This is your password in oracle
		

		try {
			// Register the oracle driver.
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			// This is the location of the database. This is the database in
			// oracle
			// provided to the class
			String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";
			System.out.println("=========================");
			// create a connection to DB on class3.cs.pitt.edu
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("--------------");
			Display();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Example0();
		connection.close();
	}

}
