/**
 * Created by Marcin on 2015-05-26.
 */

import body.Scaner;
import parser.Parser;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String input;
        input = "Pa2a3Pg7g6";
        int i;
        i = 0;

        List<Scaner.Token> tokens = Scaner.lex(input);
        System.out.println("wejscie: " + input);

//        for (Scaner.Token t : tokens) {
//            System.out.println("Token nr:" + (i + 1) + "       " + input.charAt(i) + " : " + t);
//            i++;
//        }

        Parser parser = new Parser();
        parser.printBoard();
        parser.startParsing(tokens);

        JFrame f=new JFrame("Chess");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface ui=new UserInterface();
        f.add(ui);
        f.setSize(850, 650);
        f.setVisible(true);






    }

}
