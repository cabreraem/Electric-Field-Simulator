import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by emiliacabrera on 5/9/16.
 */
public class Driver {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame("Field Simulator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                Simulator simulator = new Simulator();
                simulator.repaint();
                frame.add(simulator, BorderLayout.CENTER);

                frame.setSize(1000, 1000);

                //status bar at bottom
                JPanel statusPanel = new JPanel();
                statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                frame.add(statusPanel, BorderLayout.SOUTH);
                statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 75));
                statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
                statusPanel.setBackground(Color.RED);

                frame.setVisible(true);
            }
        });

        }
    }
