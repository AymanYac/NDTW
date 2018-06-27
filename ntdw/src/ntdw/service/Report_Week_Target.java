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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import ntdw.model.Article;
import ntdw.model.Target;

public class Report_Week_Target {

	private static Report_Week_Target INSTANCE = new Report_Week_Target();
	private List<Target> targets = new ArrayList<>();
	
	private Report_Week_Target()
    {}
	
	public static Report_Week_Target getInstance()
    {   return INSTANCE;
    }
	public List<Target> getArticles(String login) throws ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, SQLException{
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://"+Tools.load_ip()+":5432/northwind";
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","Neonec");
		props.setProperty("loginTimeout", "20");
		props.setProperty("connectTimeout", "0");
		props.setProperty("socketTimeout", "0");
		//props.setProperty("ssl","true");
		Connection conn = DriverManager.getConnection(url, props);
		PreparedStatement st = null;
		if(login.equals("Elise") || login.equals("Corinne")) {
			st = conn.prepareStatement("select * from items");
		}else {
			st = conn.prepareStatement("select * from items where classifier = ?");
			st.setString(1, login);
		}

		ResultSet rs = st.executeQuery();
		HashMap<String,HashMap<Integer,HashMap<String,Integer>>> tmp = new HashMap<String,HashMap<Integer,HashMap<String,Integer>>> ();
		while(rs.next()) {
			String user=rs.getString("classifier");
			Integer week=rs.getInt("target_week");
			String status=rs.getString("status");
			if(tmp.containsKey(user)) {
				if(tmp.get(user).containsKey(week)) {
					if(status.contains("COMPLET")) {
						status="COMPLET";
					}else {
						status="PENDING";
					}
					if(tmp.get(user).get(week).containsKey(status)) {
						Integer count = tmp.get(user).get(week).get(status);
						count = count+1;
						HashMap tmp_status = new HashMap<String,Integer>();
						tmp_status.put(status, count);
						HashMap tmp_week = new HashMap<Integer,HashMap<String,Integer>>();
						tmp_week.put(week, tmp_status);
						tmp.put(user, tmp_week);
						
					}else {
						Integer count = 1;
						HashMap tmp_status = new HashMap<String,Integer>();
						tmp_status.put(status, count);
						HashMap tmp_week = new HashMap<Integer,HashMap<String,Integer>>();
						tmp_week.put(week, tmp_status);
						tmp.put(user, tmp_week);
					}
				}else {
					if(status.contains("COMPLET")) {
						status="COMPLET";
					}else {
						status="PENDING";
					}
					Integer count = 1;
					HashMap tmp_status = new HashMap<String,Integer>();
					tmp_status.put(status, count);
					HashMap tmp_week = new HashMap<Integer,HashMap<String,Integer>>();
					tmp_week.put(week, tmp_status);
					tmp.put(user, tmp_week);
					
					
				}
			}else {
				if(status.contains("COMPLET")) {
					status="COMPLET";
				}else {
					status="PENDING";
				}
				Integer count = 1;
				HashMap tmp_status = new HashMap<String,Integer>();
				tmp_status.put(status, count);
				HashMap tmp_week = new HashMap<Integer,HashMap<String,Integer>>();
				tmp_week.put(week, tmp_status);
				tmp.put(user, tmp_week);
			}
			
			
		}
		
		
		
		
		
		return targets;
		
	}
	
}
