package utilitaires;

import game.Texts;
import model.Joueur;
import model.Match;

public class Menu {

	public static void menu_Size(Match match) {
		System.out.println(Texts.size_Choice);
		int choice = Utilitaires.saisie_char_num(3, "votre choix : ");
		// string choice = Utilitaires.saisieString();
		switch (choice) {
		case 1:
			match.setSize(10);
			break;
		case 2:
			match.setSize(12);
			break;
		case 3:
			match.setSize(14);
			break;
		default:
			System.out.println("choix invalide alors la taille sera 8*8");
			break;
		}
		// return size;
	}

	public static void menu_Player(Joueur j1, Joueur j2) {
		
	}

	public static Match menu_Principale() {
		// String choice;
		// int choice_Int;
		int choice = 0;
		Match match = new Match(10, null, null);
		do {
			System.out.println(Texts.menu_Principale);
			// choice = Utilitaires.saisieString();
			// choice_Int = Integer.valueOf(choice);
			choice = Utilitaires.saisie_char_num(3, "votre choix : ");
			switch (choice) {
			case 1:
				menu_Size(match);
				// match.setSize(size);
				break;
			case 2:
				System.out.println("joueurs ");
				break;
			case 3:
				System.out.println();
				break;
			default:
				System.out.println("erreur menu");
				break;
			}
		} while (choice != 3);
		return match;
	}

}
