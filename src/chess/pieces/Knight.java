package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);
		boolean longMovCheck = false;

		// "L" right
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, -2);

		// above right - northeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, -2, -1);

		// right - east
		possibleMovePointer(auxPosition, matrix, longMovCheck, -2, 1);

		// below right - southeast
		possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 2);

		// below - south
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, 2);

		// below left - south-west
		possibleMovePointer(auxPosition, matrix, longMovCheck, 2, 1);

		// left - west
		possibleMovePointer(auxPosition, matrix, longMovCheck, 2, -1);

		// above left - northwest
		possibleMovePointer(auxPosition, matrix, longMovCheck, 1, -2);

		return matrix;
	}

	// specific King method
	@Override
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix,
			boolean longMovCheck, int rowsOperator, int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);
		
		// can piece make long moves?
		longMovCheck = false;
		
		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			super.possibleMovePointer(auxPosition, matrix, longMovCheck, rowsOperator,
					columnsOperator);
		}
	}
}
