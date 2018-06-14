package ntdw.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.stream.IntStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Tools {

	public static String load_ip() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException {
		
		
		/*
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://"+"88.190.148.154"+":5432/northwind";
			Properties props = new Properties();
			props.setProperty("user","postgres");
			props.setProperty("password","Neonec");

			props.setProperty("loginTimeout", "20");
			props.setProperty("connectTimeout", "0");
			props.setProperty("socketTimeout", "0");
			//props.setProperty("ssl","true");
			Connection conn0 = DriverManager.getConnection(url, props);
			PreparedStatement prepStmt = conn0.prepareStatement("INSERT INTO public.timetest (ix, time) VALUES (?, ?)");
			
			
			
		    
			
			IntStream.range(1, 10).forEach(ix -> {
				try {
					prepStmt.setInt(1, ix);
					prepStmt.setTimestamp(2, maintenant(clock));
					prepStmt.addBatch();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			prepStmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		*/
		
		
		return "88.190.148.154";
		/*
		 * String ret=null;
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());    
		
		byte[] keyBytes = "JF#);&wnCL=j%5hP".getBytes();
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
	    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
	    
		//byte[] input = "172.16.25.8".getBytes(); maurice_local
		//byte[] input = "41.76.40.18".getBytes(); maurice_remote
	    //byte[] input = "88.190.148.154".getBytes(); paris
	    byte[] input = "88.190.148.154".getBytes();
		// encryption pass
	    
	    cipher.init(Cipher.ENCRYPT_MODE, key);

	    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
	    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
	    ctLength += cipher.doFinal(cipherText, ctLength);
	    		
	    File file = new File("./cypher_ip");
	    FileOutputStream fos = new FileOutputStream(file);
	    		fos.write(cipherText, 0, cipherText.length);
	    		fos.flush();
	    		fos.close();
	   	File file2 = new File("./cypher_l");
	    FileOutputStream fos2 = new FileOutputStream(file2);
	    		BigInteger bigInt = BigInteger.valueOf(ctLength);
	    		fos2.write(bigInt.toByteArray(), 0,  bigInt.toByteArray().length);
	    		fos2.flush();
	    		fos2.close();
	   
	    // decryption pass
	    		Path path = Paths.get("./cypher_ip");
	    		cipherText = Files.readAllBytes(path);
	    		path=Paths.get("./cypher_l");
	    		byte[] ctLengthBytes = Files.readAllBytes(path);
	    		BigInteger bi = new BigInteger(ctLengthBytes);
	    
	    ctLength = bi.intValue();
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
	    int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
	    ptLength += cipher.doFinal(plainText, ptLength);
	    ret=(new String(plainText));
		return ret;
		 * 
		 * 
		 * 
		 */
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

	public static Boolean checkpass(String ip,String login,String pass) throws SQLException, ClassNotFoundException {
		Boolean ret = false;
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://"+ip+":5432/northwind";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");
		
		//props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		PreparedStatement st = conn.prepareStatement("select * from public.\"auth\" WHERE \"login\" = ?");
		st.setString(1, login);
		ResultSet rs = st.executeQuery();
		if (rs.next())
		{
		    ret = pass.equals(rs.getString(2));
		}
		rs.close();
		st.close();
		conn.close();
		return ret;
	}

}
