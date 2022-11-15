import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * The Head part of the Snake "eats" the Apple.
 * 
 * @author Tim Höhn & Athavan Ranganathan 
 * @version V2
 */

public class Head extends SnakeTile
{
    private int currentInputCooldown = 0;
    private final int INPUT_COOLDOWN = 20;
    
    public ArrayList<SnakeTile> tiles = new ArrayList<SnakeTile>();
    // Kopf Konfiguration
    public Head(){
       tiles.add(this);
       GreenfootImage img = this.getImage();
       img.scale(30,30);
       img.setColor(Color.BLUE);
       img.fill();
    }
    //acts
    public void act()
    {
        move(3);
        getNewHeadingDirection();
        checkForCollision();
    }
    //auf collision wird game over ausgegeben und ein neues Tile wird in die welt gespawnt
    public void checkForCollision(){
        Apple touchingApple = (Apple) this.getOneIntersectingObject(Apple.class);
        Body touchingBody = (Body) this.getOneIntersectingObject(Body.class);
        if(touchingApple != null){
            Body newTile = new Body(this.getWorld(), tiles.get(tiles.size() - 1), this, tiles.size());
            newTile.addToWorld();
            tiles.add(newTile);
            touchingApple.chooseNewLocation();
        }
        
        if((touchingBody != null && touchingBody.id != 1) || this.isAtEdge()){
            int score = tiles.size() - 1;
            this.getWorld().showText("GAME OVER! YOUR SCORE WAS: " + score, this.getWorld().getWidth() / 2, this.getWorld().getHeight() / 2);
            Greenfoot.stop();
        }
    }
    //steuerung
    public void getNewHeadingDirection(){
        if(currentInputCooldown == 0){
            int disallowedDirection = 360 - Math.abs(180 - headingDirection);
            if(Greenfoot.isKeyDown("W") && headingDirection != 90){
                headingDirection = 270;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("A") && headingDirection != 0){
                headingDirection = 180;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("S") && headingDirection != 270){
                headingDirection = 90;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("D") && headingDirection != 180){
                headingDirection = 0;
                updateRotation();
            }
            
            else if(Greenfoot.isKeyDown("up") && headingDirection != 90){
                headingDirection = 270;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("left") && headingDirection != 0){
                headingDirection = 180;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("down") && headingDirection != 270){
                headingDirection = 90;
                updateRotation();
            }
            else if(Greenfoot.isKeyDown("right") && headingDirection != 180){
                headingDirection = 0;
                updateRotation();
            }
        }
        //cooldown
        else{
            currentInputCooldown -= 1;
        }
    }
    //neue richtung für tiles
    public void updateRotation(){
        ArrayList<Integer> newTurnPoint = new ArrayList<Integer>();
        newTurnPoint.add(this.getX());
        newTurnPoint.add(this.getY());
        
        // das die tiles nicht komplett aneinander sind
        if(tiles.size() >= 2){
            nextRotations.add(headingDirection);
            turnPoints.add(newTurnPoint);    
        }
        // cooldown für rotations
        setRotation(headingDirection);
        currentInputCooldown = INPUT_COOLDOWN;
    }
}
