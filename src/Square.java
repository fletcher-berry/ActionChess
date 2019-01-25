import java.awt.*;
import java.awt.geom.*;

/**
 * Created by Fletcher on 12/24/2016.
 */
public class Square
{
    private Color color;
    private int xCoord;
    private int yCoord;

    public static final int SIDE_LENGTH = 50;

    public Square(int x, int y, Color c)
    {
        xCoord = x;
        yCoord = y;
        color = c;
    }

    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double rect = new Rectangle2D.Double(xCoord, yCoord, SIDE_LENGTH, SIDE_LENGTH);
        g2.setColor(color);
        g2.fill(rect);
    }

    public Color getColor() {
        return color;
    }
}
