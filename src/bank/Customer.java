/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author John T Lazaro
 */
public class Customer {
    private String fname;
    private String mname;
    private String surname;
    private String gander;
    private String dateOfBirth;
    private String accountName;
    private String accountNumber;
    private String cardNumber;
    private String dateRegistered;
    private String status;
    /**
     * customer constructor
     */
    public Customer(String fname,String mname,String surname,String dateOfBirth,String gander,String accountName,String accountNumber,String cardNumber){
        this.fname=fname;
        this.mname=mname;
        this.surname=surname;
        this.dateOfBirth=dateOfBirth;
        this.gander=gander;
        this.accountName=accountName;
        this.accountNumber=accountNumber;
        this.cardNumber=cardNumber;
        
    }
    public String getFname(){
        return fname;
    }
    public String getMname(){
        return mname;
    }
    public String getSurname(){
        return surname;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getGander(){
        return gander;
    }
    public String getAccountName(){
        return accountName;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public String getCardNumber(){
        return cardNumber;
    }
    
}