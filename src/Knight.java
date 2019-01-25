import java.awt.*;
import java.awt.geom.*;

public class Knight
{
    private int[] square;
    private Color color;

    public Knight(int[] square)
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
        int x1 = square[0] * Square.SIDE_LENGTH;
        int y1 = square[1] * Square.SIDE_LENGTH;
        g2.setColor(Color.WHITE);
        Rectangle2D.Double rect1 = new Rectangle2D.Double(x1 + 16, y1 + 42, 27, 2);
        g2.fill(rect1);
        Rectangle2D.Double rect2 = new Rectangle2D.Double(x1 + 28, y1 + 34, 15, 8);
        g2.fill(rect2);
        Ellipse2D.Double ell1 = new Ellipse2D.Double(x1 + 18, y1 + 40, 8, 4);				//  left
        g2.fill(ell1);
        Ellipse2D.Double ell2 = new Ellipse2D.Double(x1 + 21, y1 + 37, 10, 6);				//  left
        g2.fill(ell2);
        Ellipse2D.Double ell3 = new Ellipse2D.Double(x1 + 23, y1 + 32, 6, 10);				// left
        g2.fill(ell3);
        Ellipse2D.Double ell4 = new Ellipse2D.Double(x1 + 36, y1 + 24, 7, 19);				//  right
        g2.fill(ell4);
        Ellipse2D.Double ell5 = new Ellipse2D.Double(x1 + 34, y1 + 21, 8, 14);				// right
        g2.fill(ell5);
        Ellipse2D.Double ell6 = new Ellipse2D.Double(x1 + 26, y1 + 26, 10, 10);				// left
        g2.fill(ell6);
        Ellipse2D.Double ell7 = new Ellipse2D.Double(x1 + 26, y1 + 18, 13, 7);				// right
        g2.fill(ell7);
        Ellipse2D.Double ell8 = new Ellipse2D.Double(x1 + 22, y1 + 16, 15, 6);				// right
        g2.fill(ell8);
        int[] x = {x1 + 22, x1 + 10, x1 + 14, x1 + 29};
        int[] y = {y1 + 18, y1 + 30, y1 + 33, y1 + 26};
        Polygon p = new Polygon(x, y, 4);																//  nose
        g2.fill(p);
        Ellipse2D.Double ell9 = new Ellipse2D.Double(x1 + 23, y1 + 13, 13, 8);				// top
        g2.fill(ell9);
        Rectangle2D.Double rect3 = new Rectangle2D.Double(x1 + 26, y1 + 24, 10, 8);
        g2.fill(rect3);
        g2.setColor(Color.BLACK);
        Ellipse2D.Double ell10 = new Ellipse2D.Double(x1 + 22, y1 + 19, 5, 2);
        g2.fill(ell10);
        Rectangle2D.Double rect4 = new Rectangle2D.Double(x1 + 25, y1 + 21, 2, 2);
        g2.fill(rect4);
        Line2D.Double line1 = new Line2D.Double(x1 + 26, y1 + 28, x1 + 32, y1 + 23);
        g2.draw(line1);
        Line2D.Double line2 = new Line2D.Double(x1 + 12, y1 + 31, x1 + 16, y1 + 28);
        g2.draw(line2);
        int[] x2 = {x1 + 24, x1 + 31, x1 + 28};
        int[] y2 = {y1 + 15, y1 + 17, y1 + 8};
        Polygon p2 = new Polygon(x2, y2, 3);
        g2.fill(p2);
        int[] x3 = {x1 + 20, x1 + 28, x1 + 21};
        int[] y3 = {y1 + 18, y1 + 15, y1 + 10};
        Polygon p3 = new Polygon(x3, y3, 3);
        g2.fill(p3);
        g2.setColor(Color.WHITE);
        Line2D.Double line5 = new Line2D.Double(x1 + 23, y1 + 17, x1 + 25, y1 + 14);
        g2.draw(line5);
    }
}