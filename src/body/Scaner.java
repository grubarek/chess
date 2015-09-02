package body;

/**
 * Created by Marcin on 2015-05-26.
 */
import java.util.List;
import java.util.ArrayList;


public class Scaner {
    public static enum Type {

        ZAPIS,
        COLUMN_ID,
        ROW_ID,
        RUCH,
        ZERO,
        MYSLNIK,
        BIERKA,
        POZYCJA_START,
        POZYCJA_END,
        MOZLIWY_RUCH,
        SKOS,
        W_PIONIE,
        W_POZIOMIE,
        RUCH_KING,
        RUCH_QUEEN,
        RUCH_ROOK,
        RUCH_KNIGHT,
        RUCH_BISHOP,
        RUCH_PAWN,
        SKOS_LG,
        SKOS_PG,
        SKOS_LD,
        SKOS_PD,
        BICIE,
        PROMOCIJA,
        SZACH,
        MAT,
        INNE,
        PION,
        EOF,
        COMMENT;
    }

    public static class Token {
        public final Type t;
        public final String c;
        public Token(Type t, String c) {
            this.t = t;
            this.c = c;
        }

        public String toString() {
            if (t == Type.COMMENT) {
                return "blad skladni, brak tokena<" + c + ">";
            }
            return t.toString();
        }
    }

    /*
     * Given a String, and an index, get the atom starting at that index
     */
    public static String getAtom(String s, int i) {
        int j = i;
        for (; j < s.length(); ) {
            if (Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);
    }

    public static List<Token> lex(String input) {
        List<Token> result = new ArrayList<Token>();
        for (int i = 0; i < input.length(); ) {
            switch (input.charAt(i)) {
                case 'K':
                    result.add(new Token(Type.BIERKA, "K"));
                    i++;
                    break;
                case 'Q':
                    result.add(new Token(Type.BIERKA, "Q"));
                    i++;
                    break;
                case 'R':
                    result.add(new Token(Type.BIERKA, "R"));
                    i++;
                    break;
                case 'N':
                    result.add(new Token(Type.BIERKA, "N"));
                    i++;
                    break;
                case 'B':
                    result.add(new Token(Type.BIERKA, "B"));
                    i++;
                    break;
                case 'P':
                    result.add(new Token(Type.BIERKA, "P"));
                    i++;
                    break;
                case 'a':
                    result.add(new Token(Type.COLUMN_ID, "a"));
                    i++;
                    break;
                case 'b':
                    result.add(new Token(Type.COLUMN_ID, "b"));
                    i++;
                    break;
                case 'c':
                    result.add(new Token(Type.COLUMN_ID, "c"));
                    i++;
                    break;
                case 'd':
                    result.add(new Token(Type.COLUMN_ID, "d"));
                    i++;
                    break;
                case 'e':
                    result.add(new Token(Type.COLUMN_ID, "e"));
                    i++;
                    break;
                case 'f':
                    result.add(new Token(Type.COLUMN_ID, "f"));
                    i++;
                    break;
                case 'g':
                    result.add(new Token(Type.COLUMN_ID, "g"));
                    i++;
                    break;
                case 'h':
                    result.add(new Token(Type.COLUMN_ID, "h"));
                    i++;
                    break;
                case '1':
                    result.add(new Token(Type.ROW_ID, "1"));
                    i++;
                    break;
                case '2':
                    result.add(new Token(Type.ROW_ID, "2"));
                    i++;
                    break;
                case '3':
                    result.add(new Token(Type.ROW_ID, "3"));
                    i++;
                    break;
                case '4':
                    result.add(new Token(Type.ROW_ID, "4"));
                    i++;
                    break;
                case '5':
                    result.add(new Token(Type.ROW_ID, "5"));
                    i++;
                    break;
                case '6':
                    result.add(new Token(Type.ROW_ID, "6"));
                    i++;
                    break;
                case '7':
                    result.add(new Token(Type.ROW_ID, "7"));
                    i++;
                    break;
                case '8':
                    result.add(new Token(Type.ROW_ID, "8"));
                    i++;
                    break;
                case '#':
                    result.add(new Token(Type.MAT, "#"));
                    i++;
                    break;
                case '+':
                    result.add(new Token(Type.SZACH, "+"));
                    i++;
                    break;
                case 'x':
                    result.add(new Token(Type.BICIE, "x"));
                    i++;
                    break;
                case '-':
                    result.add(new Token(Type.MYSLNIK, "-"));
                    i++;
                    break;
                case '0':
                    result.add(new Token(Type.ZERO, "0"));
                    i++;
                    break;
                case 'q':
                    result.add(new Token(Type.PROMOCIJA, "q"));
                    i++;
                    break;
                case 'r':
                    result.add(new Token(Type.PROMOCIJA, "r"));
                    i++;
                    break;
                case 'n':
                    result.add(new Token(Type.PROMOCIJA, "n"));
                    i++;
                    break;
                default:
                    if (Character.isWhitespace(input.charAt(i))) {
                        i++;
                    } else {
                        String atom = getAtom(input, i);
                        i += atom.length();
                        result.add(new Token(Type.COMMENT, atom));
                    }
                    break;
            }
        }
        return result;
    }
}

