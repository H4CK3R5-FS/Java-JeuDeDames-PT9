package game;

import java.util.ArrayList;
import java.util.HashMap;

import events.Round;
import model.Match;
import model.Piece;
import utilitaires.Menu;
import utilitaires.Utilitaires;

public class Game {
//draw board
//add players
//add pieces
	//String[][] board;
	Match match=Menu.menu_Principale();
	//int size = Menu.menu_Size();
	String[][] board = new String[match.getSize()][match.getSize()];
	ArrayList<Piece> alP = new ArrayList<Piece>();
	HashMap<String, Piece> hmP = new HashMap<String, Piece>();

	public void game() {
		//Menu.menu_Principale(board);
		Utilitaires.draw_Board(board);
		Utilitaires.add_Pieces_To_List(alP, board.length);
		Utilitaires.add_Pieces_To_Board(alP, hmP, board);
		Round.round(hmP, board);
	}
}
