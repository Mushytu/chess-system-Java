package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);
		boolean longMovCheck = false;

		// above left - northwest
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, -1);

		// above right - northeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 1);

		// below right - southeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, 1);

		// below left - south-west
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, -1);

		return matrix;
	}

	// specific Rook method
	@Override
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix,
			boolean longMovCheck, int rowsOperator, int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);
		
		// can piece make long moves?
		longMovCheck = true;
		
		while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
			super.possibleMovePointer(auxPosition, matrix, longMovCheck, rowsOperator,
					columnsOperator);
		}

	}

}
