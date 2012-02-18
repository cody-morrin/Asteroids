/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidstheultimateadventure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;

/**
 *
 * @author Cody
 */
public class Painter extends JComponent implements KeyListener
{

    private BigLaser bigBigLaser;
    private String asteroidTypedText = "";
    public ArrayList<LaserS> laserList = new ArrayList<LaserS>();
    public ArrayList<MiniShipLaser> miniLaserList = new ArrayList<MiniShipLaser>();
    private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    int x, y = 0;
    int planetWidth = 700;
    int planetHeight = 700;
    private StarS star;
    private Ship ship;
    Random randomTwo = new Random();
    private Asteroid asteroid;
    private int solarFlareArc = 0;
    private boolean makeAsolarFlare = false;
    private MiniShipLaser miniShipLaser;
    int[] sunspotXPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    int[] sunspotYPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    Polygon sunspotShape = new Polygon(sunspotXPoints, sunspotYPoints, 3);
    int[] sunspot2XPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    int[] sunspot2YPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    Polygon sunspot2Shape = new Polygon(sunspot2XPoints, sunspot2YPoints, 3);
    int[] sunspot3XPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    int[] sunspot3YPoints =
    {
        randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
    };
    Polygon sunspot3Shape = new Polygon(sunspot3XPoints, sunspot3YPoints, 3);
    LaserS laser = new LaserS(new Point(500, 500), new Point(2000, 2000));
    Graphics2D g2;
    private MiniShip miniShip;
    private MiniShip miniShip2;
    Image explosionImage;
    AffineTransform t = new AffineTransform();
    Font f = new Font("Copperplate", Font.BOLD, 48);
    Font f2 = new Font("Paskowy", Font.BOLD, 48);

    @Override
    public void paintComponent(Graphics g)
    {
        g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.fillRect(0, 0, width, height);//rectangle as big as screen


        g2.setTransform(t);
        g2.translate(width - 300, 300);
        g2.setColor(Color.ORANGE);//color for solar flare
        g2.setStroke(new BasicStroke(4f));
        g2.rotate(Math.toRadians(-40));
        if (makeAsolarFlare == true)
        {
            g2.drawArc(-100, -100, 300, 100, 0, solarFlareArc);//solar flare
        }
        solarFlareArc++;

        if (solarFlareArc > 360)
        {
            makeAsolarFlare = false;
        }
        g2.setTransform(t);
        ship.paintSelf(g2);
        miniShip.paintSelf(g2);
        miniShip2.paintSelf(g2);
        if (!miniShip.miniShipAlive)
        {
            drawExplosion(miniShip.getMiniShipX(), miniShip.getMiniShipY());
        }
        if (!miniShip2.miniShipAlive)
        {
            drawExplosion(miniShip2.getMiniShipX(), miniShip2.getMiniShipY());
        }
        g2.setColor(Color.GREEN);
        g2.fillOval(-500, -500, planetWidth, planetHeight);
        g2.setColor(Color.orange);
        g2.fillOval(width - 300, -500, 800, 800);//sun
        g2.setColor(Color.RED);
        g2.fillOval(-450, height - 400, 600, 600);//planet
        g2.setTransform(t);
        g2.translate(width - 50, 20);
        g2.scale(.3f, .3f);
        g2.setColor(Color.black);
        g2.fill(sunspotShape);
        g2.setTransform(t);
        g2.translate(width - 200, 40);
        g2.scale(.3f, .3f);
        g2.fill(sunspot2Shape);
        g2.setTransform(t);
        g2.translate(width - 270, 100);
        g2.scale(.3f, .3f);
        g2.fill(sunspot3Shape);
        //g2.translate(-(width - 50), -(height - 20));
        for (int i = 0; i < asteroidList.size(); i++)
        {
            asteroidList.get(i).paintSelf(g2);
            if (asteroidList.get(i).getAsteroidXposition() > width && asteroidList.get(i).getAsteroidYposition() > height)
            {
                asteroidList.remove(i);
            }
        }
        for (int a = 0; a < laserList.size(); a++)
        {
            laserList.get(a).paintSelf(g2);
            if (laserList.get(a).getTimesDisplayed() >= 1)
            {
                laserList.remove(a);
                a--;
            }
        }
        for (int a = 0; a < miniLaserList.size(); a++)
        {
            miniLaserList.get(a).paintSelf(g2);
            miniLaserList.remove(a);
            a--;
        }
        if (bigBigLaser != null)
        {
            bigBigLaser.paintSelf(g2);
            if (bigBigLaser.getTimesDisplayed() >= 10)
            {
                bigBigLaser = null;
            }
        }

        g2.setTransform(t);
        g2.setColor(Color.black);
        g2.fillRect(width - 198, 19, 170, 26);
        g2.setColor(Color.GREEN);
        g2.setFont(f);
        g2.drawString(asteroidTypedText, width - 200, 45);
        String scoreString = "Pink has " + miniShip.getKills() + " kills   Blue has " + miniShip2.getKills() + " kills";
        g2.setFont(f2);
        g2.drawString(scoreString, 600, 60);
    }

    private void drawExplosion(int x, int y)
    {
        g2.translate(x, y);
        g2.scale(.2, .2);
        g2.drawImage(explosionImage, null, this);
        g2.scale(5, 5);
        g2.translate(-x, -y);
        repaint();
    }

    public void setExplosionImage(Image explosionImage)
    {
        this.explosionImage = explosionImage;
    }

    public void setShip(Ship aship)
    {
        ship = aship;
    }

    public void setStar(StarS star)
    {
        this.star = star;
    }

    public void setAsteroid(Asteroid anAsteroid)
    {
        asteroid = anAsteroid;
    }

    public void setMakeASolarFlare(boolean makeAsolarFlare)
    {
        this.makeAsolarFlare = makeAsolarFlare;
    }

    /**
     * @param solarFlareArc the solarFlareArc to set
     */
    public void setSolarFlareArc(int solarFlareArc)
    {
        this.solarFlareArc = solarFlareArc;
    }

    public void keyTyped(KeyEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            laser.paintSelf(g2);
        }
    }

    public void keyReleased(KeyEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Graphics2D getG2()
    {
        return g2;
    }

    public void setLaserList(ArrayList<LaserS> laserList)
    {
        this.laserList = laserList;
    }

    public void setMiniLaserList(ArrayList<MiniShipLaser> miniLaserList)
    {
        this.miniLaserList = miniLaserList;
    }

    public ArrayList<Asteroid> getAsteroidList()
    {
        return asteroidList;
    }

    public void setAsteroidList(ArrayList<Asteroid> asteroidList)
    {
        this.asteroidList = asteroidList;
    }

    public String getAsteroidTypedText()
    {
        return asteroidTypedText;
    }

    public void setAsteroidTypedText(String asteroidTypedText)
    {
        this.asteroidTypedText = asteroidTypedText;
    }

    public void setBigBigLaser(BigLaser bigBigLaser)
    {
        this.bigBigLaser = bigBigLaser;
    }

    public void setMiniShip(MiniShip miniShip)
    {
        this.miniShip = miniShip;
    }

    public void setMiniShip2(MiniShip miniShip)
    {
        this.miniShip2 = miniShip;
    }

    /**
     * @param miniShipLaser the miniShipLaser to set
     */
    public void setMiniShipLaser(MiniShipLaser miniShipLaser)
    {
        this.miniShipLaser = miniShipLaser;
    }
}
