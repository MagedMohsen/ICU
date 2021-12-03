package main;

import java.util.Date;

/**
 *
 * @author Maged Mohsen Saafan
 */
public class Record {
    //Attributes
    private Date date;
    private int positive;
    private int recovered;
    private int dead;
    //Setters
    public void setDate(){
        date = new Date();
    }
    public void setPositive(int p){
        if (p>0)
            positive = p;
        else
            System.out.println("Wrong number");
    }
    public void setRecovered(int r){
        if (r>0)
            recovered = r;
        else
            System.out.println("Wrong number");
    }
    public void setDead(int d){
        if (d>0)
            dead = d;
        else
            System.out.println("Wrong number");
    }
    //Getters
    public Date getDate(){
        return date;
    }
    public int getPositive(){
        return positive;
    }
    public int getRecovered(){
        return recovered;
    }
    public int getDead(){
        return dead;
    }
    //Functions (adding positive cases, recovered caes and dead cases)
    public void addPos(){
        positive++;
    }
    public void addRec(){
        positive--;
        recovered++;
    }
    public void addDead(){
        positive--;
        dead++;
    }
}
