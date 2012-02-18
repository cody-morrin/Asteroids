package asteroidstheultimateadventure;

//TODO ship has a crater where an asteroid hits it
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 *
 * @author Cody
 */
public class Ship
{
    public int shipX = 775;
    public int shipY = 475;
    Random randomTurrets = new Random();

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public int[] shipXPoints =
    {
        0, 1, 2, 3, 4, 5, 6, 5, 5, 14, 14, 9, 9, 14, 14, 5, 5, 4, 5, 4, 6, 4, 3, 2, 1, 0, -1, -2, -4, -3, -5, -4, -5, -4, -5, -5, -10, -14, -14, -9, -9, -14, -14, -5, -5, -6, -5, -4, -3, -2, -1, 0
    };
    public int[] shipYPoints =
    {
        21, 20, 18, 15, 11, 6, 5, 5, 2, 0, -1, -1, -5, -5, -6, -8, -10, -10, -14, -14, -18, -15, -18, -16, -19, -16, -20, -17, -20, -16, -18, -14, -14, -10, -10, -8, -7, -6, -5, -5, -1, -1, 0, 2, 5, 5, 6, 11, 15, 18, 20, 21
    };
    public Polygon shipShape = new Polygon(shipXPoints, shipYPoints, 52);
    public int[] flameXPoints =
    {
        4, 6, 4, 3, 2, 1, 0, -1, -2, -4, -3, -5, -4
    };
    public int[] flameYPoints =
    {
        -14, -18, -15, -18, -16, -19, -16, -20, -17, -20, -16, -18, -14
    };
    public Polygon flameShape = new Polygon(flameXPoints, flameYPoints, 13);
    public int[] windShieldXPoints =
    {
        0, 1, 2, -2, -1, 0
    };
    public int[] windShieldYPoints =
    {
        21, 20, 18, 18, 20, 21
    };
    public Polygon windShieldShape = new Polygon(windShieldXPoints, windShieldYPoints, 6);
    public int[] bodyXPoints =
    {
        2, 3, 4, 5, -5, -4, -3, -2, 2
    };
    public int[] bodyYPoints =
    {
        18, 15, 11, 6, 6, 11, 15, 18, 18
    };
    public Polygon bodyShape = new Polygon(bodyXPoints, bodyYPoints, 9);
    public int[] rightFinXPoints =
    {
        5, 6, 5, 5
    };
    public int[] rightFinYPoints =
    {
        6, 5, 5, 6
    };
    public Polygon rightFinShape = new Polygon(rightFinXPoints, rightFinYPoints, 4);
    public int[] leftFinXPoints =
    {
        -5, -5, -6, -5
    };
    public int[] leftFinYPoints =
    {
        6, 5, 5, 6
    };
    public Polygon leftFinShape = new Polygon(leftFinXPoints, leftFinYPoints, 4);
    public int[] topRightFinXPoints =
    {
        5, 10, 10, 5, 5
    };
    public int[] topRightFinYPoints =
    {
        2, 1, -1, -1, 2
    };
    public Polygon topRightFinShape = new Polygon(topRightFinXPoints, topRightFinYPoints, 5);
    public int[] topLeftFinXPoints =
    {
        -5, -10, -10, -5, -5
    };
    public int[] topLeftFinYPoints =
    {
        2, 1, -1, -1, 2
    };
    public Polygon topLeftFinShape = new Polygon(topLeftFinXPoints, topLeftFinYPoints, 5);
    public int[] bottomRightFinXPoints =
    {
        5, 10, 10, 5, 5
    };
    public int[] bottomRightFinYPoints =
    {
        -5, -5, -7, -8, -5
    };
    public Polygon bottomRightFinShape = new Polygon(bottomRightFinXPoints, bottomRightFinYPoints, 5);
    public int[] bottomLeftFinXPoints =
    {
        -5, -10, -10, -5, -5
    };
    public int[] bottomLeftFinYPoints =
    {
        -5, -5, -7, -8, -5
    };
    public Polygon bottomLeftFinShape = new Polygon(bottomLeftFinXPoints, bottomLeftFinYPoints, 5);
    public int[] rightDockingSationXPoints =
    {
        5, 9, 9, 5, 5
    };
    public int[] rightDockingSationYPoints =
    {
        -1, -1, -5, -5, -1
    };
    public Polygon rightDockingSation = new Polygon(rightDockingSationXPoints, rightDockingSationYPoints, 5);
    public int[] leftDockingSationXPoints =
    {
        -9, -5, -5, -9, -9
    };
    public int[] leftDockingSationYPoints =
    {
        -1, -1, -5, -5, -1
    };
    public Polygon leftDockingSation = new Polygon(leftDockingSationXPoints, leftDockingSationYPoints, 5);
    public int[] engineXPoints =
    {
        -4, 4, 5, -5, -4
    };
    public int[] engineYPoints =
    {
        -10, -10, -14, -14, -10
    };
    public Polygon engine = new Polygon(engineXPoints, engineYPoints, 5);

    public void paintSelf(Graphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setStroke(new BasicStroke(0.01f));
        g2.translate(shipX, shipY);
        g2.rotate(Math.toRadians(180));
        g2.scale(10, 10);
        g2.setColor(Color.CYAN);
        g2.fill(shipShape);
        g2.setColor(Color.red);
        g2.fill(flameShape);
        g2.setColor(Color.DARK_GRAY);
        g2.fill(windShieldShape);
        g2.fill(rightDockingSation);
        g2.fill(leftDockingSation);
        g2.fill(engine);
        g2.setColor(Color.white);
        g2.fill(bodyShape);
        g2.fill(rightFinShape);
        g2.fill(leftFinShape);
        g2.fill(topRightFinShape);
        g2.fill(topLeftFinShape);
        g2.fill(bottomRightFinShape);
        g2.fill(bottomLeftFinShape);
        g2.scale(.1f, .1f);
        g2.rotate(Math.toRadians(180));
        g2.setColor(Color.black);
        g2.fillOval(width - 950, height - 950, 700, 700); //black hole
        g2.fillOval(37, 39, 10, 10);//m u r turrets
        g2.fillOval(37, 10, 10, 10);//m d r
        g2.fillOval(-48, 10, 10, 10);//m u l
        g2.fillOval(-48, 39, 10, 10);//m d l
        g2.fillOval(-48, 87, 10, 10);//d l
        g2.fillOval(37, 87, 10, 10);//d r
        g2.fillOval(37, -58, 10, 10);//u r
        g2.fillOval(-48, -58, 10, 10);//u l
        g2.translate(-800, -450);
    }

    public Point getTurretPosition()
    {
        int random = randomTurrets.nextInt(8) + 1;
        switch(random)
        {
            case 1: return new Point(shipX - 48, shipY - 58);
            case 2: return new Point(shipX + 37, shipY - 58);
            case 3: return new Point(shipX + 37, shipY + 87);
            case 4: return new Point(shipX - 48, shipY + 87);
            case 5: return new Point(shipX - 48, shipY + 39);
            case 6: return new Point(shipX - 48, shipY + 10);
            case 7: return new Point(shipX + 37, shipY + 10);
            case 8: return new Point(shipX + 37, shipY + 39);
            default: return new Point(shipX - 48, shipY - 58);
        }
    }

    public int getShipX()
    {
        return shipX;
    }

    public int getShipY()
    {
        return shipY;
    }


}
