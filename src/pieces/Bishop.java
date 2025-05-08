package pieces;

import enums.PieceColor;
import enums.LocationX;
import interfaces.IntBishop;

public class Bishop extends Figure implements IntBishop {
    public Bishop(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row, enums.PieceType.BISHOP);
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return moveToBishop(X, Y, this);
    }
}