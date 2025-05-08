package pieces;

import enums.PieceColor;
import enums.LocationX;
import enums.PieceType;
import interfaces.IntFigure;

public abstract class Figure implements IntFigure {
    protected PieceType pieceName;
    protected PieceColor pieceColor;
    protected LocationX column;
    protected int row;

    public Figure(PieceColor pieceColor, LocationX column, int row, PieceType pieceName) {
        this.pieceColor = pieceColor;
        this.column = column;
        this.row = row;
        this.pieceName = pieceName;
    }

    public LocationX getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public PieceType getPieceName() {
        return pieceName;
    }

    @Override
    public String toString() {
        return pieceName + " at " + column + row;
    }

    public PieceColor getColor() {
        return pieceColor;
    }
}