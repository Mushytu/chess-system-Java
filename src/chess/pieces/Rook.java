package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);
		boolean longMovCheck = false;

		// above
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 0);

		// left
		possibleMovePointer(auxPosition, matrix, longMovCheck, 0, -1);

		// right
		possibleMovePointer(auxPosition, matrix, longMovCheck, 0, 1);

		// below
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, 0);

		return matrix;
	}

	// specific Rook method
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
