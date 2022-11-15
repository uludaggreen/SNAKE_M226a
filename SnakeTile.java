import greenfoot.*; 
import java.util.ArrayList;

/**
 * @author Tim Höhn & Athavan Ranganathan
 * @version V2
 */

public class SnakeTile extends Actor
{
    public int headingDirection = 0;
    //[[x, y]]
    public ArrayList<ArrayList<Integer>> turnPoints = new ArrayList<ArrayList<Integer>>();
    public ArrayList<Integer> nextRotations = new ArrayList<Integer>();
}
