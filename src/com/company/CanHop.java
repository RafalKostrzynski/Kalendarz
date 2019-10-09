package com.company;

public interface CanHop {
    public abstract void makeSound();
}
class Frog implements CanHop{
    public void makeSound(){}
}
interface tj extends CanHop{public void bark();}

class TurtleFrog extends Frog{
    public TurtleFrog()
    {System.out.println();}
}
class Brazilian extends Frog{
    public Brazilian()
    {System.out.println();}
}