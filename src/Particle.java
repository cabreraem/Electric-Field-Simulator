import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by emiliacabrera on 5/7/16.
 */
public class Particle {

    private double charge;
    private double posX;
    private double posY;
    private double diam;
    private Circle pos;
    private boolean fixed;
    private double mass;
    private double forcesX;
    private double forcesY;
    private double speedX;
    private double speedY;


    public Particle(double c, int x, int y, boolean f, double m, double sx, double sy) {
        charge = c;
        diam = 20;
        posX = x;
        posY = y;

        pos = new Circle(x, y, 10);
        fixed = f;
        mass = m;
        forcesX = 0;
        forcesY = 0;
        speedX = sx;
        speedY = sy;
    }

    public Particle(int x, int y, boolean b){
        charge = 1.6 * Math.pow(10, -19);
        diam = 20;
        posX = x;
        posY = y;

        if(b)
            mass = Math.pow(10, -10);
        else
            mass = 9.1 * Math.pow(10, -31);
        forcesX = 0;
        forcesY = 0;

        speedX = 0;
        speedY = 0;

        fixed = b;
    }

    //Getters
    public double getCharge() {
        return charge;
    }

    public double getPosX(){ return posX;}

    public double getPosY(){return posY;}

    public double getDiam(){ return diam;}

    public boolean isFixed() {
        return fixed;
    }

    public double getMass() {
        return mass;
    }

    public double getSpeedX(){
        return speedX;
    }

    public double getSpeedY(){
        return speedY;
    }


    //Mathematical methods

    //post: returns magnitude of force between two charged particles
    public double findForce(Particle p){
        double x2 = p.getPosX();
        double y2 = p.getPosY();

        //r = distance between centers of the two particles
        double r = Math.sqrt((posX-x2)*(posX-x2) + (posY-y2)*(posY-y2));

        // Force = kq(1)q(2)/r^2 equation
        double force = charge * p.getCharge() / Math.pow(r, 2);

        double angle = Math.tan((y2-posY)/(x2-posX));

        //adds X component of this force to total X forces acting on particle
        double addForceX = force * Math.cos(angle);
        forcesX -= addForceX;

        //adds Y component of this force to total Y forces acting on particle
        double addForceY = force * Math.sin(angle);
        forcesY -= addForceY;

        return Math.abs(force);
    }

    //uses all X forces and Y forces to find vertical and horizontal accelerations
    //and adds those to the speed of the particle
    public void updateSpeed(){

        double accelX = forcesX / mass;
        double accelY = forcesY / mass;

        speedX += accelX;
        speedY += accelY;

        forcesX = 0;
        forcesY = 0;
    }

    public void move(){
        posX += speedX;
        posY += speedY;
    }
}
