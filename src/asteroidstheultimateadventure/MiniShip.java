package asteroidstheultimateadventure;

//TODO automated miniship
//TODO miniship dies
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class MiniShip
{

    boolean triangleShow = false;
    AffineTransform identityTransform = new AffineTransform();
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    AffineTransform shipAffineTransform = new AffineTransform();
    int miniShipX;
    int miniShipY;
    int miniShipAngle;
    int xToMove = 0;
    int yToMove = 0;
    private int kills = 0;
    boolean miniShipAlive = true;
    public int[] miniShipXPoints =
    {
        2, 2, 8, 8, 2, 2, -2, -2, -8, -8, -2, -2
    };
    public int[] miniShipYPoints =
    {
        -7, -2, 0, 2, 2, 4, 4, 2, 2, 0, -2, -7
    };
    private Polygon miniShipShape = new Polygon(miniShipXPoints, miniShipYPoints, miniShipXPoints.length);
    public int[] miniShipCockpitXPoints =
    {
        2, 1, 0, -1, -2
    };
    public int[] miniShipCockpitYPoints =
    {
        -7, -9, -10, -9, -7
    };
    public int[] collisionTriXPoints =
    {
        0, 12, -12
    };
    public int[] collisionTriYPoints =
    {
        0, -100, -100
    };
    public Polygon miniShipCockpitShape = new Polygon(miniShipCockpitXPoints, miniShipCockpitYPoints, miniShipCockpitXPoints.length);
    Area miniShipArea;
    Color miniShipColor;
    private Polygon laserRangeShape = new Polygon(collisionTriXPoints, collisionTriYPoints, collisionTriXPoints.length);
    private Area laserRangeArea;

    public MiniShip(Color color, int startX, int startY, int startAngle)
    {
        miniShipColor = color;
        miniShipX = startX;
        miniShipY = startY;
        miniShipAngle = startAngle;
    }

    public void paintSelf(Graphics2D g2)
    {
        if (miniShipAlive)
        {
            miniShipArea = new Area(miniShipShape);
            laserRangeArea = new Area(laserRangeShape);
            g2.setTransform(identityTransform); //Resets everything to 0.
            g2.setStroke(new BasicStroke(0.01f));
            g2.translate(miniShipX, miniShipY);
            g2.rotate(Math.toRadians(miniShipAngle));
            g2.scale(2, 2);
            shipAffineTransform = g2.getTransform();
            miniShipArea.transform(shipAffineTransform);
            g2.setColor(miniShipColor);
            g2.fill(getMiniShipShape());
            g2.setColor(Color.BLACK);
            g2.draw(getMiniShipShape());
            g2.draw(miniShipCockpitShape);
            g2.setColor(Color.GRAY);
            g2.fill(miniShipCockpitShape);
            g2.setColor(Color.GREEN);
            AffineTransform laserRangeAffineTransform = new AffineTransform();
            laserRangeArea = laserRangeArea.createTransformedArea(laserRangeAffineTransform);
            g2.setTransform(identityTransform);
            g2.setColor(Color.RED);
            laserRangeArea.transform(shipAffineTransform);
            g2.setColor(Color.GREEN);
            g2.draw(laserRangeArea);
            g2.setTransform(identityTransform);
        }
    }

    public int getMiniShipX()
    {
        return miniShipX;
    }

    public void setMiniShipX(int miniShipX)
    {
        if (miniShipAlive)
        {
            this.miniShipX = miniShipX;
        }
    }

    public int getMiniShipY()
    {
        return miniShipY;
    }

    public void setMiniShipY(int miniShipY)
    {
        if (miniShipAlive)
        {
            this.miniShipY = miniShipY;
        }
    }

    public int getMiniShipAngle()
    {
        return miniShipAngle;
    }

    public void setMiniShipAngle(int miniShipAngle)
    {
        this.miniShipAngle = miniShipAngle;
    }

    public int getxToMove()
    {
        return xToMove;
    }

    public void setxToMove(int xToMove)
    {
        this.xToMove = xToMove;
    }

    public int getyToMove()
    {
        return yToMove;
    }

    public void setyToMove(int yToMove)
    {
        this.yToMove = yToMove;
    }

    public Polygon getMiniShipShape()
    {
        return miniShipShape;
    }

    public int getKills()
    {
        return kills;
    }

    public void setKills(int kills)
    {
        this.kills = kills;
    }

    public void showTriangle()
    {
        triangleShow = true;
    }

    public Polygon getLaserRangeShape()
    {
        return laserRangeShape;
    }

    public Area getLaserRangeArea()
    {
        return laserRangeArea;
    }
}
