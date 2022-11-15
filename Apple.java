import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The apple is eaten by the Snake and then changes its Position
 * 
 * @author Tim Höhn & Athavan Ranganathan 
 * @version V2
 */
public class Apple extends Actor
{
    private int xPadding;
    private int yPadding;
    //Grenze für den Apfel definieren, damit er nicht am Rand spawnt.
    public Apple(int xPadding, int yPadding){
        this.xPadding = xPadding;
        this.yPadding = yPadding;
    }
    //neue random location vom Apfel
    public void chooseNewLocation(){
        int[] newPos = generateRandomPos();
        this.setLocation(newPos[0], newPos[1]);
    }
    //random location generator mit padding
    public int[] generateRandomPos(){
        int[] newPos = new int[2];
        newPos[0] = (int) (Math.random() * ((this.getWorld().getWidth() - xPadding) - xPadding)) + xPadding;
        newPos[1] = (int) (Math.random() * ((this.getWorld().getHeight() - yPadding) - yPadding)) + yPadding;
        return newPos;
    }
}
