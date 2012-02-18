package asteroidstheultimateadventure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class MiniShipLaser
{
    private AffineTransform identity = new AffineTransform();
    private int miniShipx;
    private int miniShipy;
    Point laserStart, laserEnd;

    public MiniShipLaser(Point laserStart, Point laserEnd)
    {
        this.laserStart = laserStart;
        this.laserEnd = laserEnd;
    }

    void paintSelf(Graphics2D g2)
    {
        g2.setTransform(identity);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(250, 0, 250));
        g2.drawLine(laserStart.x, laserStart.y, laserEnd.x, laserEnd.y);
    }

    public void setMiniShipy(int miniShipy)
    {
        this.miniShipy = miniShipy;
    }

    public void setMiniShipx(int miniShipx)
    {
        this.miniShipx = miniShipx;
    }
}
