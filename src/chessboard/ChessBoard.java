package chessboard;

import enums.LocationX;
import interfaces.IntChessBoard;

public class ChessBoard implements IntChessBoard {
    @Override
    public boolean verifyCoordinate(LocationX X, int Y) {
        return X != null && Y >= 1 && Y <= 8;
    }
}