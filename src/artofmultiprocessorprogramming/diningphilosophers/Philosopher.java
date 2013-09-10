/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import artofmultiprocessorprogramming.MPUtility;
import artofmultiprocessorprogramming.VerboseObject;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RoyZheng
 */
public class Philosopher extends VerboseObject implements Runnable {

    // Actual thinking time = THINK_TIME_BASE + (0,THINK_TIME_RANGE)
    public static int THINK_TIME_BASE = 5000;///< minimum thinking time
    public static int THINK_TIME_RANGE = 1000;///< random additional time
    public static int EAT_TIME_BASE = 500;///< minimum thinking time
    public static int EAT_TIME_RANGE = 2500;///< random additional time

    private long last_meal = 0;
    private Chopstick c1;
    private Chopstick c2;
    private Philosopher p1;
    private Philosopher p2;
    private Table table;
    int num;///<philosopher number
    PhilosopherState state = PhilosopherState.THINKING;

    public enum PhilosopherState {

        EATING, HUNGRY, THINKING
    }

    /**
     *
     * @param num id of the philosopher
     * @param t table the philosopher is sitting on
     */
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
                Thread.sleep(MPUtility.generateSleepTime(Philosopher.THINK_TIME_BASE, Philosopher.THINK_TIME_RANGE));
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.print("hungry");
            this.state = PhilosopherState.HUNGRY;
            this.table.repaintCanvas();
            while (true) {
                try {
                    this.print("waiting for permission to try to retrieve chopstick");
                    this.table.getChopstickPermission();
                    this.print("permission granted");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.print("attempting to retrieve chopstick");
                if (this.attemptChopstick()) {
                    break;
                }
            }
        }
    }

    protected boolean attemptChopstick() {

        if (this.c1.beingUsed()) {
            this.print("could not obtain chopsticks because they are in use");
            this.table.releaseChopstickPermission();
            this.c1.waitUntilFree();
            return false;
        }
        if (this.c2.beingUsed()) {
            this.print("could not obtain chopsticks because they are in use");
            this.table.releaseChopstickPermission();
            this.c2.waitUntilFree();
            return false;
        }
        if (!this.c1.tryTake(this)
                || !this.c2.tryTake(this)) {
            this.print("CRITICAL ERROR! tryTake failed even though !beingUsed!");
            this.table.releaseChopstickPermission();
            return false;
        }
        final Philosopher self = this;
        (new Thread(new Runnable() {
            @Override
            public void run() {
                self.state = PhilosopherState.EATING;
                self.last_meal = System.currentTimeMillis();
                table.repaintCanvas();
                self.print("eating food with chopsticks");
                try {
                    Thread.sleep(MPUtility.generateSleepTime(Philosopher.EAT_TIME_BASE, Philosopher.EAT_TIME_RANGE));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                }
                c1.dropChopstick(self);
                c2.dropChopstick(self);
                self.state = PhilosopherState.THINKING;
                table.repaintCanvas();
                self.print("finished eating, dropping chopsticks");
            }
        })).start();
        this.table.releaseChopstickPermission();
        return true;
    }

    public boolean isHungry() {
        return this.state == PhilosopherState.HUNGRY;
    }

    public boolean isEating() {
        return this.state == PhilosopherState.EATING;
    }

    public boolean isThinking() {
        return this.state == PhilosopherState.THINKING;
    }

    public PhilosopherState getState() {
        return this.state;
    }

    public String toString() {
        return this.getName() + " holding " + this.c1 + ", " + this.c2;
    }
}
