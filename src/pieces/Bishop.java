package pieces;

import enums.PieceColor;
import enums.LocationX;
import interfaces.IntFigure;

public class Bishop extends Figure implements IntFigure {
    public Bishop(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row, enums.PieceType.BISHOP);
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return Math.abs(X.ordinal() - getColumn().ordinal()) == Math.abs(Y - getRow());
    }
}