/*
@author: Gabriel Felipe Quaresma de Oliveira
date: 24/08/2023

feito com partes de código disponibilizado por Max do Val Machado no Github
https://github.com/icei-pucminas/ti2cc/tree/master/fonte-antigo/bancodedados/01ConexaoPostgres/src/main/java/com/ti2cc
*/


package ti2.ex02;

public class Pokemon {
	private int codPokedex;
	private String nome;
	private String tipo;
	private int hp;
	private int attack;
	private int speed;
	
	public Pokemon() {
		this.codPokedex = -1;
		this.nome = "";
		this.tipo = "";
		this.hp = -1;
		this.attack = -1;
		this.speed = -1;
	}
	
	public Pokemon (int codPokedex, String nome, String tipo, int hp, int attack, int speed) {
		this.codPokedex = codPokedex;
		this.nome = nome;
		this.tipo = tipo;
		this.hp = hp;
		this.attack = attack;
		this.speed = speed;
	}

	public int getCodPokedex() {
		return codPokedex;
	}

	public void setCodPokedex(int codPokedex) {
		this.codPokedex = codPokedex;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

    public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

    public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


	@Override
	public String toString() {
		return "Pokemon [codPokedex=" + codPokedex + ", nome=" + nome + ", tipo=" + tipo + ", hp=" + hp +  ", attack=" + attack + ", speed=" + speed + "]";
	}	
}
