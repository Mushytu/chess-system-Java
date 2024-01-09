package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position auxPosition = new Position(0, 0);
		Position auxPosition2 = new Position(0, 0);
		boolean longMovCheck = false;

		// white pawn logic
		if (getColor() == Color.WHITE) {
			// first
			auxPosition2.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(auxPosition2) && !getBoard().thereIsAPiece(auxPosition2)
					&& getMoveCount() == 0) {
				possibleMovePointer(auxPosition, matrix, longMovCheck, -2, 0);
			}

			// 'ahead'
			possibleMovePointer(auxPosition, matrix, longMovCheck, -1, 0);

			// diagonals
			possibleMovePointer(auxPosition, matrix, longMovCheck, -1, -1);
			possibleMovePointer(auxPosition, matrix, longMovCheck, -1, +1);

			// special move "en passant"
			if (position.getRow() == 3) {
				auxPosition2.setValues(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(auxPosition2) && isThereOpponentPiece(auxPosition2)
						&& getBoard().piece(auxPosition2) == chessMatch.getEnPassantVulnerable()) {
					matrix[auxPosition2.getRow() - 1][auxPosition2.getColumn()] = true;
				}
				auxPosition2.setValues(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(auxPosition2) && isThereOpponentPiece(auxPosition2)
						&& getBoard().piece(auxPosition2) == chessMatch.getEnPassantVulnerable()) {
					matrix[auxPosition2.getRow() - 1][auxPosition2.getColumn()] = true;
				}
			}
		}

		// black pawn logic
		if (getColor() == Color.BLACK) {
			// first
			auxPosition2.setValues(position.getRow() + 1, position.getColumn());
			if (getMoveCount() == 0 && getBoard().positionExists(auxPosition2)
					&& !getBoard().thereIsAPiece(auxPosition2)) {
				possibleMovePointer(auxPosition, matrix, longMovCheck, +2, 0);
			}

			// 'ahead'
			possibleMovePointer(auxPosition, matrix, longMovCheck, +1, 0);

			// diagonals
			possibleMovePointer(auxPosition, matrix, longMovCheck, +1, -1);
			possibleMovePointer(auxPosition, matrix, longMovCheck, +1, +1);

			// special move "en passant"
			if (position.getRow() == 4) {
				auxPosition2.setValues(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(auxPosition2) && isThereOpponentPiece(auxPosition2)
						&& getBoard().piece(auxPosition2) == chessMatch.getEnPassantVulnerable()) {
					matrix[auxPosition2.getRow() + 1][auxPosition2.getColumn()] = true;
				}
				auxPosition2.setValues(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(auxPosition2) && isThereOpponentPiece(auxPosition2)
						&& getBoard().piece(auxPosition2) == chessMatch.getEnPassantVulnerable()) {
					matrix[auxPosition2.getRow() + 1][auxPosition2.getColumn()] = true;
				}
			}
		}

		return matrix;
	}

	// specific Pawn method
	@Override
	public void possibleMovePointer(Position auxPosition, boolean[][] matrix, boolean longMovCheck, int rowsOperator,
			int columnsOperator) {
		auxPosition.setValues(position.getRow() + rowsOperator, position.getColumn() + columnsOperator);

		// can piece make long moves?
		longMovCheck = false;

		if (getBoard().positionExists(auxPosition)) {
			if (columnsOperator != 0 && isThereOpponentPiece(auxPosition)
					|| (columnsOperator == 0 && !getBoard().thereIsAPiece(auxPosition))) {
				super.possibleMovePointer(auxPosition, matrix, longMovCheck, rowsOperator, columnsOperator);
			}
		}

	}

}
