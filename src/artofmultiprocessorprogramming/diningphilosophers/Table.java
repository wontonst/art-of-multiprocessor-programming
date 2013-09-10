/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author RoyZheng
 */
public class Table {

    ArrayList<Philosopher> philos = new ArrayList<Philosopher>();
    ArrayList<Chopstick> chopsticks = new ArrayList<Chopstick>();
    ArrayList<Thread> threads = new ArrayList<Thread>();
    PhilosophersCanvas canvas;
    Semaphore arbiter = new Semaphore(1);

    public void setCanvas(PhilosophersCanvas c) {
        this.canvas = c;
    }

    public Table() {
        for (int i = 0; i != 5; i++) {
            this.chopsticks.add(new Chopstick(i));
        }
        //for(int i = 5; i != -1; i--){
        //    this.chopsticks.get(i).pairLeft(this.chopsticks.get((i-1 == -1 ? 4 : i-1)));
        //}
        for (int i = 0; i != 5; i++) {
            Philosopher p = new Philosopher(i, this);
            p.setChopsticks(this.chopsticks.get(i), this.chopsticks.get((i + 1) % 5));
            this.philos.add(p);
            System.out.println(p);
        }
    }

    public void getChopstickPermission() throws InterruptedException {
        this.arbiter.acquire();
    }

    public void releaseChopstickPermission() {
        this.arbiter.release();
    }

    public void startThreads() {
        for (Philosopher p : this.philos) {
            this.threads.add(new Thread(p));
            this.threads.get(this.threads.size() - 1).start();
        }
    }

    public void repaintCanvas() {
        this.canvas.repaint();
    }
}
