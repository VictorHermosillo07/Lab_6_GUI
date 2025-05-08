package pieces;

import enums.PieceColor;
import enums.LocationX;

public class King extends Queen {
    public King(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row);
        this.pieceName = enums.PieceType.KING;
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return Math.abs(X.ordinal() - this.column.ordinal()) <= 1 &&
                Math.abs(Y - this.row) <= 1;
    }
}