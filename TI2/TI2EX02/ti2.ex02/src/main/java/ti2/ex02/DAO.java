/*
@author: Gabriel Felipe Quaresma de Oliveira
date: 24/08/2023

feito com partes de código disponibilizado por Max do Val Machado no Github
https://github.com/icei-pucminas/ti2cc/tree/master/fonte-antigo/bancodedados/01ConexaoPostgres/src/main/java/com/ti2cc
*/
package ti2.ex02;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "TI2EX02";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirPokemon(Pokemon pokemon) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO pokemon (codPokedex, nome,  tipo, hp, attack, speed) "
					       + "VALUES ("+pokemon.getCodPokedex() + ", '" + pokemon.getNome() + "', '"  
					       + pokemon.getTipo() + "', '" + pokemon.getHp() + ", '" + pokemon.getAttack() + ", '" + pokemon.getSpeed() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarPokemon(Pokemon pokemon) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE pokemon SET nome = '" + pokemon.getNome() + "', tipo = '"  
				       + pokemon.getTipo() + "', hp = '" + pokemon.getHp()  +  "', attack = '" + pokemon.getAttack()  + "', speed = '" + pokemon.getSpeed()  + "'"
					   + " WHERE codPokedex = " + pokemon.getCodPokedex();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirPokemon(int codPokedex) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM pokemon WHERE codPokedex = " + codPokedex);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Pokemon[] getPokemon() {
		Pokemon[] pokemon = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM pokemon");		
	         if(rs.next()){
	             rs.last();
	             pokemon = new Pokemon[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                pokemon[i] = new Pokemon(rs.getInt("codPokedex"), rs.getString("nome"), 
	                		                  rs.getString("tipo"), rs.getInt("hp"), rs.getInt("attack"), rs.getInt("speed"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pokemon;
	}

	
	
}