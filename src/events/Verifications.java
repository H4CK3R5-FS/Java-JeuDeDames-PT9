package events;

import java.util.ArrayList;
import java.util.HashMap;

import model.Mouvement;
import model.Piece;
import utilitaires.Utilitaires;

public class Verifications {
	public static boolean verif_Choice(String position, ArrayList<String> alMoves) {
		if (position.length() > 0) {
			if (!((position.charAt(0) >= 'A') && (position.charAt(0) <= 'L'))) {
				return false;
			}
			for (int i = 1; i < position.length(); i++) {
				if (!((position.charAt(i) >= '0') && (position.charAt(i) <= '9'))) {
					return false;
				}
			}
			if (!(alMoves.contains(position))) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	public static void eat_Condition(HashMap<String, Piece> hmP, HashMap<String, Piece> Eat_Map, Piece P,
			ArrayList<String> alMoves, String[][] board, int signY, int signX) {
		// HashMap<String, Piece> Eat_mouvement = new HashMap<>();
		if (hmP.containsKey(board[P.getY() + 1 * signY][P.getX() + 1 * signX])) {
			if (hmP.get(board[P.getY() + 1 * signY][P.getX() + 1 * signX]).getColour() != hmP
					.get(board[P.getY()][P.getX()]).getColour()) {
				if (board[P.getY() + 2 * signY][P.getX() + 2 * signX] == ".") {
					Eat_Map.put(Utilitaires.get_Position(board, P.getX() + 2 * signX, P.getY() + 2 * signY),
							hmP.get(board[P.getY() + 1 * signY][P.getX() + 1 * signX]));
					alMoves.add(Utilitaires.get_Position(board, P.getX() + 2 * signX, P.getY() + 2 * signY));
					board[P.getY() + 2 * signY][P.getX() + 2 * signX] = Utilitaires.get_Position(board,
							P.getX() + 2 * signX, P.getY() + 2 * signY);
				}
			}
		}
	}

	// cette fonction permet de verifier le mouvement du pion en dépendant de sign
	// (si sign=-1 on verifie en haut sinon en bas)
	public static void verif_Movement(HashMap<String, Piece> hmP, Piece P, ArrayList<String> alMoves, String[][] board,
			int signY, int signX) {
		if (board[P.getY() + 1 * signY][P.getX() + 1 * signX] == ".") {
			alMoves.add(Utilitaires.get_Position(board, P.getX() + 1 * signX, P.getY() + 1 * signY));
			board[P.getY() + 1 * signY][P.getX() + 1 * signX] = Utilitaires.get_Position(board, P.getX() + 1 * signX,
					P.getY() + 1 * signY);
		}

	}

	// afficher les mouvement possibles
	public static Mouvement possible_Moves(HashMap<String, Piece> hmP, Piece P, String[][] board) {
		// possible moves
		Mouvement mvt = new Mouvement(false, null, hmP);
		ArrayList<String> alMoves = new ArrayList<String>();
		HashMap<String, Piece> Eat_Map = new HashMap<>();
		if (P.isQueen()) {
			eat_Condition(hmP, Eat_Map, P, alMoves, board, 1, -1);
			eat_Condition(hmP, Eat_Map, P, alMoves, board, 1, 1);
			eat_Condition(hmP, Eat_Map, P, alMoves, board, -1, -1);
			eat_Condition(hmP, Eat_Map, P, alMoves, board, -1, 1);
			verif_Movement(hmP, P, alMoves, board, -1, -1);
			verif_Movement(hmP, P, alMoves, board, -1, 1);
			verif_Movement(hmP, P, alMoves, board, 1, 1);
			verif_Movement(hmP, P, alMoves, board, 1, -1);
		} else if (P.getColour() == "Blanc") {
			eat_Condition(hmP, Eat_Map, P, alMoves, board, -1, -1);
			eat_Condition(hmP, Eat_Map, P, alMoves, board, -1, 1);
			verif_Movement(hmP, P, alMoves, board, -1, -1);
			verif_Movement(hmP, P, alMoves, board, -1, 1);
		} else if (P.getColour() == "Noir") {
			eat_Condition(hmP, Eat_Map, P, alMoves, board, 1, -1);
			eat_Condition(hmP, Eat_Map, P, alMoves, board, 1, 1);
			verif_Movement(hmP, P, alMoves, board, 1, 1);
			verif_Movement(hmP, P, alMoves, board, 1, -1);
		}
		mvt.setEat_Moves(Eat_Map);
		mvt.setPossible_Moves(alMoves);
		if (alMoves.isEmpty()) {
			System.out.println("aucun mouvement a été detecté pour le pion " + P.getName());
			mvt.setPossible_Moves_Exist(false);
		} else {
			Utilitaires.display_List(alMoves);
			Utilitaires.read_Board(board);
			mvt.setPossible_Moves_Exist(true);
		}
		return mvt;

	}
}
