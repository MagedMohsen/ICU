package main;

/**
 *
 * @author Maged Mohsen Saafan
 */
public class Patient {
    //Attributes
    private String pSSN;
    private String pName;
    private String pGender;
    private int pAge;
    private String pStatus;
    private String pEmail;
    private String pPass;
    //Setters
    public void setpName(String n){
        pName = n;
    }
    public void setpGender(String g){
        pGender = g;
    }
    public void setpSSN(String s){
        if (s.length()==14)
        pSSN = s;
        else
            System.out.println("SSN should be 14 number");
    }
    public void setpAge(int a){
        if (a>0)
        pAge = a;
        else
            System.out.println("please enter a valid age");
    }
    public void setpStatus(String st){
        pStatus = st;
    }
    public void setpEmail(String e){
        if (e.contains("@")&&e.contains(".com"))
        pEmail = e;
        else
            System.out.println("please enter a valid email");
    }
    public void setppass(String p){
        char c;
        boolean upper = false;
        boolean lower = false;
        boolean number = false;
        for (int i=0;i<p.length();i++){
            c = p.charAt(i);
            if (Character.isUpperCase(c))
                upper = true;
        }
        for (int i=0;i<p.length();i++){
            c = p.charAt(i);
            if (Character.isLowerCase(c))
                lower = true;
        }
        for (int i=0;i<p.length();i++){
            c = p.charAt(i);
            if (Character.isDigit(c))
                number = true;
        }
        if (!upper)
            System.out.println("please choose a password that contains at least one uppercase letter");
        if (!lower)
            System.out.println("please choose a password that contains at least one lowercase letter");
        if (!number)
            System.out.println("please choose a password that contains at least one number");
        if (p.length()<8)
            System.out.println("please choose a password that consists of at least 8 characters");
        if (upper && lower && number && p.length()>=8)
            pPass = p;
        
    }
    //Getters
    public String getpName(){
        return pName;
    }
    public String getpGender(){
        return pGender;
    }
    public String getpSSN(){
        return pSSN;
    }
    public int getpAe(){
        return pAge;
    }
    public String getpStatus(){
        return pStatus;
    }
    public String getpEmail(){
        return pEmail;
    }
    public String getpPass(){
        return pPass;
    }
}
