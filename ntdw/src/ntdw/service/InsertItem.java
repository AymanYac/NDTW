package ntdw.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;

public class InsertItem {

	public static void main(String[] args) {





		try {

			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://"+"88.190.148.154"+":5432/"+getDabase();

			Properties props = new Properties();

			props.setProperty("user","postgres");

			props.setProperty("password","Neonec");



			props.setProperty("loginTimeout", "20");

			props.setProperty("connectTimeout", "0");

			props.setProperty("socketTimeout", "0");

			//props.setProperty("ssl","true");

			Connection conn0 = DriverManager.getConnection(url, props);

			PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.items(aid, sd, ld, po, cid, prio, target, status, timetaken, fedate, ledate, classifier, reviewer, \"classChange\", ques, ocid, visible, loadtime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");







			final Clock clock = new NanoClock().withZone(ZoneId.of("UTC"));



			List<List<String>> file = readTXTFile("C:\\Users\\Deathshadow\\Downloads\\First_Items.csv");

			for(List<String> line:file) {
				prepStmt.setString(1, line.get(0));
				prepStmt.setString(2, line.get(1));
				prepStmt.setString(3, line.get(2));
				prepStmt.setString(4, line.get(3));
				prepStmt.setString(5, line.get(4));
				prepStmt.setInt(6, Integer.parseInt(line.get(7)));
				prepStmt.setBoolean(7, false);
				prepStmt.setString(8, "NOUVEAU");
				prepStmt.setFloat(9, (float) 0.0);
				prepStmt.setDate(10, null);
				prepStmt.setDate(11, null);
				prepStmt.setString(12, line.get(6));
				prepStmt.setString(13, line.get(9));
				prepStmt.setBoolean(14, false);
				prepStmt.setString(15, null);
				prepStmt.setString(16, line.get(4));
				prepStmt.setString(17, "OUI");
				prepStmt.setTimestamp(18, maintenant(clock));
				prepStmt.addBatch();
				
				//ItemID	SD	LD	PO	ClassID	Original ClassID	prenom	Priority	Target	Binome	
				
				

			}

			prepStmt.executeBatch();


		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	private static String getDabase() {

		return "northwindA";
	}


	public static Timestamp maintenant(Clock clock) {

		final Instant instant = Instant.now(clock);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS").withZone( ZoneId.of("UTC") );

		String timePoint = formatter.format(instant);

		return java.sql.Timestamp.valueOf(timePoint);



	}

	public static Timestamp toTimestamp(ZonedDateTime dateTime){

		return new Timestamp(dateTime.toInstant().getEpochSecond() * 1000L);

	}

	private static List<List<String>> readTXTFile(String csvFileName) throws IOException {

		String line = null;
		BufferedReader stream = null;
		List<List<String>> csvData = new ArrayList<List<String>>();

		try {
			stream = new BufferedReader(new FileReader(csvFileName));
			while ((line = stream.readLine()) != null) {
				String[] splitted = line.split(";;;;");
				List<String> dataLine = new ArrayList<String>(splitted.length);
				for (String data : splitted)
					dataLine.add(data);
				csvData.add(dataLine);
			}
		} finally {
			if (stream != null)
				stream.close();
		}

		return csvData;

	}







}
