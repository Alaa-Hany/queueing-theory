package com.alaa.queueing_theory.app.queues;

public class Stochastic {


    private int k;
    private int c;
    private double mu;
    private double lamda;


    public Stochastic(double mu, double lamda) {
        this.mu = mu;
        this.lamda = lamda;
    }

    public Stochastic(int k, int c, double mu, double lamda) {
        this.k = k;
        this.c = c;
        this.mu = mu;
        this.lamda = lamda;
    }

    public Stochastic() {
        this.k = 0;
        this.c = 0;
        this.mu = 0;
        this.lamda = 0;
    }


    public double calcL() {
        return Math.random();
    }

    public double calcLq() {
        return Math.random();
    }


    public double calcW() {
        return Math.random();
    }

    public double calcWq() {
        return Math.random() ;
    }


    public double calcPZero() {
        double p = this.calcP();
        return 0.0;

    }

    public double calcP() {
        if ((this.getC() == 0) || (this.getMu() == 0)) return 0.0;
        return ((this.getLamda() / this.getMu()) / this.getC());
    }


    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getLamda() {
        return lamda;
    }

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }
}
