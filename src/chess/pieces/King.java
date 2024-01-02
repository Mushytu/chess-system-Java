package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);

		// above - north
		possibleMovePointer(auxPosition, matrix, -1, 0);

		// above right - northeast
		possibleMovePointer(auxPosition, matrix, -1, 1);

		// right - east
		possibleMovePointer(auxPosition, matrix, 0, 1);

		// below right - southeast
		possibleMovePointer(auxPosition, matrix, 1, 1);

		// below - south
		possibleMovePointer(auxPosition, matrix, 1, 0);

		// below left - south-west
		possibleMovePointer(auxPosition, matrix, 1, -1);

		// left - west
		possibleMovePointer(auxPosition, matrix, 0, -1);

		// above left - northwest
		possibleMovePointer(auxPosition, matrix, -1, -1);

		return matrix;
	}

	// specific King method
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix, int rowsOperator, int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);

		if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
			matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;

		}
	}
}
