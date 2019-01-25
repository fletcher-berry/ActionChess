import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Fletcher on 12/24/2016.
 */
public class ChessRunner
{

    private Board board;
    private boolean playing;
    private int pawnsCaptured;

    public ChessRunner()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(8 * Square.SIDE_LENGTH + 15, 8 * Square.SIDE_LENGTH + 48);
        frame.setLocation(400, 250);
        board = new Board();
        frame.add(board);
        frame.setVisible(true);
        playing = true;
        pawnsCaptured = 0;
    }

    /*
     * determine if a location is in a list of locations
     * @param list a list of locations with each location represented as an int array of 2 elements
     * @param loc the location in question
     */
    public static boolean contains(ArrayList<int[]> list, int[] loc)
    {
        for (int[] a: list)
        {
            if(a[0] == loc[0] && a[1] == loc[1])
                return true;
        }
        return false;
    }

    public void run()
    {
        /* This class is used to move the knight to the square the user clicks on
         * if the knight can move there
         */
        class ClickListener implements MouseListener
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if(!playing)
                    return;
                int x = e.getX();
                int y = e.getY();
                int[] square = board.getSquare(x, y);
                // if knight can play to the clicked square, move there
                if(contains(board.getLegalMoves(), square))
                {
                    board.getKnight().setSquare(square);
                    Iterator<Pawn> iter = board.getPawns().iterator();
                    // if pawn on the square remove it
                    while(iter.hasNext())
                    {
                        Pawn p = iter.next();
                        int[] s = p.getSquare();
                        if(s[0] == square[0] && s[1] == square[1])
                        {
                            iter.remove();
                            pawnsCaptured++;
                        }
                    }
                    board.paintImmediately(0, 0, 450, 450);
                    // check if any pawn can capture the knight
                    for(Pawn p: board.getPawns())
                    {
                        int[] s = p.getSquare();
                        if(s[1] == square[1] - 1 && Math.abs(s[0] - square[0]) == 1)
                        {
                            long time = System.currentTimeMillis();
                            // wait 0.25 seconds before capturing
                            while(true)
                            {
                                if(System.currentTimeMillis() > time + 250)
                                    break;
                            }
                            p.setSquare(square);
                            board.getKnight().setSquare(null);
                            board.repaint();
                            endGame();
                        }
                    }
                }
            }


            public void mouseClicked(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        }

        MouseListener mouse = new ClickListener();
        board.addMouseListener(mouse);

        class TimeListener implements ActionListener
        {
            @Override
            /*
             * advance all pawns at fixed intervals
             */
            public void actionPerformed(ActionEvent e)
            {
                if(!playing)
                    return;
                boolean otherSide = false;
                for(Pawn p : board.getPawns())
                {
                    int file = p.getSquare()[0];
                    int rank = p.getSquare()[1];
                    // don't move pawn if blockaded by knight
                    if(board.getKnight().getSquare()[0] != file || board.getKnight().getSquare()[1] - 1 != rank)
                    {
                        if(board.getKnight().getSquare()[0] == file)
                        {
                            int f = file;
                            int r = rank + 1;
                            boolean empty = false;
                            // if a chain of pawns on a file are blockaded by the knight, don't move them, otherwise do move them
                            while(!empty)
                            {
                                if(r == 8)
                                    break;
                                if(board.getKnight().getSquare()[0] == f && board.getKnight().getSquare()[1] == r)
                                {
                                    break;
                                }
                                empty = true;
                                for (Pawn pawn : board.getPawns())
                                {
                                    if(pawn.getSquare()[0] == f && pawn.getSquare()[1] == r)
                                    {
                                        empty = false;
                                    }
                                }
                                r++;
                            }
                            if(empty)
                            {
                                // advance the pawn.  If it reaches the other side, the game ends.
                                p.setSquare(new int[]{file, rank + 1});
                                if(rank == 6)
                                {
                                    otherSide = true;
                                }
                            }
                        }
                        else
                        {
                            p.setSquare(new int[]{file, rank + 1});
                            if(rank == 6)
                            {
                                otherSide = true;
                            }
                        }
                    }
                }
                // add new pawns on the far side with 20% probability for each file
                for(int k = 0; k < 8; k++)
                {
                    if(Math.random() < .2)
                    {
                        if(board.getKnight().getSquare()[0] != k || board.getKnight().getSquare()[1] != 0)
                            board.getPawns().add(new Pawn(new int[]{k, 0}));
                    }
                }
                board.repaint();
                if(otherSide)
                    endGame();
            }
        }
        ActionListener a = new TimeListener();
        Timer time = new Timer(3000, a);
        time.start();
        while(playing)
        {

        }
        time.stop();
    }

    public void endGame()
    {
        if(playing)
        {
            board.paintImmediately(0, 0, 450, 450); // if pawn reaches other side, make sure this is shown before showing message.
            playing = false;
            JOptionPane.showMessageDialog(null, "game over.  You captured " + pawnsCaptured + " pawns.");
        }

    }
}
