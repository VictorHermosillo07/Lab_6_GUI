package pieces;

import enums.PieceColor;
import enums.LocationX;
import interfaces.IntBishop;

public class Queen extends Rook implements IntBishop {
    public Queen(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row);
        this.pieceName = enums.PieceType.QUEEN;
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return super.moveTo(X, Y) || moveToBishop(X, Y, this);
    }
}