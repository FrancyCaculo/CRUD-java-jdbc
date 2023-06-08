package Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement bg = null;
		
		try {
			// INICIALIZAR A CONEXÃO
			conn = DB.getConnection();
			
			// DÊ CONSULTA BD NESTA LINHA ABAIXO
			
			// DADOS DA TABELA SELLER
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM seller");
			while(rs.next()) {
				System.out.println("Id: " + rs.getInt("Id") + " | "+"Name: " + rs.getString("Name") + " | "+ "Email: " + rs.getString("Email") + " | "+ "BirthDate: " + rs.getString("BirthDate") + " | "+ "BaseSalary: " + rs.getDouble("BaseSalary") + " | " + rs.getString("DepartmentId"));																											
			}
			
			//String query = "INSERT INTO seller VALUES (null,'Wilson','wilsom@gmail.com','2005/03/18','10000.00','4')".toUpperCase();
			
			String query = "DELETE FROM seller WHERE seller.Id = 15";
			
			//String query = "UPDATE seller SET Name = 'Murilo Ximenes' WHERE seller.Id = 14";
			
			
			
			// INICIALIZAR A INSTRUÇÃO
			bg = conn.prepareStatement(query);

			// LANÇA CONSULTA NO BD E SALVA AS LINHAS AFETADAS NA CONSULTA
			int rows = bg.executeUpdate();
			

			// VERIFIQUE SE ALGUMA LINHA FOI AFETADA E RETORNE UMA STRING
			String result = rows > 0 ? rows + " rows affecteds!" : "\n\n No rows affecteds!";
			System.out.println(result);	
		}

		catch (SQLException e) {
			e.getMessage();
		}

		finally {
			// FECHAR AS CONEXÕES
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
