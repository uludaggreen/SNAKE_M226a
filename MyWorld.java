import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MyWorld extends World
{
    //Constructor für die Objekte aus der Klasse MyWorld
    public MyWorld()
    {    
        super(800, 600, 1); 
        prepare();
    }
    // Vorbereitung für den Start des Spiels
    private void prepare()
    {
        Apple apple = new Apple(100, 100);
        addObject(apple,283,228);
        Head head = new Head();
        addObject(head,450,392);
    }
}
