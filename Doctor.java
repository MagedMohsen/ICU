package main;

/**
 *
 * @author Maged Mohsen Saafan
 */
public class Doctor {
    //Attributes
    private String dSSN;
    private String dName;
    private String dGender;
    private String sciDgre;
    private String job;
    private String dEmail;
    private String dPass;
    //Setters
    public void setdName(String n){
        dName = n;
    }
    public void setdGender(String g){
        dGender = g;
    }
    public void setdSSN(String s){
        if (s.length()==14)
        dSSN = s;
        else
            System.out.println("SSN should be 14 number");
    }
    public void setDSciDgre(String sc){
        sciDgre = sc;
    }
    public void setDJob(String j){
        job = j;
    }
    public void setdEmail(String e){
        if (e.contains("@")&&e.contains(".com"))
        dEmail = e;
        else
            System.out.println("please enter a valid email");
    }
    public void setdpass(String p){
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
            dPass = p;
    }
    //Getters
    public String getdName(){
        return dName;
    }
    public String getdGender(){
        return dGender;
    }
    public String getdSSN(){
        return dSSN;
    }
    public String getDSciDgre(){
        return sciDgre;
    }
    public String getDJob(){
        return job;
    }
    public String getdEmail(){
        return dEmail;
    }
    public String getdPass(){
        return dPass;
    }
    
}
