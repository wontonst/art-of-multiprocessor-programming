/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import artofmultiprocessorprogramming.VerboseObject;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RoyZheng
 */
public class Philosopher extends VerboseObject implements Runnable {

    private Semaphore eating = new Semaphore(1);
    private long last_meal;
    private Chopstick c1;
    private Chopstick c2;
    private Philosopher p1;
    private Philosopher p2;
    private Table table;
    int num;///<philosopher number

    public Philosopher(int num, Table t) {
        super("Philosopher" + num);
        this.table = t;
    }

    public void setChopsticks(Chopstick o, Chopstick t) {
        this.c1 = o;
        this.c2 = t;
    }

    public void setNeighbors(Philosopher o, Philosopher t) {
        this.p1 = o;
        this.p2 = t;
    }

    @Override
    public void run() {
        this.print("Starting thread");
        while (true) {
            try {
                Thread.sleep(Main.THINKING_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (true) {
                this.print("attempting to retrieve chopstick");
                this.attemptChopstick();
            }
        }
    }

    protected void attemptChopstick() {
    }
}
