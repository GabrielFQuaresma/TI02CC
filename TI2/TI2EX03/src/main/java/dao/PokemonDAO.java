package dao;

import model.Pokemon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PokemonDAO extends DAO {	
	public PokemonDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Pokemon pokemon) {
		boolean status = false;
		try {
			String sql = "INSERT INTO pokemon(codPokedex, nome, tipo, hp, attack, speed) "
				       + "VALUES("+ pokemon.getCodPokedex() + ", '" + pokemon.getNome() + "', '"  
				       + pokemon.getTipo() + "', " + pokemon.getHp() + ", " + pokemon.getAttack() + ", " + pokemon.getSpeed() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Pokemon get(int codPokedex) {
		Pokemon Pokemon = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM pokemon WHERE codokedex="+codPokedex;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 Pokemon = new Pokemon(rs.getInt("codPokedex"), rs.getString("nome"), 
		                  rs.getString("tipo"), rs.getInt("hp"), rs.getInt("attack"), rs.getInt("speed"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Pokemon;
	}
	
	
	public List<Pokemon> get() {
		return get("");
	}

	
	public List<Pokemon> getOrderByCodPokedex() {
		return get("codPokedex");		
	}
	
	
	public List<Pokemon> getOrderByHp() {
		return get("hp");		
	}
	
	
	public List<Pokemon> getOrderByAttack() {
		return get("attack");		
	}
	
	public List<Pokemon> getOrderBySpeed() {
		return get("speed");		
	}
	
	private List<Pokemon> get(String orderBy) {
		List<Pokemon> pokemons = new ArrayList<Pokemon>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM pokemon" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Pokemon p = new Pokemon(rs.getInt("codPokedex"), rs.getString("nome"), 
		                  rs.getString("tipo"), rs.getInt("hp"), rs.getInt("attack"), rs.getInt("speed"));
	            pokemons.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pokemons;
	}
	
	
	public boolean update(Pokemon pokemon) {
		boolean status = false;
		try {  
			String sql = "UPDATE pokemon SET nome = '" + pokemon.getNome() + "', tipo = '"  
				       + pokemon.getTipo() + "', hp = '" + pokemon.getHp()  +  "', attack = '" + pokemon.getAttack()  + "', speed = '" + pokemon.getSpeed()  + "'"
					   + " WHERE codPokedex = " + pokemon.getCodPokedex();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int codPokedex) {
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
}