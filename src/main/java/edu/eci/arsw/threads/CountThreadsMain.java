/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author Camilo Fajardo y Andrea Duran
 */
public class CountThreadsMain {
    
    public static void main(String a[]){

        CountThread threadOne = new CountThread();
        threadOne.setInterval(0, 99);

        CountThread threadTwo = new CountThread();
        threadTwo.setInterval(99, 199);

        CountThread threadThree = new CountThread();
        threadThree.setInterval(200, 299);

        /**threadOne.start();
        threadTwo.start();
        threadThree.start();**/

        threadOne.run();
        threadTwo.run();
        threadThree.run();
    }

}
