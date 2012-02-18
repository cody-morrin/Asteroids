package asteroidstheultimateadventure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class LaserS
{
    private AffineTransform identity = new AffineTransform();
    public Point laserStart = new Point(100, 100);
    public Point laserEnd = new Point(500, 500);
    private int timesDisplayed = 0;

    public LaserS(Point laserStart, Point laserEnd)
    {
        this.laserStart = laserStart;
        this.laserEnd = laserEnd;
    }
    public LaserS()
    {
        
    }
    
    public void paintSelf(Graphics2D g2)
    {
        timesDisplayed ++;
        g2.setTransform(identity);
        laserStart = new Ship().getTurretPosition();
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(5));
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