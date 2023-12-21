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

		// above
		possibleMovePointer(auxPosition, matrix, -1, 0);

		// left
		possibleMovePointer(auxPosition, matrix, 0, -1);

		// right
		possibleMovePointer(auxPosition, matrix, 0, 1);

		// below
		possibleMovePointer(auxPosition, matrix, 1, 0);

		return matrix;
	}

	// specific Rook method
	@Override
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix, int rowsOperator, int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);

		if (!getBoard().thereIsAPiece(auxPosition)) {
			super.possibleMovePointer(auxPosition, matrix, rowsOperator, columnsOperator);
		}
	}

}
