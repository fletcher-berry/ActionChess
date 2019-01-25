import java.awt.*;
import java.awt.geom.*;


public class Pawn
{
    private int[] square;
    private Color color;

    public Pawn(int[] square)
    {
        this.square = square;
    }

    public int[] getSquare()
    {
        return square;
    }

    public void setSquare(int[] newSquare)
    {
        square = newSquare;
    }

    public void draw(Graphics2D g2)
    {
        if(square == null)
            return;
        int x = square[0] * Square.SIDE_LENGTH;
        int y = square[1] * Square.SIDE_LENGTH;
        g2.setColor(Color.BLACK);
        Ellipse2D.Double top = new Ellipse2D.Double(x + 20, y + 12, 10, 10);
        g2.fill(top);
        Ellipse2D.Double mid = new Ellipse2D.Double(x + 17, y + 21, 16, 13);
        g2.fill(mid);
        Rectangle2D.Double rect1 = new Rectangle2D.Double(x + 20, y + 28, 10, 17);
        g2.fill(rect1);
        Rectangle2D.Double rect2 = new Rectangle2D.Double(x + 17, y + 35, 3, 10);
        g2.fill(rect2);
        Rectangle2D.Double rect3 = new Rectangle2D.Double(x + 30, y + 35, 3, 10);
        g2.fill(rect3);
        Rectangle2D.Double rect4 = new Rectangle2D.Double(x + 13, y + 39, 5, 6);
        g2.fill(rect4);
        Rectangle2D.Double rect5 = new Rectangle2D.Double(x + 32, y + 39, 5, 6);
        g2.fill(rect5);
    }
}
