package main;

/**
 *
 * @author Maged Mohsen Saafan
 */
public class Hospital {
    //Attributes
    private String hID;
    private String hName;
    private String hAddress;
    private String hCity;
    private int hNumOfBeds;
    
    //Setters
    public void setHID (String h){
        hID=h;
    }
    public void setHName (String n){
        hName=n;
    }
    public void setHAddress (String a){
        hAddress=a;
    }
    public void setHCity (String s){
        hCity=s;
    }
    public void setHNumOfBeds (int n){
        if (n>0)
        hNumOfBeds=n;
        else 
            System.out.println("please enter a positive number");
    }
    
    //Getters
    public String getHID(){
        return hID;
    }
    public String getHName(){
        return hName;
    }
    public String getHAddress(){
        return hAddress;
    }
    public String getHCity(){
        return hCity;
    }
    public int getHNumOfBeds(){
        return hNumOfBeds;
    }
}
