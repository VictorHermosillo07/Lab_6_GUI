import chessboard.ChessBoard;
import enums.PieceColor;
import enums.LocationX;
import enums.PieceType;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::runGUIVersion);
    }

    private static void runGUIVersion() {
        JFrame frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());

        // Chessboard panel
        JPanel boardContainerPanel = new JPanel(new GridLayout(8, 8));
        JPanel[][] boardCells = new JPanel[8][8];
        String[][] gameBoard = new String[8][8]; // Backend logic for the board

        // Initialize chessboard with alternating colors
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel boardCell = new JPanel(new GridLayout());
                boolean isWhite = (i + j) % 2 == 0;
                boardCell.setBackground(isWhite ? Color.WHITE : Color.GRAY);
                boardCells[i][j] = boardCell;
                boardContainerPanel.add(boardCell);
            }
        }

        frame.add(boardContainerPanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel();

        // Dropdowns for selecting piece, pieceColor, and positions
        String[] pieces = {"Pawn", "Rook", "Knight", "Queen", "King", "Bishop"};
        String[] colors = {"WHITE", "BLACK"};
        String[] indices = {"0", "1", "2", "3", "4", "5", "6", "7"};


        JComboBox<String> pieceSelector = new JComboBox<>(pieces);
        JComboBox<String> colorSelector = new JComboBox<>(colors);
        JComboBox<String> rowSelector = new JComboBox<>(indices);
        JComboBox<String> columnSelector = new JComboBox<>(indices);
        JComboBox<String> targetRowSelector = new JComboBox<>(indices);
        JComboBox<String> targetColumnSelector = new JComboBox<>(indices);

        // Buttons
        JButton placeButton = new JButton("Place Piece");
        JButton moveButton = new JButton("Move Piece");
        JButton clearButton = new JButton("Clear Board");

        // Add components to control panel
        controlPanel.add(new JLabel("Piece:"));
        controlPanel.add(pieceSelector);
        controlPanel.add(new JLabel("PieceColor:"));
        controlPanel.add(colorSelector);
        controlPanel.add(new JLabel("Row:"));
        controlPanel.add(rowSelector);
        controlPanel.add(new JLabel("Column:"));
        controlPanel.add(columnSelector);
        controlPanel.add(placeButton);

        controlPanel.add(new JLabel("Target Row:"));
        controlPanel.add(targetRowSelector);
        controlPanel.add(new JLabel("Target Column:"));
        controlPanel.add(targetColumnSelector);
        controlPanel.add(moveButton);

        controlPanel.add(clearButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        // Chessboard and logic setup
        ChessBoard chessBoard = new ChessBoard();
        List<Figure> placedPieces = new ArrayList<>();
        Set<PieceType> addedPieces = new HashSet<>();

        // Place piece button listener
        placeButton.addActionListener(e -> {
            try {
                String pieceName = (String) pieceSelector.getSelectedItem();
                PieceType pieceType = PieceType.valueOf(pieceName.toUpperCase());

                // Ensure piece is not reused
                if (addedPieces.contains(pieceType)) {
                    JOptionPane.showMessageDialog(frame, "Piece already placed. Choose another.");
                    return;
                }

                String colorName = (String) colorSelector.getSelectedItem();
                PieceColor pieceColor = PieceColor.valueOf(colorName);

                int row = Integer.parseInt(rowSelector.getSelectedItem().toString());
                int column = Integer.parseInt(columnSelector.getSelectedItem().toString());

                // Verify position
                if (!chessBoard.verifyCoordinate(LocationX.values()[column], row + 1)) {
                    JOptionPane.showMessageDialog(frame, "Invalid position.");
                    return;
                }

                // Create and place piece
                Figure piece = (Figure) Class.forName("pieces." + pieceType.toString().charAt(0) + pieceType.toString().substring(1).toLowerCase())
                        .getDeclaredConstructor(PieceColor.class, LocationX.class, int.class)
                        .newInstance(pieceColor, LocationX.values()[column], row + 1);

                placedPieces.add(piece);
                addedPieces.add(pieceType);

                // Update GUI
                placeImage(boardCells, row, column, "src/images/" + pieceType.toString().toLowerCase() + "_" + colorName.toLowerCase() + ".png");
                gameBoard[row][column] = pieceType.toString();

                JOptionPane.showMessageDialog(frame, pieceType + " placed at " + LocationX.values()[column] + (row + 1));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // Move piece button listener
        moveButton.addActionListener(e -> {
            try {
                int currentRow = Integer.parseInt(rowSelector.getSelectedItem().toString());
                int currentColumn = Integer.parseInt(columnSelector.getSelectedItem().toString());
                int targetRow = Integer.parseInt(targetRowSelector.getSelectedItem().toString());
                int targetColumn = Integer.parseInt(targetColumnSelector.getSelectedItem().toString());

                // Ensure a piece exists at the current position
                if (gameBoard[currentRow][currentColumn] == null) {
                    JOptionPane.showMessageDialog(frame, "No piece at the selected position.");
                    return;
                }

                // Find the piece
                Figure piece = placedPieces.stream()
                        .filter(p -> p.getRow() == currentRow + 1 && p.getColumn() == LocationX.values()[currentColumn])
                        .findFirst()
                        .orElse(null);

                if (piece == null) {
                    JOptionPane.showMessageDialog(frame, "Piece not found.");
                    return;
                }

                // Verify target position
                if (!chessBoard.verifyCoordinate(LocationX.values()[targetColumn], targetRow + 1)) {
                    JOptionPane.showMessageDialog(frame, "Invalid target position.");
                    return;
                }

                // Check if move is valid
                if (piece.moveTo(LocationX.values()[targetColumn], targetRow + 1)) {
                    // Update GUI
                    boardCells[currentRow][currentColumn].removeAll();
                    boardCells[currentRow][currentColumn].revalidate();
                    boardCells[currentRow][currentColumn].repaint();

                    placeImage(boardCells, targetRow, targetColumn, "src/images/" + piece.getPieceName().toString().toLowerCase() + "_" + piece.getColor().toString().toLowerCase() + ".png");

                    // Update backend
                    gameBoard[currentRow][currentColumn] = null;
                    gameBoard[targetRow][targetColumn] = piece.getPieceName().toString();

                    JOptionPane.showMessageDialog(frame, "Piece moved to " + LocationX.values()[targetColumn] + (targetRow + 1));
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid move for " + piece.getPieceName().toString().toLowerCase());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });

        // Clear board button listener
        clearButton.addActionListener(e -> {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    boardCells[row][column].removeAll();
                    boardCells[row][column].revalidate();
                    boardCells[row][column].repaint();
                    gameBoard[row][column] = null;
                }
            }
            placedPieces.clear();
            addedPieces.clear();
            JOptionPane.showMessageDialog(frame, "Board cleared.");
        });

        frame.setVisible(true);
    }

    private static void placeImage(JPanel[][] boardCells, int row, int col, String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JLabel pieceLabel = new JLabel(scaledIcon);
            boardCells[row][col].add(pieceLabel);
            boardCells[row][col].revalidate();
            boardCells[row][col].repaint();
        } catch (Exception e) {
            System.out.println("Image not found: " + imagePath);
        }
    }
}
