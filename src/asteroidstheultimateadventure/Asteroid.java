package asteroidstheultimateadventure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
public class Asteroid implements ActionListener
{
    AffineTransform identityTransform = new AffineTransform();
    int framesStillAlive = 0;
    boolean isGoingToDie = false;
    private ArrayList<Asteroid> asteroidList;
    Random randomTwo = new Random();
    Polygon asteroidShape;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int asteroidYposition;
    private int asteroidXposition;
    private int xSpeed;
    private int ySpeed;
    Rectangle2D.Double lightFlash = new Rectangle2D.Double(0, 0, width, height);
    private boolean flash = true;
    public int flashCounter = 0;
    LaserS laser = new LaserS();
    public int damage = 0;
    public Color newColor;
    private String asteroidNumber = "";
    private Area asteroidArea;
    private AffineTransform asteroidAreaAffineTransform = new AffineTransform();
    public Asteroid()
    {
        calculateInitialAsteroidPosition();
        calculateAsteroidDirection();
        newColor = Color.GRAY;
    }
    public void setDimensions()
    {
        int[] asteroidXPoints =
        {
            randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
        };
        int[] asteroidYPoints =
        {
            randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100), randomTwo.nextInt(100)
        };
        asteroidShape = new Polygon(asteroidXPoints, asteroidYPoints, 5);
        asteroidArea = new Area(asteroidShape);
    }
    public void paintSelf(Graphics2D g2)
    {
        if (damage == 0)
        {
            newColor = Color.gray;
        } else
        {
            newColor = Color.DARK_GRAY;
        }
        g2.setTransform(identityTransform);
//        g2.setFont(new Font("Paskowy", Font.BOLD, 35));
        g2.setColor(Color.green);
        g2.drawString("00000" + asteroidNumber, asteroidXposition, asteroidYposition);
        g2.setColor(newColor);
        g2.translate(this.asteroidXposition, this.asteroidYposition);
        g2.setStroke(new BasicStroke(1));
        g2.fill(this.asteroidShape);
        g2.setColor(Color.WHITE);
        g2.draw(this.asteroidShape);
        asteroidArea = new Area(asteroidShape);
        asteroidArea.transform(g2.getTransform());
        g2.setColor(newColor);
//        g2.fill(asteroidArea);
        g2.setColor(Color.green);
        g2.setColor(newColor);
        g2.setTransform(identityTransform);
        if (this.asteroidXposition > width && this.asteroidYposition > height)
        {
        }
        if (this.asteroidXposition > (width - 100) && this.asteroidYposition > (height - 100) && this.flash)
        {
            g2.setTransform(new AffineTransform());
            g2.setColor(Color.white);
            g2.fill(lightFlash);
            this.flash = false;
        }
    }

    public void setColor(Color newColor)
    {
        this.newColor = newColor;
    }

    public Color getColor()
    {
        return newColor;
    }

    public void setAsteroidList(ArrayList<Asteroid> asteroidList)
    {
        this.asteroidList = asteroidList;
    }

    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < asteroidList.size(); i++)
        {
//            if (!asteroidList.get(i).isIsGoingToDie())
//            {
                asteroidList.get(i).asteroidXposition += asteroidList.get(i).xSpeed;
                asteroidList.get(i).asteroidYposition += asteroidList.get(i).ySpeed;
//                    if (asteroidArea.getBounds().x >= Toolkit.getDefaultToolkit().getScreenSize().width || asteroidArea.getBounds().y >= Toolkit.getDefaultToolkit().getScreenSize().height)
//                {
//                    setIsGoingToDie(true);
//                }
//            } else
//            {
                asteroidList.get(i).setFramesStillAlive(asteroidList.get(i).getFramesStillAlive() + 1);
//            }
            if (asteroidList.get(i).getFramesStillAlive() >= 350)
            {
                asteroidList.remove(i);
            }
        }
    }

    public void calculateInitialAsteroidPosition()
    {
        int side = (int) (Math.random() * 4);
        side++;
        if (side == 1 || side == 2)
        {
            asteroidXposition = 0;
            asteroidYposition = (int) (Math.random() * height * .75);
        } else if (side == 4 || side == 3)
        {
            asteroidXposition = (int) (Math.random() * width * .75);
            asteroidYposition = 0;
        }
    }

    private void calculateAsteroidDirection()
    {
        int moveToX = width;
        int moveToY = height;

        //MOVE TO BOTTOM RIGHT CORNER EVERY TIME. GET ARCTAN AND ADD 90

        ySpeed = (moveToY - asteroidYposition) / 300;
        xSpeed = (moveToX - asteroidXposition) / 300;
    }

    public int getAsteroidXposition()
    {
        return asteroidXposition;
    }

    public int getAsteroidYposition()
    {
        return asteroidYposition;
    }

    public String getAsteroidNumber()
    {
        return asteroidNumber;
    }

    public void setAsteroidNumber(String asteroidNumber)
    {
        this.asteroidNumber = asteroidNumber;
    }

    public boolean isIsGoingToDie()
    {
        return isGoingToDie;
    }

    public void setIsGoingToDie(boolean isGoingToDie)
    {
        this.isGoingToDie = isGoingToDie;
    }

    public int getFramesStillAlive()
    {
        return framesStillAlive;
    }

    public void setFramesStillAlive(int framesStillAlive)
    {
        this.framesStillAlive = framesStillAlive;
    }

    public Area getAsteroidArea()
    {
        return asteroidArea;
    }
}
