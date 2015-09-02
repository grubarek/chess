package parser;

import body.Scaner;

import java.util.List;

/**
 * Created by Marcin on 2015-05-26.
 */
public class Parser {

    public static String[] boardColumns, boardRows;
    public static ChessField[][] chessBoard;

    public Parser() {
        boardColumns = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        boardRows = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        chessBoard = new ChessField[8][8];
        //COLOR: 0 WHITE 1 BLACK
        //PIECE: 5 ROOK 4 KNIGHT 3 BISHOP 2 QUEEN 1 KING 0 PAWN

        initializeBoard();
    }

    public void initializeBoard() {
        for (int column = 1; column < 9; column++) {
            for (int row = 1; row < 9; row++) {

                int color = row > 2 ? 0 : 1; // 0 BIALE 1 CZARNE

                if (row == 1 || row == 8) { // dodajemy figury
                    if (column == 1 || column == 8) {// ROOK
                        ChessPiece piece = new ChessPiece(row, column, 5, color);
                        chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);

                    } else if (column == 2 || column == 7) {// KNIGHT
                        ChessPiece piece = new ChessPiece(row, column, 4, color);
                        chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);

                    } else if (column == 3 || column == 6) {// BISHOP
                        ChessPiece piece = new ChessPiece(row, column, 3, color);
                        chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);

                    } else if (column == 4) {// QUEEN
                        ChessPiece piece = new ChessPiece(row, column, 2, color);
                        chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);
                    } else if (column == 5) {// KING
                        ChessPiece piece = new ChessPiece(row, column, 1, color);
                        chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);
                    }

                } else if (row == 2 || row == 7) { // dodajemy piony
                    ChessPiece piece = new ChessPiece(row, column, 0, color);
                    chessBoard[column - 1][row - 1] = new ChessField(row, column, piece);

                } else {
                    chessBoard[column - 1][row - 1] = new ChessField(row, column);
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoard[j][i].toString());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

    }

    public boolean isAvailable(int newRow, int newCol, int oldRow, int OldCol) {
        if (chessBoard[newCol][newRow].isEmpty) {
            return true;
        } else {
            return chessBoard[newCol][newRow].getPiece().color != chessBoard[OldCol][oldRow].getPiece().color;
        }
    }

    //t typ c - pole
    public void startParsing(List<Scaner.Token> tokens) {
        ChessPiece.Color color = ChessPiece.Color.BLACK;
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println("Token nr:" + (i + 1) + "       " + tokens.get(i).t + " : " + tokens.get(i).c);
            if (tokens.get(i).t == Scaner.Type.BIERKA) { // figura col row
                int col = java.util.Arrays.binarySearch(boardColumns, tokens.get(i + 1).c) + 1;
                int row = Integer.parseInt(tokens.get(i + 2).c);
                System.out.println(tokens.get(i).c + "  c: " + col + " r: " + row);

                if (!chessBoard[col - 1][row - 1].isEmpty) {
                    if (chessBoard[col - 1][row - 1].getPiece().checkType(tokens.get(i).c)) {

                        int newCol = java.util.Arrays.binarySearch(boardColumns, tokens.get(i + 3).c) + 1;
                        int newRow = Integer.parseInt(tokens.get(i + 4).c);

                        if (chessBoard[col - 1][row - 1].getPiece().color == color) {
                            if (chessBoard[col - 1][row - 1].getPiece().canGoTo(newRow, newCol) &&
                                    isAvailable(newRow - 1, newCol - 1, row - 1, col - 1)) {

                                chessBoard[col - 1][row - 1].getPiece().goTo(newRow, newCol);
                                chessBoard[newCol - 1][newRow - 1].setPiece(chessBoard[col - 1][row - 1].getPiece());
                                chessBoard[col - 1][row - 1].setPiece(null);
                                chessBoard[col - 1][row - 1].setIsEmpty(true);

                                printBoard();

                                if (color == ChessPiece.Color.BLACK) {
                                    color = ChessPiece.Color.WHITE;
                                } else {
                                    color = ChessPiece.Color.BLACK;
                                }


                            } else {
                                System.out.println("NIEPOPRAWNY RUCH");
                                return;
                            }
                        } else {
                            System.out.println("NIEPOPRAWNY KOLOR");
                            return;
                        }


                    } else {
                        System.out.println("BRAK ZGODNOSCI FIGUR");
                        return;
                    }
                } else {
                    System.out.println("BRAK FIGURY W WYZNACZONYM MIEJSCU");
                    return;
                }
            }
        }

    }
}
