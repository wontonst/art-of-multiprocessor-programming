/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author RoyZheng
 */
public class GraphicDisplay extends JFrame {

    protected JPanel infopanel;
    protected JPanel canvas;

    public GraphicDisplay(JPanel ip, JPanel c) {
        int X = 10;
        int Y = 10;
        this.canvas = c;
        this.infopanel = ip;

        if (this.infopanel != null) {
            this.add(this.infopanel);
            X += this.infopanel.getWidth();
            Y += this.infopanel.getHeight();
        }
        if (this.canvas != null) {
            this.add(this.canvas);
            X += this.canvas.getWidth();
            Y += this.canvas.getHeight();
        }
        this.setSize(X, Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
