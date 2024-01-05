package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

	public void possibleMovePointer(Position auxPosition, boolean[][] matrix,
			boolean longMovCheck, int rowsOperator, int columnsOperator) {
		
		// long movement piece check
		if (longMovCheck) {
			matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
			auxPosition.setRow(auxPosition.getRow() + rowsOperator);
			auxPosition.setColumn(auxPosition.getColumn() + columnsOperator);
		} else {
			matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}
	}
}
