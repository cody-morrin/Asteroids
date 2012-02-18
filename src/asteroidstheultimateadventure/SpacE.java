/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidstheultimateadventure;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Cody
 */
public class SpacE implements KeyListener
{
    MiniShip miniShip;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private Painter painter;
    private ArrayList<String> asteroidNumbers = new ArrayList<String>();
    URL laserAndExplosionFile = this.getClass().getResource("explosion4.wav");
    AudioClip laserAndExplosionSound = Applet.newAudioClip(laserAndExplosionFile);
    private boolean isUpPressed = false;
    private boolean isRightPressed = false;
    private boolean isLeftPressed = false;
    private boolean isSpacePressed = false;
    int spacePressCount = 1;
    private boolean isWPressed = false;
    private boolean isDPressed = false;
    private boolean isAPressed = false;
    private boolean isShiftPressed = false;
    int shiftPressCount = 1;

    public void makeSpace()
    {
        JFrame playingField = new JFrame("Asteroid Ship");
        playingField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playingField.setSize(width, height);
        playingField.setVisible(true);
        playingField.add(painter);
        playingField.addKeyListener(this);
    }

    public void setPainter(Painter painter)
    {
        this.painter = painter;
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            asteroidNumbers.add(painter.getAsteroidTypedText());
            painter.setAsteroidTypedText("");
            laserAndExplosionSound.play();
        }
        if (e.getKeyCode() == KeyEvent.VK_0)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 0);
        }
        if (e.getKeyCode() == KeyEvent.VK_1)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 1);
        }
        if (e.getKeyCode() == KeyEvent.VK_2)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 2);
        }
        if (e.getKeyCode() == KeyEvent.VK_3)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 3);
        }
        if (e.getKeyCode() == KeyEvent.VK_4)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 4);
        }
        if (e.getKeyCode() == KeyEvent.VK_5)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 5);
        }
        if (e.getKeyCode() == KeyEvent.VK_6)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 6);
        }
        if (e.getKeyCode() == KeyEvent.VK_7)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 7);
        }
        if (e.getKeyCode() == KeyEvent.VK_8)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 8);
        }
        if (e.getKeyCode() == KeyEvent.VK_9)
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText() + 9 );
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && !painter.getAsteroidTypedText().equals(""))
        {
            painter.setAsteroidTypedText(painter.getAsteroidTypedText().substring(0, painter.getAsteroidTypedText().length() - 1));
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            isUpPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            isRightPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            isLeftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            isSpacePressed = true;
            spacePressCount++;
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            isWPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            isDPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            isAPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            isShiftPressed = true;
            shiftPressCount++;
        }

    }

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            isSpacePressed = false;
            spacePressCount = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            isUpPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            isRightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            isLeftPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            isShiftPressed = false;
            shiftPressCount = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            isWPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            isDPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            isAPressed = false;
        }
    }

    ArrayList<String> getNumbersToRemove()
    {
        return asteroidNumbers;
    }

    void clearAsteroidNumbers()
    {
        asteroidNumbers.clear();
    }

    public void setMiniShip(MiniShip miniShip)
    {
        this.miniShip = miniShip;
    }

    public boolean isIsUpPressed()
    {
        return isUpPressed;
    }
    public boolean isIsRightPressed()
    {
        return isRightPressed;
    }
    public boolean isIsLeftPressed() 
    {
        return isLeftPressed;
    }
    public boolean isIsSpacePressed()
    {
        boolean spacePressed = isSpacePressed;
        isSpacePressed = false;
        return spacePressed;
    }
    public boolean isIsWPressed()
    {
        return isWPressed;
    }
    public boolean isIsDPressed()
    {
        return isDPressed;
    }
    public boolean isIsAPressed()
    {
        return isAPressed;
    }
    public boolean isIsShiftPressed()
    {
        boolean shiftPressed = isShiftPressed;
        isShiftPressed = false;
        return shiftPressed;
    }

}
