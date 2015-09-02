package parser;


/**
 * Created by Marcin on 2015-05-26.
 */

/**
 * Row <1,8>
 * Col <1,8>
 */
public class ChessPiece {
    class Moves {
        int row, col;

        public Moves(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Moves moves = (Moves) o;

            if (row != moves.row) return false;
            return col == moves.col;

        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    public enum Piece {
        KING,
        QUEEN,
        ROOK,
        BISHOP,
        KNIGHT,
        PAWN
    }

    public static enum Color {
        WHITE,
        BLACK
    }

    int row, col;
    Piece piece;
    Color color;

    public ChessPiece(int row, int col, int pieceNumber, int colorNumber) {
        this.row = row;
        this.col = col;
        switch (pieceNumber) {
            case 0:
                piece = Piece.PAWN;
                break;
            case 1:
                piece = Piece.KING;
                break;
            case 2:
                piece = Piece.QUEEN;
                break;
            case 3:
                piece = Piece.BISHOP;
                break;
            case 4:
                piece = Piece.KNIGHT;
                break;
            case 5:
                piece = Piece.ROOK;
                break;
        }

        switch (colorNumber) {
            case 0:
                color = Color.WHITE;
                break;
            case 1:
                color = Color.BLACK;
                break;
        }
    }

    public void goTo(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean canGoTo(int row, int col) {
        if (row > 8 || row < 1 || col > 8 || col < 1) {
            return false;
        }

        if (piece == Piece.PAWN) {
            if (col != this.col) {
                return false;
            } else {
                if (this.color == Color.BLACK && this.row < row) {
                    return true;
                } else if (this.color == Color.WHITE && this.row > row) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (piece == Piece.KING) {
            return !(Math.abs(this.col - col) > 1 || Math.abs(this.row - row) > 1); //ruch wiecej niz o 1 pole -> false
        } else if (piece == Piece.QUEEN) {
            return true;
        } else if (piece == Piece.BISHOP) {
            //przesuniecie o taki sam wektor [A, A]
            return Math.abs(this.row - row) == Math.abs(this.col - col);
        } else if (piece == Piece.KNIGHT) {
            if ((Math.abs(this.row - row) == 2 && Math.abs(this.col - col) == 1) ||
                    (Math.abs(this.row - row) == 1 && Math.abs(this.col - col) == 2)) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((this.row == row && this.col != col) ||
                    (this.row != row && this.col == col)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean checkType(String type){
        if(piece == Piece.PAWN && type.equals("P")){
            return true;
        } else  if(piece == Piece.KING && type.equals("K")){
            return true;
        } else  if(piece == Piece.QUEEN && type.equals("Q")){
            return true;
        } else  if(piece == Piece.BISHOP && type.equals("B")){
            return true;
        } else  if(piece == Piece.KNIGHT && type.equals("N")){
            return true;
        } else  if(piece == Piece.ROOK && type.equals("R")){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return piece +
                " " + color ;
    }

}
