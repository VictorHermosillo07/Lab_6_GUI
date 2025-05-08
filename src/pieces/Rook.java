package pieces;

import enums.PieceColor;
import enums.LocationX;

public class Rook extends Figure {
    public Rook(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row, enums.PieceType.ROOK);
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return X == this.column || Y == this.row;
    }
}