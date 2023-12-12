package application;

import java.util.Locale;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc1 = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		
		//capture logic test
		while (true) {
			UI.printBoard(match.getPieces());
			System.out.print("\nSource: ");
			ChessPosition source = UI.readChessPosition(sc1);
			
			System.out.print("\nTarget: ");
			ChessPosition target = UI.readChessPosition(sc1);
			
			ChessPiece capturedPiece = match.performChessMove(source, target);
		}
	}

}
