/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import artofmultiprocessorprogramming.GraphicDisplay;

/**
 *
 * @author RoyZheng
 */
public class Main {

    public static int THINKING_TIME = 5500;
    
    public static void main(String[] args) {
        Table t = new Table();
        PhilosophersCanvas c = new PhilosophersCanvas(t);
        GraphicDisplay g = new GraphicDisplay(null, c);
        t.setCanvas(c);
        t.startThreads();
    }
}
