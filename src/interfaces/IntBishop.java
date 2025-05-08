package interfaces;

import enums.LocationX;
import pieces.Figure;

public interface IntBishop {
    default boolean moveToBishop(LocationX X, int Y, Figure piece) {
        return Math.abs(X.ordinal() - piece.getColumn().ordinal()) == Math.abs(Y - piece.getRow());
    }
}