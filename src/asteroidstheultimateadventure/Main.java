//Copyright © Cody Morrin 2010-2012
package asteroidstheultimateadventure;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main implements ActionListener
{
    AffineTransform identity = new AffineTransform();
    private int asteroidNum = 0;
    public SpacE space;
    public Ship ship;
    public MiniShip miniShip;
    public MiniShip miniShip2;
    public Painter painter;
    public StarS star;
    public Asteroid asteroid;
    public ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
    public ArrayList<LaserS> laserList = new ArrayList<LaserS>();
    public ArrayList<MiniShipLaser> miniLaserList = new ArrayList<MiniShipLaser>();
    public ArrayList<MiniShip> miniShipList = new ArrayList<MiniShip>();
    Timer asteroidTimer;
    Timer addAsteroidTimer;
    Timer collisionTimer;
    Timer paintTimer;
    Main main;
    Timer solarFlareTimer;
    Timer laserSTimer;
    URL laserFile = this.getClass().getResource("laser.wav");
    AudioClip laserSound = Applet.newAudioClip(laserFile);
    URL explosionAddress = this.getClass().getResource("transparent_explosion.png");
    Image explosionImage;
    Timer miniShipSpeedTimer;
    Area overlapArea;
    boolean isUpPressed;
    boolean minishipAsteroidCollision = false;
    private double miniShipMovementSpeed = 3.9;
    AffineTransform asteroidAffineTransform;
    URL backgroundAddress = this.getClass().getResource("space.png");
    Image backgroundImage;
    static boolean canStart = false;

    public static void main(String[] args)
    {
        new Main().getGoing();
    }

    private void getGoing()
    {
        JOptionPane.showMessageDialog(null, "Use awrow keys to control the pink ship. Use WASD to control the blue ship.\nMove the green triangle over an asteroid to destroy it.\nType any number, then press enter to destroy the corresponding asteroid.", "Asteroid Game", JOptionPane.PLAIN_MESSAGE);
        main = new Main();
        star = new StarS();
        painter = new Painter();
        ship = new Ship();
        miniShip = new MiniShip(Color.PINK, 710, 504, 270);
        miniShip2 = new MiniShip(Color.CYAN, 839, 504, 90);
        miniShipList.add(miniShip);
        miniShipList.add(miniShip2);
        asteroid = new Asteroid();
        addAsteroidTimer = new Timer(250, this);
        addAsteroidTimer.start();
        asteroid.setAsteroidList(asteroidList);
        space = new SpacE();
        painter.setStar(star);
        painter.setShip(ship);
        painter.setMiniShip(miniShip);
        painter.setMiniShip2(miniShip2);
        painter.setAsteroid(asteroid);
        space.setPainter(painter);
        space.makeSpace();
        space.setMiniShip(miniShip);
        try
        {
            explosionImage = ImageIO.read(explosionAddress);
            painter.setExplosionImage(explosionImage);
            backgroundImage = ImageIO.read(backgroundAddress);
            painter.setbackgroundImage(backgroundImage);
        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        asteroidTimer = new Timer(30, asteroid);
        asteroidTimer.start();

        collisionTimer = new Timer(20, this);
        collisionTimer.start();

        solarFlareTimer = new Timer(30000, this);
        solarFlareTimer.start();

        laserSTimer = new Timer(250, this);
        laserSTimer.start();

        paintTimer = new Timer(20, this);
        paintTimer.start();

        miniShipSpeedTimer = new Timer(20, this);
        miniShipSpeedTimer.start();
    }

    public boolean intersects(Area area1, Area area2)
    {
        Area area1clone = (Area) area1.clone();
        area1clone.intersect(area2);
        if (!area1clone.isEmpty())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void removeAsteroidByNumber() throws InterruptedException
    {
        ArrayList<String> numbers = space.getNumbersToRemove();
        for (int j = 0; j < numbers.size(); j++)
        {
            String number = numbers.get(j);
            for (int i = 0; i < asteroidList.size(); i++)
            {
                if (asteroidList.get(i).getAsteroidNumber().equals(number))
                {
                    Asteroid temp = asteroidList.get(i);
                    BigLaser laser = new BigLaser(new Point(temp.getAsteroidXposition(), 0), new Point(temp.getAsteroidXposition(), temp.getAsteroidYposition()));
                    painter.setBigBigLaser(laser);

                    asteroidList.get(i).setIsGoingToDie(true);
                }
            }
        }
        space.clearAsteroidNumbers();
    }

    public boolean collision(Area area1, Area area2)
    {
        Area area1clone = (Area) area1.clone();
        area1clone.intersect(area2);
        if (!area1clone.isEmpty())
        {
            return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e)
    {
        try
        {
            removeAsteroidByNumber();
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (e.getSource().equals(addAsteroidTimer))
        {
            Asteroid asteroid = new Asteroid();
            asteroid.setAsteroidNumber(Integer.toString(asteroidNum));
            asteroidNum++;
            asteroid.setDimensions();
            asteroidList.add(asteroid);
        }
        if (e.getSource().equals(solarFlareTimer))
        {
            painter.setMakeASolarFlare(true);
            painter.setSolarFlareArc(0);
        }

        if (e.getSource().equals(laserSTimer) && asteroidList.size() > 0)
        {
            int astIndex = nearestAsteroid();
            Asteroid closestAsteroid = asteroidList.get(astIndex);
            LaserS laser = new LaserS(ship.getTurretPosition(), new Point(closestAsteroid.getAsteroidXposition(), closestAsteroid.getAsteroidYposition()));
            laserList.add(laser);
            painter.setLaserList(laserList);
            if (closestAsteroid.damage == 0)
            {

                closestAsteroid.setColor(Color.BLUE);


                closestAsteroid.damage = 1;
            } else
            {
                asteroidList.remove(closestAsteroid);
            }
            painter.setAsteroidList(asteroidList);
            laserSound.play();
        }

        if (e.getSource().equals(paintTimer))
        {
            painter.repaint();
            for (int i = 0; i < miniShipList.size(); i++)  // loops through miniships
            {
                AffineTransform identity = new AffineTransform();
                identity.translate(miniShipList.get(i).getMiniShipX(), miniShipList.get(i).getMiniShipY());
                Area miniShipArea = new Area(miniShipList.get(i).getMiniShipShape());
                miniShipArea.transform(identity);//it knows where it is
                int closestAstIndex = nearestAsteroidToMiniShip(miniShipList.get(i));
                identity = new AffineTransform();
                if (asteroidList.size() > 0)
                {
                    identity.translate(asteroidList.get(closestAstIndex).getAsteroidXposition(), asteroidList.get(closestAstIndex).getAsteroidYposition());
                    Area astArea = new Area(asteroidList.get(closestAstIndex).asteroidShape);
                    astArea.transform(identity);
                    if (intersects(miniShipArea, astArea))  // destroy ship if intersects with closest asteroid
                    {
                        minishipAsteroidCollision = true;
                        miniShipList.get(i).miniShipAlive = false;
                    }
                    Area firingAreaArea = miniShipList.get(i).getLaserRangeArea();
                    for (int j = 0; j < asteroidList.size(); j++)
                    {
                        Area asteroidArea = asteroidList.get(j).getAsteroidArea();
                        if (intersects(asteroidArea, firingAreaArea) && miniShipList.get(i).miniShipAlive)
                        {
                            asteroidList.remove(asteroidList.get(j));
                            miniShipList.get(i).setKills(miniShipList.get(i).getKills() + 1);
                        }
                    }
                }
            }

            if (!miniShipList.get(0).isMiniShipAlive() && !miniShipList.get(1).isMiniShipAlive())
            {
                laserSTimer.stop();
                for (int i = 0; i < 0; i++)
                {
                    Integer wasteOfTime = new Integer(55);
                }
                System.exit(0);
                //^^^^IS to allow the sound clip time to finish before it exits
            }
        }
        if (e.getSource().equals(miniShipSpeedTimer))
        {
            if (space.isIsUpPressed())
            {
                double dX = -miniShipMovementSpeed * Math.sin(Math.toRadians(miniShip.getMiniShipAngle()));
                double dY = miniShipMovementSpeed * Math.cos(Math.toRadians(miniShip.getMiniShipAngle()));
                miniShip.setMiniShipX(miniShip.getMiniShipX() - (int) dX);
                miniShip.setMiniShipY(miniShip.getMiniShipY() - (int) dY);
            }
            if (space.isIsRightPressed())
            {
                miniShip.setMiniShipAngle(miniShip.getMiniShipAngle() + 3);
            }
            if (space.isIsLeftPressed())
            {
                miniShip.setMiniShipAngle(miniShip.getMiniShipAngle() - 3);
            }
            if (space.isIsSpacePressed())
            {
                ArrayList<Asteroid> asteroidInBoundaryList = new ArrayList<Asteroid>();
                miniShip.showTriangle();
            }
        }
        if (e.getSource().equals(miniShipSpeedTimer))
        {
            if (space.isIsWPressed())
            {
                double dX = -miniShipMovementSpeed * Math.sin(Math.toRadians(miniShip2.getMiniShipAngle()));
                double dY = miniShipMovementSpeed * Math.cos(Math.toRadians(miniShip2.getMiniShipAngle()));
                miniShip2.setMiniShipX(miniShip2.getMiniShipX() - (int) dX);
                miniShip2.setMiniShipY(miniShip2.getMiniShipY() - (int) dY);
            }
            if (space.isIsDPressed())
            {
                miniShip2.setMiniShipAngle(miniShip2.getMiniShipAngle() + 3);
            }
            if (space.isIsAPressed())
            {
                miniShip2.setMiniShipAngle(miniShip2.getMiniShipAngle() - 3);
            }
        }
    }

    private int nearestAsteroid()
    {
        int lowestDifference = 10000000;
        int lowestDiffAstIndex = 0;
        for (int i = 0; i < asteroidList.size(); i++)
        {
            Asteroid tempAsteroid = asteroidList.get(i);
            int difference = Math.abs(tempAsteroid.getAsteroidXposition() - ship.getShipX()) + Math.abs(tempAsteroid.getAsteroidYposition() - ship.getShipY());
            if (i == 0)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }

            if (difference < lowestDifference)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }

        }

        return lowestDiffAstIndex;
    }

    private int nearestAsteroidToMiniShip(MiniShip miniShip)
    {
        int lowestDifference = 10000000;
        int lowestDiffAstIndex = 0;
        for (int i = 0; i < asteroidList.size(); i++)
        {
            Asteroid tempAsteroid = asteroidList.get(i);
            int deltaX = tempAsteroid.getAsteroidXposition() - miniShip.getMiniShipX();
            int deltaY = tempAsteroid.getAsteroidYposition() - miniShip.getMiniShipY();
            int difference = (int) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            if (difference < lowestDifference)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }
        }

        return lowestDiffAstIndex;
    }

    private boolean nearestPossibleAsteroidToMiniShip(MiniShip miniShip)
    {
        int lowestDifference = 10000000;
        int lowestDiffAstIndex = 0;
        for (int i = 0; i < asteroidList.size(); i++)
        {
            Asteroid tempAsteroid = asteroidList.get(i);
            int difference = Math.abs(tempAsteroid.getAsteroidXposition() - miniShip.getMiniShipX()) + Math.abs(tempAsteroid.getAsteroidYposition() - miniShip.getMiniShipY());
            if (i == 0)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }

            if (difference < lowestDifference)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }
        }

        if (lowestDifference > 500)
        {
            return false;
        } else
        {
            return true;
        }
    }

    private Asteroid nearestAsteroidInRange(MiniShip miniShip, ArrayList<Asteroid> rangeList)
    {
        int lowestDifference = 10000000;
        int lowestDiffAstIndex = 0;
        for (int i = 0; i < rangeList.size(); i++)
        {
            Asteroid tempAsteroid = rangeList.get(i);
            int deltaX = tempAsteroid.getAsteroidXposition() - miniShip.getMiniShipX();
            int deltaY = tempAsteroid.getAsteroidYposition() - miniShip.getMiniShipY();
            int difference = (int) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

            if (difference < lowestDifference)
            {
                lowestDifference = difference;
                lowestDiffAstIndex = i;
            }
        }

        return rangeList.get(lowestDiffAstIndex);
    }
}
