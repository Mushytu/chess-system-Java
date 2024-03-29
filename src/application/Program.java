package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc1 = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		List<ChessPiece> capturedPiecesList = new ArrayList<>();

		while (!match.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(match, capturedPiecesList);
				System.out.print("\nSource: ");
				ChessPosition source = UI.readChessPosition(sc1);

				boolean[][] possibleMoves = match.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(match.getPieces(), possibleMoves);

				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(sc1);

				ChessPiece capturedPiece = match.performChessMove(source, target);

				if (capturedPiece != null) {
					capturedPiecesList.add(capturedPiece);
				}
				if (match.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc1.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc1.nextLine().toUpperCase();
					}
					match.replacePromotedPiece(type);
				}
			} catch (ChessException e1) {
				System.out.println(e1.getMessage());
				sc1.nextLine();
			} catch (InputMismatchException e2) {
				System.out.println(e2.getMessage());
				sc1.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(match, capturedPiecesList);
	}

}
