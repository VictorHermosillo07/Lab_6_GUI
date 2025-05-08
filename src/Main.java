import chessboard.ChessBoard;
import enums.Color;
import enums.LocationX;
import enums.PieceType;
import interfaces.*;
import pieces.*;

import java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        ChessBoard chessBoard = new ChessBoard();
        List<Figure> pieces = new ArrayList<>();
        Set<PieceType> addedPieces = new HashSet<>();

        System.out.println("Setup your 5 chess pieces (Bishop is excluded).");

        while (pieces.size() < 2) {
            PieceType pieceType = null;
            while (pieceType == null) {
                System.out.print("Please select a chess piece (Pawn, Rook, Knight, Queen, King): ");
                String pieceInput = userInputScanner.nextLine().toUpperCase();
                try {
                    pieceType = PieceType.valueOf(pieceInput);
                    if (addedPieces.contains(pieceType)) {
                        System.out.println("You have already added a " + pieceType.toString().toLowerCase() + ". Please choose a different piece.");
                        pieceType = null;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid piece type. Please choose from the list.");
                }
            }

            addedPieces.add(pieceType);

            Color color = null;
            while (color == null) {
                System.out.print("Please select a color for " + pieceType.toString().toLowerCase() + " (WHITE/BLACK): ");
                try {
                    color = Color.valueOf(userInputScanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid color. Please enter WHITE or BLACK:");
                }
            }

            System.out.print("Please select a starting position (example: a 2): ");
            char column;
            int row;
            LocationX locationX = null;
            while (true) {
                column = userInputScanner.next().charAt(0);
                row = userInputScanner.nextInt();
                userInputScanner.nextLine(); // Consume the newline

                try {
                    locationX = LocationX.valueOf(String.valueOf(column).toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid column. Please choose a column between 'a' and 'h'.");
                    continue;
                }

                if (chessBoard.verifyCoordinate(locationX, row)) {
                    break;
                }
                System.out.println("Invalid position. Column must be between 'a' and 'h', row between 1 and 8.");
            }

            Figure piece = null;
            try {
                String className = "pieces." + pieceType.toString().charAt(0) + pieceType.toString().substring(1).toLowerCase();
                piece = (Figure) Class.forName(className).getDeclaredConstructor(Color.class, LocationX.class, int.class).newInstance(color, locationX, row);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (piece != null) {
                pieces.add(piece);
                System.out.println(pieceType + " placed at " + column + row);
            }
        }

        // Display all pieces and their info
        System.out.println("\nList of pieces and their current positions:");
        for (Figure piece : pieces) {
            System.out.println(piece.getPieceName().toString().toLowerCase() + " at " + piece.getColumn() + piece.getRow() +
                    " (Color: " + piece.getColor().toString().toLowerCase() + ")");
        }

        System.out.println("\nNow, test a move for all pieces.");
        LocationX targetColumn = null;
        int targetRow;
        while (true) {
            System.out.print("Enter target position (example: A 5): ");
            String position = userInputScanner.nextLine().toUpperCase().trim();

            // Split the position into column and row
            char targetColumnChar = position.charAt(0);
            targetRow = Integer.parseInt(position.substring(2).trim());

            try {
                targetColumn = LocationX.valueOf(String.valueOf(targetColumnChar));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid column. Please choose a column between 'A' and 'H'.");
                continue;
            }

            // Verify the target position using chessboard
            if (chessBoard.verifyCoordinate(targetColumn, targetRow)) {
                break;
            }
            System.out.println("Invalid target position. Column must be between 'A' and 'H', row between 1 and 8.");
        }

        // Check if the move is valid for each piece
        for (Figure piece : pieces) {
            if (piece.moveTo(targetColumn, targetRow)) {
                System.out.println(piece.getPieceName().toString().toLowerCase() + " at " + piece.getColumn() + piece.getRow() +
                        " can move to " + targetColumn + targetRow);
            } else {
                System.out.println(piece.getPieceName().toString().toLowerCase() + " at " + piece.getColumn() + piece.getRow() +
                        " cannot move to " + targetColumn + targetRow);
            }
        }
    }
}