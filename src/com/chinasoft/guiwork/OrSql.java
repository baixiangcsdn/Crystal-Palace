package com.chinasoft.guiwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrSql {
	private static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:ORCL";
	private String USER="scott";
	private String PASSWORD="123456";
	private Connection con;
	private PreparedStatement ps;
	

	public void setUSER(String uSER) {
		USER = uSER;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPreparedStatement(String sql) throws Exception{
		Class.forName(DRIVER);
		con=DriverManager.getConnection(URL, USER, PASSWORD);
		ps=con.prepareStatement(sql);
	}
	
	public ResultSet DQL() throws Exception{
		ResultSet rs=ps.executeQuery();
		return rs;
	}
	public int DML() throws Exception{
		int num=ps.executeUpdate();
		return num;
	}
	public boolean DDL() throws Exception{
		boolean tf=ps.execute();
		return tf;
	}
	
	public void closeAll(ResultSet rs) throws SQLException{
		if(con!=null){
			con.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(rs!=null){
			rs.close();
		}
	}
}
