package pieces;

import enums.PieceColor;
import enums.LocationX;

public class Knight extends Figure {
    public Knight(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row, enums.PieceType.KNIGHT);
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        int colDiff = Math.abs(X.ordinal() - this.column.ordinal());
        int rowDiff = Math.abs(Y - this.row);
        return (colDiff == 2 && rowDiff == 1) || (colDiff == 1 && rowDiff == 2);
    }
}