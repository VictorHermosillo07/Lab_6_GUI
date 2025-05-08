package pieces;

import enums.PieceColor;
import enums.LocationX;

public class Pawn extends Figure {
    public Pawn(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row, enums.PieceType.PAWN);
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        int direction = (this.pieceColor == PieceColor.WHITE) ? 1 : -1;
        int startRow = (this.pieceColor == PieceColor.WHITE) ? 2 : 7;

        if (X == this.column && Y == this.row + direction) {
            return true;
        }
        return this.row == startRow && X == this.column && Y == this.row + 2 * direction;
    }
}