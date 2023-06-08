package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	//OBJETO DE CONEXÃO COM BD DO JDBC
	private static Connection conn = null;
	
	//MÉTODO CONEXÃO BANCO DE DADOS
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = loadProperties(); //CARREGANDO PROPRIEDADES DO BANCO DE DADOS
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props); //CONEXÃO COM O BANCO DE DADOS
			} catch(SQLException e){
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//MÉTODO FECHAR CONEXÃO BANCO DE DADOS
	public  static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//MÉTODO AUXILIAR CARREGAR DADOS DB.PROPRIEDADES
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//MÉTODO FECHAR STATEMENT
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//MÉTODO FECHAR RESULTSET
	public static void closeResultset(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
}
