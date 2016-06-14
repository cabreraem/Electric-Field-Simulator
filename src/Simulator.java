import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by emiliacabrera on 5/9/16.
 */
public class Simulator extends JPanel implements ActionListener, MouseListener{

    private ArrayList<Particle> fixedParts;
    private ArrayList<Particle> movingParts;
    private Particle test;
    private boolean isFixed;


    public Simulator(){
        setBackground(Color.BLACK);

        setFocusable(true);
        this.addMouseListener(this);

        test = new Particle(100,100, false);
        isFixed = false;

        fixedParts = new ArrayList<>();
        movingParts = new ArrayList<>();

        Timer timer = new Timer(1000/60, this);
        timer.start();

    }

    public void actionPerformed(ActionEvent e){
        update();
    }

    public void update(){
        for(int i= 0; i < movingParts.size(); i++){
            Particle temp = movingParts.get(i);

            for (int j = 0; j < fixedParts.size(); j++) {
                Particle check = fixedParts.get(j);
                temp.findForce(check);

                //if particles are the same charge and they intersect, it stops moving
                if((temp.isPositive() && check.isPositive()) || !(temp.isPositive() && check.isPositive())){
                    if(temp.distance(check) < temp.getDiam()/ 2. + check.getCharge()/2.){
                        movingParts.remove(i);
                    }
                }

                else{

                }
            }

            for(int k= 0; k< movingParts.size(); k++){
                if( i!=k)
                    movingParts.get(i).findForce(movingParts.get(k));
            }

            movingParts.get(i).updateSpeed();
            movingParts.get(i).move();
        }


        test.move();

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.WHITE);

        //g.fillOval((int)test.getPosX(),(int)test.getPosY(),(int)test.getDiam(),(int)test.getDiam());


        for(int i = 0; i<fixedParts.size(); i++){
            g.setColor(Color.RED);
            g.fillOval((int)fixedParts.get(i).getPosX(), (int)fixedParts.get(i).getPosY(), (int)fixedParts.get(i).getDiam(), (int)fixedParts.get(i).getDiam());
        }

        for (int i = 0; i < movingParts.size(); i++) {
            g.setColor(Color.BLUE);
            g.fillOval((int) movingParts.get(i).getPosX(), (int) movingParts.get(i).getPosY(), (int) movingParts.get(i).getDiam(), (int) movingParts.get(i).getDiam());
        }

    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            isFixed = false;
        }
        else
            isFixed = true;

        int x = e.getX();
        int y = e.getY();

        Particle adder = new Particle(x, y, isFixed);

        if(isFixed)
            fixedParts.add(adder);
        else
            movingParts.add(adder);

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){

    }
}
