package events;

import java.util.HashMap;

import game.Texts;
import model.Mouvement;
import model.Piece;
import utilitaires.Utilitaires;

public class Round {

//round sert à gerer un tour pour un joueur
	public static void round(HashMap<String, Piece> hmP, String[][] board) {
		while (true) {
			Utilitaires.read_Board(board);
			System.out.println(Texts.piece_Choice);
			String choix = Utilitaires.saisieString().toUpperCase();
			while (!hmP.containsKey(choix)) {
				choix = Utilitaires.saisieString().toUpperCase();
			}
			// prev_pos est la position precedente de Pion avant de le bouger
			String prev_pos = Utilitaires.get_Position(board, hmP.get(choix).getX(), hmP.get(choix).getY());
			Mouvement mvt = Verifications.possible_Moves(hmP, hmP.get(choix), board);
			if (mvt.isPossible_Moves_Exist()) {
				System.out.println("choisis ta position : ");
				String choix_pos = Utilitaires.saisieString().toUpperCase();

				while (!Verifications.verif_Choice(choix_pos, mvt.getPossible_Moves())) {
					choix_pos = Utilitaires.saisieString().toUpperCase();
				}
				System.out.println(mvt.getEat_Moves().containsKey(choix_pos));
				if (mvt.getEat_Moves().containsKey(choix_pos)) {
					Events.eat_Event(hmP.get(choix), mvt.getEat_Moves().get(choix_pos), board, hmP);
				}
				int x = choix_pos.charAt(0);
				x = x - 64;
				int y = Integer.valueOf(choix_pos.substring(1));
				System.out.println("Player moved " + hmP.get(choix).getName() + " from " + prev_pos + " to "
						+ Utilitaires.get_Position(board, x, y));
				Events.move(hmP, hmP.get(choix), board, x, y);

				Utilitaires.reinitBoard(hmP, board);
			}

		}

	}
}
