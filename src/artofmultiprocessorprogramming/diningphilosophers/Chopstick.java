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
public class Chopstick extends VerboseObject {

    Semaphore wait = new Semaphore(1);
    int num;///<chopstick number
    Philosopher in_use = null;

    public Chopstick(int num) {
        super("Chopstick" + num);
        this.num = num;
    }

    public boolean beingUsed() {
        return this.in_use != null;
    }

    public boolean tryTake(Philosopher p) {
        if (!this.beingUsed()) {
            this.in_use = p;
            if (this.wait.availablePermits() == 0) {
                this.print("ERROR! NO PERMITS AVAILABLE TO TAKE EVEN THOUGH CHOPSTICK IS FREE!");
            } else {
                try {
                    this.wait.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Chopstick.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return true;
        }
        return false;
    }

    public void dropChopstick(Philosopher p) {
        if (p != this.in_use) {
            this.print("ERROR! Philsopher " + p + " tried to drop this chopstick even though he wasn't holding it.");
        } else {
            this.in_use = null;
            this.wait.release();
        }
    }

    public void waitUntilFree() {
        try {
            this.wait.acquire();
            this.wait.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Chopstick.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
