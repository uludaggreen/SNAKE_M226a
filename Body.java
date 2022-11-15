import java.util.ArrayList; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.*;

/**
 * The Body will grow when the Snake eats an apple.
 * 
 * @author Tim Höhn & Athavan Ranganathan
 * @version V2
 */

public class Body extends SnakeTile
{
    World world;
    SnakeTile tileToFollow;
    Head head;
    public int id;
    
    //Body Konfiguration
    public Body(World world, SnakeTile tileToFollow, Head head, int id){
       this.world = world;
       this.tileToFollow = tileToFollow;
       this.head = head;
       this.id = id;
       this.setRotation(tileToFollow.headingDirection);
       
       GreenfootImage img = this.getImage();
       img.scale(30,30);
       img.setColor(Color.CYAN);
       img.fill();
    }
    
    //acts
    public void act(){
        move(3);
        checkForTurnPoint();
    }
    
    //bei einem turnpoint wird eine neue rotation gegeben und dem Tile gefolgt
    public void checkForTurnPoint(){
        if(tileToFollow.turnPoints.size() > 0){
            ArrayList<Integer> newTurnPoint = tileToFollow.turnPoints.get(0);
            if(this.getX() == newTurnPoint.get(0) && this.getY() == newTurnPoint.get(1)){
                headingDirection = tileToFollow.nextRotations.get(0);
                setRotation(headingDirection);
                if(id != head.tiles.size() - 1){
                    nextRotations.add(headingDirection);
                    turnPoints.add(newTurnPoint);   
                }
                tileToFollow.nextRotations.remove(0);
                tileToFollow.turnPoints.remove(0);
            }
        }
    }
    
    //tiles werden gespawnt und werden nach der nächsten Pos nachgezogen
    public int[] getSpawnLocation(){
        int[] nextPos = new int[2];
        nextPos[0] = tileToFollow.getX();
        nextPos[1] = tileToFollow.getY();
        switch(tileToFollow.headingDirection){
            case 0:
                nextPos[0] -= this.getImage().getWidth();
                break;
            case 90:
                nextPos[1] -= this.getImage().getWidth();
                break;
            case 180:
                nextPos[0] += this.getImage().getWidth();
                break;
            case 270:
                nextPos[1] += this.getImage().getWidth();
                break;
        }
        headingDirection = tileToFollow.headingDirection;
        return nextPos;
    }
    // spawn Location abrufen
    public void addToWorld(){
        int[] spawnPosition = getSpawnLocation();
        world.addObject(this, spawnPosition[0], spawnPosition[1]);
    }
}
