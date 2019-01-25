import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Fletcher on 12/24/2016.
 */
public class Board extends JComponent
{
    private Square[][] board;
    private Knight knight;
    private ArrayList<Pawn> pawns;

    public Board()
    {
        pawns = new ArrayList<>();
        board = new Square[8][8];
        for(int j = 0; j < 8; j++)
        {
            for(int k = 0; k < 8; k++)
            {
                Color c;
                if((j + k) % 2 == 0)
                    c = Color.GRAY;
                else
                    c = Color.DARK_GRAY;
                board[j][k] = new Square(j * Square.SIDE_LENGTH, k * Square.SIDE_LENGTH, c);
                if(k < 4 && Math.random() < .14)
                {
                    Pawn p = new Pawn(new int[]{j, k});
                    pawns.add(p);
                }
            }
        }
        knight = new Knight(new int[]{(int)(Math.random() * 8), (int)(Math.random() * 4 + 4)});
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)(g);
        for(int j = 0; j < 8; j++)
        {
            for(int k = 0; k < 8; k++)
            {
                g2.setColor(board[j][k].getColor());
                board[j][k].draw(g2);
            }
        }
        for (Pawn p : pawns)
        {
            p.draw(g2);
        }
        knight.draw(g2);
    }

    public ArrayList<int[]> getLegalMoves()
    {
        ArrayList<int[]> list = new ArrayList<>();
        int row = knight.getSquare()[0];
        int col = knight.getSquare()[1];
        if(row + 2 <= 7 && col + 1 <= 7)
        {
            list.add(new int[]{row + 2, col + 1});
        }
        if(row + 1 <= 7 && col + 2 <= 7)
        {
            list.add(new int[]{row + 1, col + 2});
        }
        if(row + 2 <= 7 && col - 1 >= 0)
        {
            list.add(new int[]{row + 2, col - 1});
        }
        if(row + 1 <= 7 && col - 2 >= 0)
        {
            list.add(new int[]{row + 1, col - 2});
        }
        if(row - 1 >= 0 && col + 2 <= 7)
        {
            list.add(new int[]{row - 1, col + 2});
        }
        if(row - 2 >= 0 && col + 1 <= 7)
        {
            list.add(new int[]{row - 2, col + 1});
        }
        if(row - 1 >= 0 && col - 2 >= 0)
        {
            list.add(new int[]{row - 1, col - 2});
        }
        if(row - 2 >= 0 && col - 1 >= 0)
        {
            list.add(new int[]{row - 2, col - 1});
        }
        return list;
    }

    public int[] getSquare(int x, int y)
    {
        int newX = x / Square.SIDE_LENGTH;
        int newY = y / Square.SIDE_LENGTH;
        return new int[]{newX, newY};
    }

    public Knight getKnight() {
        return knight;
    }

    public ArrayList<Pawn> getPawns() {
        return pawns;
    }
}
