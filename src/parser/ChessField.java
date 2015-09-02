package parser;

/**
 * Created by Marcin on 2015-05-26.
 */
public class ChessField {
    ChessPiece piece;
    int row, col;
    Boolean isEmpty;

    public ChessField(int row, int col) {
        this.col = col;
        this.row = row;
        isEmpty = true;
    }

    public ChessField(int row, int col, ChessPiece piece) {
        this.col = col;
        this.row = row;
        isEmpty = false;
        this.piece = piece;
    }

    @Override
    public String toString() {
        if (isEmpty) {
            return "[" + Parser.boardColumns[col-1] +  row + "]";
        } else {
            return "[" +/* Parser.boardColumns[col-1] +  row + "]" + */piece.toString()+"] ";
        }
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        this.isEmpty = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
