package asteroidstheultimateadventure;

import java.awt.Polygon;

public class StarS
{
    public int[] starXPoints = {0,1,0,-1};
    public int[] starYPoints = {1,0,-1,0};
    public Polygon starShape = new Polygon(starXPoints, starXPoints, 3);
}
