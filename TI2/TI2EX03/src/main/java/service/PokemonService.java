package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.PokemonDAO;
import model.Pokemon;
import spark.Request;
import spark.Response;


public class PokemonService {

	private PokemonDAO pokemonDAO = new PokemonDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CODPOKEDEX = 1;
	private final int FORM_ORDERBY_HP = 2;
	private final int FORM_ORDERBY_ATTACK = 3;
	
	
	public PokemonService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Pokemon(), FORM_ORDERBY_CODPOKEDEX);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Pokemon(), orderBy);
	}

	
	public void makeForm(int tipo, Pokemon pokemon, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPokemon = "";
		if(tipo != FORM_INSERT) {
			umPokemon += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/Pokemon/list/1\">Novo Pokemon</a></b></font></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t</table>";
			umPokemon += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/Pokemon/";
			String name, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Pokemon";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + pokemon.getCodPokedex();
				name = "Atualizar Pokemon (codPokedex " + pokemon.getCodPokedex() + ")";
				buttonLabel = "Atualizar";
			}
			umPokemon += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umPokemon += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
            umPokemon += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"Nome\" value=\""+ pokemon.getNome() + "\"></td>";
            umPokemon += "\t\t\t<td>codPokedex: <input class=\"input--register\" type=\"number\" name=\"codPokedex\" value=\""+ pokemon.getCodPokedex() + "\"></td>";
			umPokemon += "\t\t\t<td>Attack: <input class=\"input--register\" type=\"number\" name=\"Attack\" value=\""+ pokemon.getAttack() +"\"></td>";
			umPokemon += "\t\t\t<td>Hp: <input class=\"input--register\" type=\"number\" name=\"Hp\" value=\""+ pokemon.getHp() +"\"></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td>&nbsp;Speed: <input class=\"input--register\" type=\"number\" name=\"Speed\" value=\""+ pokemon.getSpeed() + "\"></td>";
			umPokemon += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t</table>";
			umPokemon += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umPokemon += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Pokemon (ID " + pokemon.getCodPokedex() + ")</b></font></td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
            umPokemon += "\t\t\t<td>Nome: "+ pokemon.getNome() + "</td>";
            umPokemon += "\t\t\t<td>codPokedex: "+ pokemon.getCodPokedex() + "</td>";
			umPokemon += "\t\t\t<td>Attack: "+ pokemon.getAttack() +"</td>";
			umPokemon += "\t\t\t<td>Hp: "+ pokemon.getHp() +"</td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t\t<tr>";
			umPokemon += "\t\t\t<td>&nbsp;Speed: "+ pokemon.getSpeed() + "</td>";
			umPokemon += "\t\t\t<td>&nbsp;</td>";
			umPokemon += "\t\t</tr>";
			umPokemon += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Pokemon>", umPokemon);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Pokemons</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/Pokemon/list/" + FORM_ORDERBY_CODPOKEDEX + "\"><b>codPokedex</b></a></td>\n" +
        		"\t<td><a href=\"/Pokemon/list/" + FORM_ORDERBY_ATTACK + "\"><b>Hp</b></a></td>\n" +
        		"\t<td><a href=\"/Pokemon/list/" + FORM_ORDERBY_HP + "\"><b>Attack</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List <Pokemon> Pokemons;
		if (orderBy == FORM_ORDERBY_CODPOKEDEX) {           Pokemons = pokemonDAO.getOrderByCodPokedex();
		} else if (orderBy == FORM_ORDERBY_ATTACK) {		Pokemons = pokemonDAO.getOrderByAttack();
		} else if (orderBy == FORM_ORDERBY_HP) {			Pokemons = pokemonDAO.getOrderByHp();
		} else {											Pokemons = pokemonDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Pokemon p : Pokemons) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCodPokedex() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getAttack() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Pokemon/" + p.getCodPokedex() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Pokemon/update/" + p.getCodPokedex() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeletePokemon('" + p.getCodPokedex() + "', '" + p.getNome() + "', '" + p.getAttack() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-Pokemon>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
        int codPokedex = Integer.parseInt(request.queryParams("codPokedex"));
		String Nome = request.queryParams("Nome");
        String Tipo = request.queryParams("Tipo");
		int attack = Integer.parseInt(request.queryParams("Attack"));
		int speed = Integer.parseInt(request.queryParams("Speed"));
        int hp = Integer.parseInt(request.queryParams("Hp"));
		
		String resp = "";
		
		Pokemon pokemon = new Pokemon(codPokedex, Nome, Tipo, attack, speed, hp);
		
		if(pokemonDAO.insert(pokemon) == true) {
            resp = "pokemon (" + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "pokemon (" + ") não inserido!";
			response.status(404); // 404 Not found
		}
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":codpokedex"));		
		Pokemon pokemon = (Pokemon) pokemonDAO.get(id);
		
		if (pokemon != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, pokemon, FORM_ORDERBY_CODPOKEDEX);
        } else {
            response.status(404); // 404 Not found
            String resp = "Pokemon " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id = Integer.parseInt(request.params(":codokedex"));		
		Pokemon pokemon = (Pokemon) pokemonDAO.get(id);
		
		if (pokemon != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, pokemon, FORM_ORDERBY_CODPOKEDEX);
        } else {
            response.status(404); // 404 Not found
            String resp = "Pokemon " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":codpokedex"));
		Pokemon pokemon = pokemonDAO.get(id);
        String resp = "";       

        if (pokemon != null) {
        	pokemon.setNome(request.queryParams("Nome"));
            pokemon.setAttack(Integer.parseInt(request.queryParams("Attack")));
        	pokemon.setHp(Integer.parseInt(request.queryParams("Hp")));
        	pokemon.setSpeed(Integer.parseInt(request.queryParams("Speed")));
        	pokemonDAO.update(pokemon);
        	response.status(200); // success
            resp = "Pokemon (codPokedex " + pokemon.getCodPokedex() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Pokemon (codPokedex \"" + pokemon.getCodPokedex() + "\") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":codpokedex"));
        Pokemon pokemon = pokemonDAO.get(id);
        String resp = "";       

        if (pokemon != null) {
            pokemonDAO.delete(id);
            response.status(200); // success
            resp = "Pokemon (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Pokemon (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}