/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import java.util.ArrayList;

/**
 *
 * @author RoyZheng
 */
public class Table {

    ArrayList<Philosopher> philos = new ArrayList<Philosopher>();
    ArrayList<Chopstick> chopsticks = new ArrayList<Chopstick>();

    public Table() {
        for (int i = 0; i != 5; i++) {
            this.chopsticks.add(new Chopstick(i));
        }
        for(int i = 5; i != -1; i--){
            this.chopsticks.get(i).pairLeft(this.chopsticks.get((i-1 == -1 ? 4 : i-1)));
        }
        for(int i = 0; i != 5; i++){
            Philosopher p = new Philosopher(i,this);
            p.setChopsticks(this.chopsticks.get(i), this.chopsticks.get(i+1%5));
            this.philos.add(p);
        }
    }
}
