package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);
		boolean longMovCheck = false;

		// above - north
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 0);

		// above right - northeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 1);

		// right - east
		possibleMovePointer(auxPosition, matrix, longMovCheck, 0, 1);

		// below right - southeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, 1);

		// below - south
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, 0);

		// below left - south-west
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, -1);

		// left - west
		possibleMovePointer(auxPosition, matrix, longMovCheck, 0, -1);

		// above left - northwest
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, -1);

		// specialmove "Castling"
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// specialmove "Castling kingside rook"
			Position rook1 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(rook1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					matrix[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// specialmove "Castling queenside rook"
			Position rook2 = new Position(position.getRow(), position.getColumn() + 3);
			if (testRookCastling(rook2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					matrix[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return matrix;
	}

	// specific King method
	@Override
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix, boolean longMovCheck, int rowsOperator,
			int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);

		// can piece make long moves?
		longMovCheck = false;

		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			super.possibleMovePointer(auxPosition, matrix, longMovCheck, rowsOperator, columnsOperator);
		}
	}
}
