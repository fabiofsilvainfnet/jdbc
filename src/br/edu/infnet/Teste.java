package br.edu.infnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Teste {

	
	public static void main(String[] args) {
		
		
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar driver do banco de dados");
			e.printStackTrace();
		}
		
		Connection conexao = null;
		
		
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
		//exemplo de url de conexão com mysql passando o timezone: useLegacyDatetimeCode=false&serverTimezone=America/New_York"
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "root");
		} catch (SQLException e) {
			System.out.println("Erro de conexão com o banco de dados");
			e.printStackTrace();
		} 
		
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));

		Statement comando = null;
		try {
			comando = conexao.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));

		
		String sql = "select * from estados";

		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));

		try {
			ResultSet resultado = comando.executeQuery(sql);
			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				String nome = resultado.getString("nome");
				
				System.out.println("Id: " + id + " Nome: " + nome);
			}
			
			resultado.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(DateFormat.getDateTimeInstance().format(new Date()));


		String sql2 = "select * from estados where id = ?";
		PreparedStatement comando2 = null;
		try {
			comando2 = conexao.prepareStatement(sql2);
			comando2.setInt(1, 3);
			
			ResultSet resultado = comando2.executeQuery();

			while (resultado.next()) {
				Integer id = resultado.getInt("id");
				String nome = resultado.getString("nome");
				
				System.out.println("Id: " + id + " Nome: " + nome);
			}
			
			resultado.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			comando.close();
			comando2.close();
			conexao.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
}
