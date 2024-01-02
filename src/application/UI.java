package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// boilerplate (colors) source:
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// text colors
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// background colors
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// boilerplate (screen cleaner) source:
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc1) {
		try {
			String stringFormattedPosition = sc1.nextLine();
			char column = stringFormattedPosition.charAt(0);
			Integer row = Integer.parseInt(stringFormattedPosition.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e1) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8");
		}
	}
	
	// match printing
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		
		System.out.println("\nTurn : " + chessMatch.getTurn());
		if (chessMatch.getCurrentPlayer() == Color.BLACK) {
			System.out.println("Waiting player: " + ANSI_YELLOW + chessMatch.getCurrentPlayer() + ANSI_RESET);
		} else {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());	
		}
	}
	
	//	board printing
	public static void printBoard(ChessPiece[][] pieces) {
		
		// columns index (above)
		System.out.println(ANSI_RED + "X" + ANSI_RESET + 
						ANSI_CYAN + " a|b|c|d|e|f|g|h " + ANSI_RESET + 
						ANSI_RED + "X" + ANSI_RESET);
		
		// board
		// rows index (right and left)
		for (int i = 0; i < pieces.length; i++) {
			System.out.printf(ANSI_CYAN + "%d %s", 8 - i, ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.printf(ANSI_CYAN + "%d %s\n", 8 - i, ANSI_RESET);
		}
		
		// columns index (below)
		System.out.println(ANSI_RED + "X" + ANSI_RESET + 
						ANSI_CYAN + " a|b|c|d|e|f|g|h " + ANSI_RESET + 
						ANSI_RED + "X" + ANSI_RESET);
	}
	
	// board print showing possible moves
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		// columns index (above)
		System.out.println(ANSI_RED + "X" + ANSI_RESET + 
						ANSI_CYAN + " a|b|c|d|e|f|g|h " + ANSI_RESET + 
						ANSI_RED + "X" + ANSI_RESET);

		// board
		// rows index (right and left)
		for (int i = 0; i < pieces.length; i++) {
			System.out.printf(ANSI_CYAN + "%d %s", 8 - i, ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.printf(ANSI_CYAN + "%d %s\n", 8 - i, ANSI_RESET);
		}
		
		// columns index (below)
		System.out.println(ANSI_RED + "X" + ANSI_RESET + 
						ANSI_CYAN + " a|b|c|d|e|f|g|h " + ANSI_RESET + 
						ANSI_RED + "X" + ANSI_RESET);
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printCapturedPieces (List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Captured Pieces: ");
		
		System.out.print("White: ");
		System.out.println(ANSI_WHITE + Arrays.toString(white.toArray()) + ANSI_RESET);
		
		System.out.print("Black: ");
		System.out.println(ANSI_YELLOW + Arrays.toString(black.toArray()) + ANSI_RESET);
	}
}
