package com.alaa.queueing_theory.app.queues;


public class DD1K {

    private int k;
    private double lamda;
    private double mu;
    private int initNoOfCustomers;


    /**
     * @param k     number of servers
     * @param lamda ratio of arrival customers
     * @param mu    ratio of served customer
     */
    public DD1K(int k, double lamda, double mu) {

        this.k = k;
        this.lamda = lamda;
        this.mu = mu;
    }


    /**
     * Create queue with all zeros default for all vars
     */

    public DD1K() {
        this.k = 0;
        this.lamda = 0;
        this.mu = 0;
    }


    /**
     * create queue with lamda and mu
     * k default equal 4
     *
     * @param lamda ratio of arrival customers
     * @param mu    ratio of served customer
     */
    public DD1K(double lamda, double mu) {
        this.lamda = lamda;
        this.mu = mu;
        this.k = 4;

    }


    /**
     * get number of waiting customers in queue at special time
     *
     * @param t time to get waiting customers at
     * @return int
     */
    public int getNoOfCustomersAtT(int t) {
        if (t >= this.getTi()) {
            return this.getNoOfCustomersAtTAfterTi(t);
        }
        return this.getNoOfCustomersAtTBeforeTi(t);

    }

    public int getNoOfCustomersAtTBeforeTi(int t) {
        return Math.min(this.getK() - 1, this.getNoOfAllCustomersAtT(t) - this.getNoOfServedCustomersAtT(t));
    }

    public int getNoOfCustomersAtTAfterTi(int t) {
        if (this.lamda > this.getMu()) {
            return this.getK() - 1;
        }
        return 0;
    }


    private int getNoOfCustomersAtTAsLaw(int t) {
        return this.getNoOfCustomersAtTBeforeTi(t);
    }


    /**
     * get number of served customers in queue at special time
     *
     * @param t time to get served customer at
     * @return int
     */
    private int getNoOfServedCustomersAtT(int t) {
        if (this.getLamda() > this.getMu())
            return (int) ((this.mu * t) - (this.mu / this.lamda));
        return (int) (this.mu * t);
    }


    /**
     * get number of  all  customers at special time
     *
     * @param t time to get number of customers in system
     * @return int
     */
    private int getNoOfAllCustomersAtT(int t) {
        if (this.getLamda() <= this.getMu())
            return ((int) (this.lamda * t)) + this.initNoOfCustomers;
        return (int) (this.lamda * t);
    }


    /**
     * get time that customer will wait before be served
     *
     * @param customerNumber customer's number
     */

    public int getWaitingTimeForCustomerNo(int customerNumber) {
        if (customerNumber >= (this.getTi() * this.getLamda())) {
            return this.getWaitingTimeForCustomerAfterTi(customerNumber);
        }
        return this.getWaitingTimeForCustomerBeforeTi(customerNumber);


    }


    /**
     * @param customerNumber
     * @return
     */
    private int getWaitingTimeForCustomerBeforeTi(int customerNumber) {
        if (this.getLamda() > this.getMu()) {
            return (int) ((1 / this.getMu() - 1 / this.getLamda()) * (customerNumber - 1));
        }
        return (int) (((this.getInitNoOfCustomers() - 1 + customerNumber) * (1 / this.getMu())) - customerNumber * (1 / this.getLamda()));
    }


    private int getWaitingTimeForCustomerAfterTi(int customerNumber) {
        if (this.getLamda() > this.getMu()) {
            return (int) ((1 / this.getMu() - 1 / this.getLamda()) * (this.getTi() * this.getLamda() - 2));
        }
        return 0;
    }

    /*
     * setters
     * k , lamda , mu and initial customer when system start
     *
     */

    public void setK(int k) {
        this.k = k;
    }

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public void setInitNoOfCustomers(int initNoOfCustomers) {
        this.initNoOfCustomers = initNoOfCustomers;
    }
    // end Setter


    /*
     * getters
     * k , lamda , mu , initial customer when system start and ti
     */
    public int getK() {
        return this.k;
    }

    public double getLamda() {
        return lamda;
    }

    public double getMu() {
        return mu;
    }

    public int getInitNoOfCustomers() {
        return this.initNoOfCustomers;
    }


    /**
     * trial error for ti
     *
     * @return ti
     */
    public int getTi() {
        double tmpTi = this.calculateTi();
        tmpTi -= (int) (1 / this.getLamda());
        while (this.getK() == this.getNoOfCustomersAtTAsLaw((int) tmpTi)) {
            tmpTi -= (int) (1 / this.getLamda());
        }
        return ((this.getLamda() % this.getMu()) == 0.0) ? (int) (tmpTi + (1 / this.getLamda())) : (int) (tmpTi + (2 / this.getLamda()));
    }


    /**
     * calculate first ti
     *
     * @return int ti
     */
    private int calculateTi() {
        if (this.getLamda() > this.getMu()) {
            double f = (this.getLamda() * this.getK() - this.getMu());
            double s = (Math.pow(this.getLamda(), 2) - this.getLamda() * this.getMu());
            return (int) Math.ceil(f / s);
        }
        return (int) (this.getInitNoOfCustomers() / (this.getMu() - this.getLamda()));
    }
}
