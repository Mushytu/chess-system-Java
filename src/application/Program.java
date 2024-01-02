package application;

import java.util.InputMismatchException;
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
		
		while (true) {
			try {
			UI.clearScreen();
			UI.printMatch(match);
			System.out.print("\nSource: ");
			ChessPosition source = UI.readChessPosition(sc1);
			
			boolean[][] possibleMoves = match.possibleMoves(source);
			UI.clearScreen();
			UI.printBoard(match.getPieces(), possibleMoves);
	
			System.out.print("\nTarget: ");
			ChessPosition target = UI.readChessPosition(sc1);
			
			ChessPiece capturedPiece = match.performChessMove(source, target);
			} catch(ChessException e1) {
				System.out.println(e1.getMessage());
				sc1.nextLine();
			} catch(InputMismatchException e2) {
				System.out.println(e2.getMessage());
				sc1.nextLine();
			}	
		}
	}

}
