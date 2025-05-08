package testing_JUnit;

import enums.PieceColor;
import enums.LocationX;
import org.junit.jupiter.api.Test;
import pieces.*;

import static org.junit.jupiter.api.Assertions.*;

public class MoveLogicTest {
    //Bishop
    @Test
    void testValidDiagonalMoveBishop() {
        Bishop bishop = new Bishop(PieceColor.WHITE, LocationX.D, 4);
        assertTrue(bishop.moveTo(LocationX.F, 6));
    }

    @Test
    void testInvalidStraightMoveBishop() {
        Bishop bishop = new Bishop(PieceColor.WHITE, LocationX.D, 4);
        assertFalse(bishop.moveTo(LocationX.D, 6));
    }

    @Test
    void testInvalidNonDiagonalMoveBishop() {
        Bishop bishop = new Bishop(PieceColor.WHITE, LocationX.D, 4);
        assertFalse(bishop.moveTo(LocationX.E, 6));
    }

    //Queen
    @Test
    void testValidDiagonalMoveQueen() {
        Queen queen = new Queen(PieceColor.WHITE, LocationX.D, 4);
        assertTrue(queen.moveTo(LocationX.F, 6));
    }

    @Test
    void testValidStraightMoveQueen() {
        Queen queen = new Queen(PieceColor.WHITE, LocationX.D, 4);
        assertTrue(queen.moveTo(LocationX.D, 8));
    }

    @Test
    void testInvalidNonStraightOrDiagonalMoveQueen() {
        Queen queen = new Queen(PieceColor.WHITE, LocationX.D, 4);
        assertFalse(queen.moveTo(LocationX.E, 6));
    }

    //Rook
    @Test
    void testValidVerticalMove() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertTrue(rook.moveTo(LocationX.A, 8));
    }

    @Test
    void testValidHorizontalMove() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertTrue(rook.moveTo(LocationX.H, 1));
    }

    @Test
    void testInvalidDiagonalMove() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertFalse(rook.moveTo(LocationX.B, 2));
    }
}
