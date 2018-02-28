package com.tibd.aula01.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
	public static Connection connection = null;
	public static PreparedStatement statament;

	public static void main(String[] args) throws SQLException {

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/aula", "root", "teste@2017AS");
			/*
			 * String sql =
			 * "create table aluno(id int not null auto_increment,nome varchar(255),"
			 * + "matricula varchar(255),primary key (id));";
			 */
			// statament = connection.prepareStatement(sql);
			
			// System.out.println("Conexão Estabelecida!");
			
			//inserirAluno("Rafaela", "432");
			imprimirAlunos();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e);
		} finally {
			connection.close();
		}

	}

	public static void inserirAluno(String nome, String matricula) throws SQLException {
		try {
			if (connection != null) {
				String sql = "insert into aluno(nome,matricula) values (?,?)";
				statament = connection.prepareStatement(sql);
				statament.setString(1, nome);
				statament.setString(2, matricula);
				statament.execute();
				statament.close();

			} else {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/aula", "root", "teste@2017AS");
				String sql = "insert into aluno(nome,matricula) values (?,?)";
				statament = connection.prepareStatement(sql);
				statament.setString(1, nome);
				statament.setString(2, matricula);
				statament.execute();
				statament.close();
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e);
		}

	}
	
	public static void imprimirAlunos() throws SQLException{
		try {
			if (connection != null) {
				String sql = "select * from aluno;";
				statament = connection.prepareStatement(sql);
				ResultSet resultSet = statament.executeQuery();
				
				while (resultSet.next()) {
					System.out.print("Nome: " + resultSet.getString("nome")+"\t"
							+ "" + "Matricula: " + resultSet.getString("matricula"));
					System.out.println();
				}

			}else{
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/aula", "root", "teste@2017AS");
				
				String sql = "select * from aluno;";
				statament = connection.prepareStatement(sql);
				ResultSet resultSet = statament.executeQuery();
				
				while (resultSet.next()) {
					System.out.print("Nome: " + resultSet.getString("nome")+"\t"
							+ "" + "Matricula: " + resultSet.getString("matricula"));
					System.out.println();
				}
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e);
		}finally{
			statament.close();
			connection.close();
		}
		
	}

}
