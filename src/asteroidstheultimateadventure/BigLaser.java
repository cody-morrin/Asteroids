package asteroidstheultimateadventure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class BigLaser
{
private AffineTransform identity = new AffineTransform();
    public Point laserStart;
    public Point laserEnd;
    private int timesDisplayed = 0;

    public BigLaser(Point laserStart, Point laserEnd)
    {
        this.laserStart = laserStart;
        this.laserEnd = laserEnd;
    }
    public void paintSelf(Graphics2D g2)
    {
        timesDisplayed ++;
        g2.setTransform(identity);
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(100));
        g2.drawLine(laserStart.x, laserStart.y, laserEnd.x, laserEnd.y);
    }

    public Point getLaserEnd()
    {
        return laserEnd;
    }

    public void setLaserEnd(Point laserEnd)
    {
        this.laserEnd = laserEnd;
    }

    public Point getLaserStart()
    {
        return laserStart;
    }

    public void setLaserStart(Point laserStart)
    {
        this.laserStart = laserStart;
    }

    public int getTimesDisplayed()
    {
        return timesDisplayed;
    }
}
