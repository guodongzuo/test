package com.jisheng.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataUtil {
	private static final Properties pps = new Properties();
	static{
		 try {
			pps.load(DataUtil.class.getClassLoader().getResourceAsStream("DB_URL.properties"));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static final String CONN_URL_CRM = pps.getProperty("CONN_URL_CRM");
	public static final String CONN_URL_JCT = pps.getProperty("CONN_URL_JCT");
	
	private static Connection getConnection(String url){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		}catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean exec(String sql,String url){
		Connection conn = null;
		Statement st = null;
		try{
			conn = DataUtil.getConnection(url);
			st = conn.createStatement();
			st.execute(sql);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}finally{
			try{
				if(st!=null){
					st.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static Map<String,String> qry(String sql,String url){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Map<String,String> map = new HashMap<String,String>();
		try{
			conn = DataUtil.getConnection(url);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();  
			int count = rsmd.getColumnCount(); 
			while(rs.next()){
				for (int i = 1; i <= count; i++) {  
					String key = rsmd.getColumnName(i);  
				    String value = rs.getString(i);  
			        map.put(key, value);  
				}  
			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
				if(st!=null){
					st.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]){
		System.out.println(DataUtil.getConnection(DataUtil.CONN_URL_CRM));
		System.out.println(DataUtil.getConnection(DataUtil.CONN_URL_JCT));
	}
}
