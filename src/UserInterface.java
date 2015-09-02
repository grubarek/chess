/**
 * Created by Administrator on 2015-07-15.
 */

import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class UserInterface extends JPanel implements MouseListener, MouseMotionListener{
    static int x=0,y=0;
    static int squareSize=64;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.gray);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        for (int i=0;i<64;i+=2) {
            g.setColor(new Color(255,200,100));
            g.fillRect((i%8+(i/8)%2)*squareSize, (i/8)*squareSize, squareSize, squareSize);
            g.setColor(new Color(150,50,30));
            g.fillRect(((i+1)%8-((i+1)/8)%2)*squareSize, ((i+1)/8)*squareSize, squareSize, squareSize);
        }
       Image chessPiecesImage;
       chessPiecesImage=new ImageIcon("ChessPieces.png").getImage();


        for (int i=0;i<64;i++) {
            int j=-1,k=-1;
            switch (Parser.chessBoard[i/8][i%8].toString()) {
                case "[PAWN WHITE] ":j=5; k=0;
                    break;
                case "[PAWN BLACK] ": j=5; k=1;
                    break;
                case "[ROOK WHITE] ": j=2; k=0;
                    break;
                case "[ROOK BLACK] ": j=2; k=1;
                    break;
                case "[KNIGHT WHITE] ": j=4; k=0;
                    break;
                case "[KNIGHT BLACK] ": j=4; k=1;
                    break;
                case "[BISHOP WHITE] ": j=3; k=0;
                    break;
                case "[BISHOP BLACK] ": j=3; k=1;
                    break;
                case "[QUEEN WHITE] ": j=1; k=0;
                    break;
                case "[QUEEN BLACK] ": j=1; k=1;
                    break;
                case "[KING WHITE] ": j=0; k=0;
                    break;
                case "[KING BLACK] ": j=0; k=1;
                    break;
                //case "[f4]":j=0; k=1;
                //default:j=0; k=1;
            }
            if (j!=-1 && k!=-1) {
                g.drawImage(chessPiecesImage, (i%8)*squareSize, (i/8)*squareSize, (i%8+1)*squareSize, (i/8+1)*squareSize, j*64, k*64, (j+1)*64, (k+1)*64, this);
            }
        }
        /*g.setColor(Color.BLUE);
        g.fillRect(x-20, y-20, 40, 40);
        g.setColor(new Color(190,81,215));
        g.fillRect(40, 20, 80, 50);
        g.drawString("Jonathan", x, y);
        */
    }
    @Override
    public void mouseMoved(MouseEvent e) {x=e.getX();
        y=e.getY();
        repaint();}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}


}
