package pieces;

import enums.PieceColor;
import enums.LocationX;
import interfaces.IntFigure;

public class Queen extends Rook implements IntFigure {
    public Queen(PieceColor pieceColor, LocationX column, int row) {
        super(pieceColor, column, row);
        this.pieceName = enums.PieceType.QUEEN;
    }

    @Override
    public boolean moveTo(LocationX X, int Y) {
        return super.moveTo(X, Y) || Math.abs(X.ordinal() - getColumn().ordinal()) == Math.abs(Y - getRow());
    }
}