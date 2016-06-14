/**
 * Created by emiliacabrera on 5/7/16.
 */
public class Particle {

    private double charge;
    private double posX;
    private double posY;
    private double diam;
    private boolean fixed;
    private boolean positive;
    private double mass;
    private double forcesX;
    private double forcesY;
    private double speedX;
    private double speedY;


    public Particle(double c, int x, int y, boolean f, double m, double sx, double sy) {
        charge = c;

        if(charge > 0)
            positive = true;

        else
            positive = false;

        diam = 20;
        posX = x;
        posY = y;
        fixed = f;
        mass = m;
        forcesX = 0;
        forcesY = 0;
        speedX = sx;
        speedY = sy;
    }

    public Particle(int x, int y, boolean b){
        diam = 20;
        posX = x;
        posY = y;

        if(b) {
            mass = Math.pow(10, -10);
            charge = 1.6 * Math.pow(10, -19);
        }
        else {
            mass = 9.1 * Math.pow(10, -31);
            charge = 1.6 * Math.pow(10, -19);
        }
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

    public boolean isPositive() {
        return positive;
    }

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

    //Setters

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }


    //Mathematical methods

    //post: returns distance between centers of two particles
    public double distance(Particle p){
        double x2 = p.getPosX();
        double y2 = p.getPosY();

        //r = distance between centers of the two particles
        double r = Math.sqrt((posX-x2)*(posX-x2) + (posY-y2)*(posY-y2))* Math.pow(10, -6);

        return r * Math.pow(10, 2);
    }

    //post: returns magnitude of force between two charged particles
    public double findForce(Particle p){
        double x2 = p.getPosX();
        double y2 = p.getPosY();

        double r = distance(p);

        // Force = kq(1)q(2)/r^2 equation
        double force = charge * p.getCharge() / Math.pow(r, 2);

        //adds X component of this force to total X forces acting on particle
        double addForceX = force * (posX - x2) / r;
        forcesX -= addForceX;


        //adds Y component of this force to total Y forces acting on particle
        double addForceY = force * (posY - y2) / r;
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

        System.out.println(accelX);
    }

    public void move(){
        posX += speedX;
        posY += speedY;
    }
}
