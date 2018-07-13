package ntdw.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;



public class Tools
{
	public static String load_ip()
			throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException
	{


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


















































	public static Timestamp maintenant(Clock clock)
	{
		/* 140 */     Instant instant = Instant.now(clock);
		/* 141 */     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSSSSSSSS").withZone(ZoneId.of("UTC"));
		/* 142 */     String timePoint = formatter.format(instant);
		/* 143 */     return Timestamp.valueOf(timePoint);
	}

	public static Timestamp toTimestamp(ZonedDateTime dateTime)
	{
		/* 148 */     return new Timestamp(dateTime.toInstant().getEpochSecond() * 1000L);
	}

	public static Boolean checkpass(String ip, String login, String pass) throws SQLException, ClassNotFoundException {
		/* 152 */     Boolean ret = Boolean.valueOf(false);
		/* 153 */     Class.forName("org.postgresql.Driver");
		/* 154 */     String url = "jdbc:postgresql://" + ip + ":5432/" + getDatabaseName();
		/* 155 */     Properties props = new Properties();
		/* 156 */     props.setProperty("user", "postgres");
		/* 157 */     props.setProperty("password", "Neonec");
		/* 158 */     props.setProperty("loginTimeout", "20");
		/* 159 */     props.setProperty("connectTimeout", "0");
		/* 160 */     props.setProperty("socketTimeout", "0");


		/* 163 */     Connection conn = DriverManager.getConnection(url, props);
		/* 164 */     PreparedStatement st = conn.prepareStatement("select * from public.\"auth\" WHERE \"login\" = ?");
		/* 165 */     st.setString(1, login);
		/* 166 */     ResultSet rs = st.executeQuery();
		/* 167 */     if (rs.next())
		{
			/* 169 */       ret = Boolean.valueOf(pass.equals(rs.getString(2)));
		}
		/* 171 */     rs.close();
		/* 172 */     st.close();
		/* 173 */     conn.close();
		/* 174 */     return ret;
	}

	public static String getDatabaseName() {
		/* 178 */     return "northwindA";
	}
}


/* Location:              C:\Users\Deathshadow\Desktop\Neonec_Specification_Wizard\back up\1.3.9\NTDW_V1.3.9_Paris_TEST.jar!\ntdw\service\Tools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */