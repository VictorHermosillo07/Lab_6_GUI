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
    void testValidVerticalMoveRook() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertTrue(rook.moveTo(LocationX.A, 8));
    }

    @Test
    void testValidHorizontalMoveRook() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertTrue(rook.moveTo(LocationX.H, 1));
    }

    @Test
    void testInvalidDiagonalMoveRook() {
        Rook rook = new Rook(PieceColor.BLACK, LocationX.A, 1);
        assertFalse(rook.moveTo(LocationX.B, 2));
    }

    //King
    @Test
    void testValidMoveKing() {
        King king = new King(PieceColor.WHITE, LocationX.E, 4);
        assertTrue(king.moveTo(LocationX.E, 5));
        assertTrue(king.moveTo(LocationX.F, 5));
    }

    @Test
    void testInvalidMoveKing() {
        King king = new King(PieceColor.WHITE, LocationX.E, 4);
        assertFalse(king.moveTo(LocationX.E, 6));
        assertFalse(king.moveTo(LocationX.G, 4));
    }

    //Pawn
    @Test
    void testValidMovePawn() {
        Pawn pawn = new Pawn(PieceColor.WHITE, LocationX.E, 2);
        assertTrue(pawn.moveTo(LocationX.E, 3));
        assertTrue(pawn.moveTo(LocationX.E, 4));
    }

    @Test
    void testInvalidMovePawn() {
        Pawn pawn = new Pawn(PieceColor.WHITE, LocationX.E, 2);
        assertFalse(pawn.moveTo(LocationX.E, 5));
        assertFalse(pawn.moveTo(LocationX.F, 3));
    }

    //Knight
    @Test
    void testValidMoveKnight() {
        Knight knight = new Knight(PieceColor.BLACK, LocationX.G, 8);
        assertTrue(knight.moveTo(LocationX.H, 6));
        assertTrue(knight.moveTo(LocationX.F, 6));
    }

    @Test
    void testInvalidMoveKnight() {
        Knight knight = new Knight(PieceColor.BLACK, LocationX.G, 8);
        assertFalse(knight.moveTo(LocationX.G, 6));
        assertFalse(knight.moveTo(LocationX.E, 6));
    }
}
