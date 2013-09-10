/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming;

/**
 *
 * @author wontonst
 */
public class MPUtility {

    public static long generateSleepTime(int base, int added_range) {
        return (long) (base + Math.random() * added_range);
    }
}
