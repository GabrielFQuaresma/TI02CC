/*
@author: Gabriel Felipe Quaresma de Oliveira
date: 24/08/2023

feito com partes de código disponibilizado por Max do Val Machado no Github
https://github.com/icei-pucminas/ti2cc/tree/master/fonte-antigo/bancodedados/01ConexaoPostgres/src/main/java/com/ti2cc
*/

package ti2.ex02;

import java.io.*;
import java.nio.charset.*;

class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static void print(){
   }

   public static void print(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void print(float x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void print(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(){
   }

   public static void println(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(float x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void println(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void printf(String formato, double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static double readDouble(){
      double d = -1;
      try{
         d = Double.parseDouble(readString().trim().replace(",","."));
      }catch(Exception e){}
      return d;
   }

   public static double readDouble(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readDouble();
   }

   public static float readFloat(){
      return (float) readDouble();
   }

   public static float readFloat(String str){
      return (float) readDouble(str);
   }

   public static int readInt(){
      int i = -1;
      try{
         i = Integer.parseInt(readString().trim());
      }catch(Exception e){}
      return i;
   }

   public static int readInt(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readInt();
   }

   public static String readString(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n' && tmp != ' ');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readString(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readString();
   }

   public static String readLine(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readLine(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readLine();
   }

   public static char readChar(){
      char resp = ' ';
      try{
         resp  = (char)in.read();
      }catch(Exception e){}
      return resp;
   }

   public static char readChar(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readChar();
   }

   public static boolean readBoolean(){
      boolean resp = false;
      String str = "";

      try{
         str = readString();
      }catch(Exception e){}

      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
         resp = true;
            }

      return resp;
   }

   public static boolean readBoolean(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readBoolean();
   }

   public static void pause(){
      try{
         in.read();
      }catch(Exception e){}
   }

   public static void pause(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      pause();
   }
}

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

        
		
		
		System.out.println("-----------------------------------1----");
		System.out.println("|               POKEMON               |");
		System.out.println("|                                     |");
		System.out.println("|                                     |");
        System.out.println("| 1- LISTAR                           |");
        System.out.println("| 2- INSERIR                          |");
        System.out.println("| 3- EXCLUIR                          |");
        System.out.println("| 4- ATUALIZAR                        |");
        System.out.println("| 5- SAIR                             |");
        System.out.println("|                                     |");
        System.out.println("|                                     |");
        System.out.println("|                                     |");
        System.out.println("---------------------------------------");

        
      //Valor que regira a escolha do usúario
      	int choice = 0;
        
        
        do
        {
        	choice = MyIO.readInt("Qual deseja escolher? ");
		    switch(choice)
		    {   
                //Listagem dos pokemons
			    case 1:
			    {
                    Pokemon[] pokemon = null;
                    pokemon = dao.getPokemon();


                    if(pokemon != null)
                    {
                    	int length = pokemon.length;
                        for(int i = 0; i < length; i++)
                        {
                            System.out.println(pokemon[i].toString());
                        }
                    }
                    else
                    {
                        System.out.println("Não há nenhum pokemon adicionado.");
                    }
				    break;
			    }
                //Inserção de pokemons
                case 2:
                {
                    int codPokedex = MyIO.readInt("Código Pokedex do pokemon: ");
                    String nome = MyIO.readString("Nome do pokemon: ");
                    String tipo = MyIO.readString("Tipo do pokemon: ");
                    int hp = MyIO.readInt("HP do pokemon: ");
                    int attack = MyIO.readInt("Ataque do pokemon: ");
                    int speed = MyIO.readInt("Velocidade do pokemon: ");

                    Pokemon pokemon = new Pokemon(codPokedex, nome, tipo, hp, attack, speed);

                    if (dao.inserirPokemon(pokemon) == true)
                    {
                        System.out.println("Inserção com sucesso -> " + pokemon.toString());
                    }
                    else
                    {
                    	System.out.println("Pokemon não existe");
                    }
                    break;
                }
                //Exclusão de um pokemon
                case 3:
                {
                    int codPokedex = MyIO.readInt("Código Pokedex do pokemon que deseja excluir: ");

                    if (dao.excluirPokemon(codPokedex))
                    {
                        System.out.println("Exclusão com sucesso -> " + codPokedex);
                    }
                    else
                    {
                    	System.out.println("Não foi possível excluir");
                    }
                    break;
                }
                //Atualizar um pokemon
                case 4:
                {
                    int codPokedex = MyIO.readInt("Código Pokedex do pokemon: ");
                    String nome = MyIO.readLine("Nome do pokemon: ");
                    String tipo = MyIO.readString("Tipo do pokemon: ");
                    int hp = MyIO.readInt("HP do pokemon: ");
                    int attack = MyIO.readInt("Ataque do pokemon: ");
                    int speed = MyIO.readInt("Velocidade do pokemon: ");

                    Pokemon pokemon = new Pokemon(codPokedex, nome, tipo, hp, attack, speed);

                    if (dao.atualizarPokemon(pokemon) == true)
                    {
                        System.out.println("Atualização com sucesso -> " + pokemon.toString());
                    }
                    break;
                }
                //Terminar o programa
                case 5:
                {
                    System.out.println("Encerrando...");
                    dao.close();
                    break;
                }
                default:
                    System.out.println("Opção não faz parte da lista de ações...");
                    break;   
		    }
        }while(choice != 5);
	}
}