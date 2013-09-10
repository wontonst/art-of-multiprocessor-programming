/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class PhilosophersCanvas extends JPanel {

    Table t;

    PhilosophersCanvas(Table t) {
        this.t = t;
        this.setSize(50 + t.philos.size() * 86, 200);
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        int x = 25, y = 100;
        for (int i = 0; i != t.philos.size(); i++) {
            switch (t.philos.get(i).state) {
                case HUNGRY:
                    g.setColor(Color.red);
                    g.fillRect(x, y, 50, 50);
                    break;
                case EATING:
                    g.setColor(Color.green);
                    g.fillRect(x, y, 50, 50);
                    break;
                case THINKING:
                    g.setColor(Color.black);
                    g.drawRect(x, y, 50, 50);
                    break;
            }
            x += 88;
        }
        x = 7;
        y = 50;
        for (int i = 0; i != t.chopsticks.size(); i++) {
            g.setColor(Color.black);
            if (t.chopsticks.get(i).beingUsed()) {
                g.fillRect(x, y, 10, 100);
            } else {
                g.drawRect(x, y, 10, 100);
            }
            x += 88;
        }
    }
}
