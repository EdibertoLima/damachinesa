/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogototal;

/**
 *
 * @author ediberto
 */
public class Jogador {

	private char jog;

	public Jogador(char j){
		this.jog=j;
	}

	public char getJogador(){
		return jog;
	}

	public void nextPlayer(){
		if(this.jog=='X')
			this.jog='O';
		else
			this.jog='X';
	}
}
