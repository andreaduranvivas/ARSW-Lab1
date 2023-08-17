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
public class CountThread extends Thread{

    int A;
    int B;

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public void run(){
        System.out.println("thread is running...");
        printNumbers(this.A, this.B);
    }
    public static void main(String[] args) {
        CountThread obj = new CountThread();
        obj.start();
    }

    private void printNumbers(int A, int B){
        for (int i = A; i <= B; i ++) {
            System.out.println(i);
        }
    }

    public void setInterval(int A, int B) {
        setA(A);
        setB(B);
    }
}


