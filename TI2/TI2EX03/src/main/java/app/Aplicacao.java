package app;

import static spark.Spark.*;
import service.PokemonService;


public class Aplicacao{
	private static PokemonService pokemonService = new PokemonService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/Pokemon/insert", (request, response) -> pokemonService.insert(request, response));

        get("/Pokemon/:id", (request, response) -> pokemonService.get(request, response));
        
        get("/Pokemon/list/:orderby", (request, response) -> pokemonService.getAll(request, response));

        get("/Pokemon/update/:id", (request, response) -> pokemonService.getToUpdate(request, response));
        
        post("/Pokemon/update/:id", (request, response) -> pokemonService.update(request, response));
           
        get("/Pokemon/delete/:id", (request, response) -> pokemonService.delete(request, response));

             
    }
}