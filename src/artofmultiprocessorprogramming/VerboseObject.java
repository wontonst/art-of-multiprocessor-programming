/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package artofmultiprocessorprogramming;

/**
 *
 * @author RoyZheng
 */
public class VerboseObject {
    String name;
    public VerboseObject(String name){
        this.name = name;
    }
    public void print(String p){
        System.out.println(name+":"+p);
    }
    public String toString(){
        return this.name;
    }
    public String getName(){
        return this.name;
    }
}
