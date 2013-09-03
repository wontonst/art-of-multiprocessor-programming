/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming.diningphilosophers;

import artofmultiprocessorprogramming.VerboseObject;
import java.util.concurrent.Semaphore;

/**
 *
 * @author RoyZheng
 */
public class Chopstick extends VerboseObject {
    
    Semaphore leftpair;
    Semaphore rightpair;
    
    int num;///<chopstick number
    
    public Chopstick(int num){
        super("Chopstick"+num);
        this.num = num;
    }
    public void pairLeft(Chopstick c){
        Semaphore s = new Semaphore(1);
        this.leftpair = s;
        c.rightpair = s;
    }
    public synchronized boolean attemptPickup(){
        if(leftpair.availablePermits() != 0 && right)
    }
}
