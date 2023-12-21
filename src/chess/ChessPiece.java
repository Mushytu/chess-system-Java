package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

	public void possibleMovePointer(Position auxPosition, boolean[][] matrix, int rowsOperator, int columnsOperator) {
		
		// empty position check
		while (getBoard().positionExists(auxPosition)) {
			matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
			// Cartesian coordinate system
			auxPosition.setRow(auxPosition.getRow() + rowsOperator);
			auxPosition.setColumn(auxPosition.getColumn() + columnsOperator);
			
		}
		
		// opponent held position check
		if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
			matrix[auxPosition.getRow()][auxPosition.getColumn()] = true;
		}
		
	}
	
}
