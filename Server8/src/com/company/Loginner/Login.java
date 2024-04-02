package com.company.Loginner;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Login implements Serializable {
    public String login;
    public String password;
    public boolean newUser;
    public int r;
    public int g;
    public int b;
    private boolean approved = false;
    public boolean prev = true;
    private static final long serialVersionUID = 44;

    public void login(){
        System.out.println("Type 'sign up', if you are a new user and 'sign in', if you have an account");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(line.toLowerCase(Locale.ROOT).equals("sign up"))
            newUser = true;
        if(line.toLowerCase(Locale.ROOT).equals("sign in"))
            newUser = false;
        System.out.print("Enter your login: ");
        login = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();
        password = getMD5(password);

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMD5(String plaintext){
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
            return hashtext;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public boolean isApproved(){
        return approved;
    }

    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public boolean isNew(){
        return newUser;
    }
}
