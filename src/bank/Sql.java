/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;
/**
 * BANK DATABASE CONNECTION,RETRIVAL,MANIPULATION AND QUERY
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author John T Lazaro
 */
public class Sql {
    //constant declaration
    private static final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/bank";
    private static final String ROOT="root";
    private static final String PASSWORD="john1997";
    //connector
    private Connection conn=null;
    private PreparedStatement insertIntoCustomerDetails=null;
    private PreparedStatement insertIntoCustomerSponorDetails=null;
    private PreparedStatement insertIntoCustomerFile=null;
    private PreparedStatement insertIntoCustomerAccounts=null;
    private PreparedStatement insertIntoAccountDetails=null;
    private PreparedStatement insertIntoStaffDetails=null;
    private PreparedStatement insertIntoStaffAddress=null;
    private PreparedStatement selectCustomerImage=null;
    private PreparedStatement setNewPassword=null;
    private PreparedStatement selectStaffPassword=null;
    private PreparedStatement getAccountOwner=null;
    private PreparedStatement selectAccount=null;
    private PreparedStatement selectAllStaff=null;
    private PreparedStatement updateBalance=null;
    private PreparedStatement insertIntoDeposit=null;
    private PreparedStatement insertIntoWithdrawal=null;
    private PreparedStatement  selectAllCustomer=null;
    private PreparedStatement insertIntoTransfer=null;
    private PreparedStatement getPrevoiusCardNumber=null;
    private PreparedStatement getAllRegisteredCustomer=null;
    private PreparedStatement getOneReport=null;
    private ResultSet gRt=null;
    private ResultSet getOwner=null;
    private ResultSet getAllStaff=null;
    private ResultSet getCustomerImage=null;
    private ResultSet retriveStaffPassword=null;
    private ResultSet getAccount=null;
    private ResultSet getAllCustomer;
    private ResultSet getCardNumber;
    private ResultSet crd=null;
    public Sql(){
        try{
            conn=DriverManager.getConnection(DATABASE_URL,ROOT,PASSWORD);
            /**
            *JOptionPane.showMessageDialog(null,"connected");
            * commented ::: success to connect 
            */
            /**
             * insert Customer_details
             */
            String insertintocustomersdetails="insert into customer_details(firstname,middlename,surname,date,gender,marital_status,customer_identity,c_address,c_password)"
                                        + "values (?,?,?,?,?,?,?,?,?)";
            insertIntoCustomerDetails=conn.prepareStatement(insertintocustomersdetails);
            /**
             * prepare statement to insert data to the customer_sponsor_details table
             */
            String insertintocustomersponsordetails="insert into customer_sponser_details(names,location,sponser_id,id_number,sponser_picture,customer_identity)"
                                                    + "values (?,?,?,?,?,?)";
            insertIntoCustomerSponorDetails=conn.prepareStatement(insertintocustomersponsordetails);
            /**
             * prepare statement to insert data to the customer_files
             */
            String insertintocustomerfiles="insert into customer_files(customer_image,customer_form,customer_identity)"
                                            + "values (?,?,?)";
            insertIntoCustomerFile=conn.prepareStatement(insertintocustomerfiles);
            /**
             * prepare statement for insert values to the customer account databases
             */
            String insertintothecustomeraccount="insert into customer_accounts(account_no,account_name,card_no,balance)"
                                                 + "values (?,?,?,?)";
            insertIntoCustomerAccounts=conn.prepareStatement(insertintothecustomeraccount);
            /**
             * prepare statement to insert data to the account details
             */
            String insertintoaccountdetails="insert into account_details(account_no,bank_name,branch_name,region,email,address,mobile)"
                                            + "values (?,?,?,?,?,?,?)";
            insertIntoAccountDetails=conn.prepareStatement(insertintoaccountdetails);
            /**
             * prepare statement to insert values into staff_details table
             */
            String insertintostaffdetails="insert into staff_details(firstname,surname,date,position,gender,staff_number,s_password,staff_picture)"
                                         + "values (?,?,?,?,?,?,?,?)";
            insertIntoStaffDetails=conn.prepareStatement(insertintostaffdetails);
            /**
             * statement to insert value to the staff_address table
             */
            String insertintostaffaddress="insert into staff_address(staff_number,country,street,s_email)"
                                           + "values (?,?,?,?)";
            insertIntoStaffAddress=conn.prepareStatement(insertintostaffaddress);
            /*
               get account owner
            */
            String getaccountowner="select c_password from customer_details where customer_identity=?";
            getAccountOwner=conn.prepareStatement(getaccountowner);
            /**
             * update c_password filled
            */
            String setnewpassword="update customer_details set c_password=? where customer_identity=?";
            setNewPassword=conn.prepareStatement(setnewpassword);
            /**
             * select password and all customer staff_number
            */
            String selectstaff="select s_password from staff_details where staff_number=?";
            selectStaffPassword=conn.prepareStatement(selectstaff);
            String selectallstaff="select staff_number from staff_details";
            selectAllStaff=conn.prepareStatement(selectallstaff);
            /**
             * get customer picture and account name
             */
            String selectcustomerpicture="select customer_image from customer_files where customer_identity=?";
            selectCustomerImage=conn.prepareStatement(selectcustomerpicture);
            String selectaccount="select account_name ,balance from customer_accounts where account_no=?";
            selectAccount=conn.prepareStatement(selectaccount);
            /**
             * update the balance 
             */
            String updatebalance="update customer_accounts set balance=? where account_no=?";
            updateBalance=conn.prepareStatement(updatebalance);
            /**
             * insert into the table deposit
             */
            String insertintodeposit="insert into deposit values (?,?,?,?,?)";
            insertIntoDeposit=conn.prepareStatement(insertintodeposit);
            /**
             * insert into the withdrawal table
             */
            String insertintowithdrawal="insert into withdrawal values(?,?,?,?,?)";
            insertIntoWithdrawal=conn.prepareStatement(insertintowithdrawal);
            /**
             * select customer account number
             */
            String selectallcustomer="select account_no from customer_accounts";
            selectAllCustomer=conn.prepareStatement(selectallcustomer);
            /**
             * insert into transfer table
             */
            String insertintotransfer="insert into transfer values (?,?,?,?,?)";
            insertIntoTransfer=conn.prepareStatement(insertintotransfer);
            /**
             * get card number
             */
            String getcardnumber="select card_no from customer_accounts";
            getPrevoiusCardNumber=conn.prepareStatement(getcardnumber);
            /**
             * get all customer details
             */
            String getallcustomerRegistered="select * from customer_details,customer_accounts where account_no=customer_identity";
            getAllRegisteredCustomer=conn.prepareStatement(getallcustomerRegistered);
            /**
             * get one report 
             */
            String getonereport="select * from customer_details,customer_accounts where account_no=? and customer_identity=?";
            getOneReport=conn.prepareStatement(getonereport);
        }catch(SQLException ex){
            //popup incase error happened during database connection
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void insertIntoCustomerDetails(String firstname,String middlename,String surname,String date,String gender,String marital_status,String customer_identity,String c_address)throws Exception{
    
        insertIntoCustomerDetails.setString(1,firstname);
        insertIntoCustomerDetails.setString(2,middlename);
        insertIntoCustomerDetails.setString(3,surname);
        insertIntoCustomerDetails.setString(4,date);
        insertIntoCustomerDetails.setString(5,gender);
        insertIntoCustomerDetails.setString(6,marital_status);
        insertIntoCustomerDetails.setString(7,customer_identity);
        insertIntoCustomerDetails.setString(8,c_address);
        insertIntoCustomerDetails.setString(9,"new");
        insertIntoCustomerDetails.executeUpdate();

    }
    public void insertIntoCustomerSponorDetails(String names,String location,String sponser_id,String id_number,FileInputStream sponser_picture,String customer_identity)throws Exception{
            insertIntoCustomerSponorDetails.setString(1,names);
            insertIntoCustomerSponorDetails.setString(2,location);
            insertIntoCustomerSponorDetails.setString(3,sponser_id);
            insertIntoCustomerSponorDetails.setString(4,id_number);
            insertIntoCustomerSponorDetails.setBinaryStream(5,sponser_picture);
            insertIntoCustomerSponorDetails.setString(6,customer_identity);
            insertIntoCustomerSponorDetails.executeUpdate();
    }
    public void insertIntoCustomerFile(FileInputStream customer_image,FileInputStream  customer_form,String customer_identity)throws Exception{
            insertIntoCustomerFile.setBinaryStream(1,customer_image);
            insertIntoCustomerFile.setBinaryStream(2,customer_form);
            insertIntoCustomerFile.setString(3,customer_identity);
            insertIntoCustomerFile.executeUpdate();
    }
    //method
    public void insertIntoStaffAddress(String staff_number,String country,String street,String s_email)throws Exception{
       
        insertIntoStaffAddress.setString(1,staff_number);
        insertIntoStaffAddress.setString(2,country);
        insertIntoStaffAddress.setString(3,street);
        insertIntoStaffAddress.setString(4,s_email);
        insertIntoStaffAddress.executeUpdate();
    }
    public void insertIntoStaffDetails(String firstname,String surname,String date,String position,String gender,String password,FileInputStream staff_picture,String staff_number)throws Exception{
            insertIntoStaffDetails.setString(1,firstname);
            insertIntoStaffDetails.setString(2,surname);
            insertIntoStaffDetails.setString(3,date);
            insertIntoStaffDetails.setString(4,position);
            insertIntoStaffDetails.setString(5,gender);
            insertIntoStaffDetails.setString(6,staff_number);
            insertIntoStaffDetails.setString(7,password);
            insertIntoStaffDetails.setBinaryStream(8,staff_picture);
            insertIntoStaffDetails.executeUpdate();
    }
    public void insertIntoCustomerAccounts(String account_no,String account_name,String card_no,double balance)throws Exception{
        
            insertIntoCustomerAccounts.setString(1,account_no);
            insertIntoCustomerAccounts.setString(2,account_name);
            insertIntoCustomerAccounts.setString(3,card_no);
            insertIntoCustomerAccounts.setDouble(4,balance);
            insertIntoCustomerAccounts.executeUpdate();
    }
    public void insertIntoAccountDetails(String account_no,String bank_name,String branch_name,String region,String email,String address,String mobile)throws Exception{
            //exception thrown
            insertIntoAccountDetails.setString(1,account_no);
            insertIntoAccountDetails.setString(2,bank_name);
            insertIntoAccountDetails.setString(3,branch_name);
            insertIntoAccountDetails.setString(4,region);
            insertIntoAccountDetails.setString(5,email);
            insertIntoAccountDetails.setString(6,address);
            insertIntoAccountDetails.setString(7,mobile);
            insertIntoAccountDetails.executeUpdate();
    }
    public void setNewPassword(String newpassword,String accountnumber){
        try{
            setNewPassword.setString(1,newpassword);
            setNewPassword.setString(2,accountnumber);
            setNewPassword.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public String getAccountOwner(String account_no){
        String password=null;
        try{
            getAccountOwner.setString(1,account_no);
            getOwner=getAccountOwner.executeQuery();
            while(getOwner.next()){
                password=getOwner.getString("c_password");
            }
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
         }
       return password;
    }
    public String selectStaffPassword(String staff_number){
        String password=null;
        try{
            selectStaffPassword.setString(1,staff_number);
            retriveStaffPassword=selectStaffPassword.executeQuery();
            while(retriveStaffPassword.next()){
                password=retriveStaffPassword.getString("s_password");
            }
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
         }
       return password;
    }
    public ArrayList<String> selectAllStaff(){
        ArrayList<String> arrList=new ArrayList<>();
        try{
            getAllStaff=selectAllStaff.executeQuery();
            while(getAllStaff.next()){
                arrList.add(getAllStaff.getString("staff_number"));
            }
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
         }
       return arrList;
    }
    public File selectCustomerImage(String accountnumber){
        File file=null;
	FileOutputStream fos;
	byte b[];
	Blob blob;
        try{
            file=new File("D:\\man of steel\\bank\\ picture.png");
            fos=new FileOutputStream(file);
            selectCustomerImage.setString(1,accountnumber);
            getCustomerImage=selectCustomerImage.executeQuery();
            while(getCustomerImage.next()){
                blob=getCustomerImage.getBlob("customer_image");
                b=blob.getBytes(1,(int)blob.length());
		fos.write(b);
            }
            
        }catch(IOException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return file;
    }
    public String selectAllAccount(String accountnumber){
        String accountname=null;
        try{
            selectAccount.setString(1,accountnumber);
            getAccount=selectAccount.executeQuery();
            while(getAccount.next()){
            accountname=getAccount.getString("account_name");
          
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        return accountname;
    }
    public double selectAccountBalance(String accountnumber){
        double accountBalance=0.0;
        try{
            selectAccount.setString(1,accountnumber);
            getAccount=selectAccount.executeQuery();
            while(getAccount.next()){
            accountBalance=getAccount.getDouble("balance");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return accountBalance;
    }
    public void updateBalance(double totalBalance,String accountNumber){
        try{
            updateBalance.setDouble(1,totalBalance);
            updateBalance.setString(2,accountNumber);
            updateBalance.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void insertIntoDeposit(String reference_no,double deposited_amount,String transaction_date,double total_balance,String accountnumber){
        try{
            insertIntoDeposit.setString(1, reference_no);
            insertIntoDeposit.setDouble(2, deposited_amount);
            insertIntoDeposit.setString(3, transaction_date);
            insertIntoDeposit.setDouble(4, total_balance);
            insertIntoDeposit.setString(5, accountnumber);
            insertIntoDeposit.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }finally{
            try{
                insertIntoDeposit.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
    }
    public void insertIntoWithdrawal(String account_no,String reference_number,String transaction_date,double withdrawal_amount,double total_balance){
        try{
            insertIntoWithdrawal.setString(1,account_no);
            insertIntoWithdrawal.setString(2,reference_number);
            insertIntoWithdrawal.setString(3, transaction_date);
            insertIntoWithdrawal.setDouble(4,withdrawal_amount);
            insertIntoWithdrawal.setDouble(5,total_balance);
            insertIntoWithdrawal.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public ArrayList<String> selectAllCustomer(){
        ArrayList<String> arrList=new ArrayList<>();
        try{
            getAllCustomer=selectAllCustomer.executeQuery();
            while(getAllCustomer.next()){
                arrList.add(getAllCustomer.getString("account_no"));
            }
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
         }
       return arrList;
    }
    public void insertIntoTransfer(String reference_number,String sender_account_number,String receiver_account_number,String date,double amount){
        try{
            insertIntoTransfer.setString(1,reference_number);
            insertIntoTransfer.setString(2,sender_account_number);
            insertIntoTransfer.setString(3,receiver_account_number);
            insertIntoTransfer.setString(4,date);
            insertIntoTransfer.setDouble(5,amount);
            insertIntoTransfer.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public int getCardNumber(){
        String cardNumber,createCardNumber;
        int number=0;
        try{
            getCardNumber=getPrevoiusCardNumber.executeQuery();
            while(getCardNumber.next()){
                cardNumber=getCardNumber.getString("card_no");
                int len=cardNumber.length();
                createCardNumber=cardNumber.substring(len-3, len);
                try{
                    number=Integer.parseInt(createCardNumber);
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null,"fail to create account card_number exhaust");
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return number;
    }
    public ArrayList<Customer> getAllCustomerRegistered(){
        ArrayList<Customer> customerlist=new ArrayList<>();
        Customer customer;
        try{
        crd=getAllRegisteredCustomer.executeQuery();
        while(crd.next()){
            customer=new Customer(crd.getString("firstname"),crd.getString("middlename"),
                                  crd.getString("surname"),crd.getString("date"),
                                  crd.getString("gender"),crd.getString("account_name"),
                                  crd.getString("account_no"),crd.getString("card_no"));
            customerlist.add(customer);
        }
        }catch(SQLException ex){
            
        }
        return customerlist;
    }
    public ArrayList<Customer> getOneReport(String account_no){
        ArrayList<Customer> customer=new ArrayList<>();
        Customer oneCustomer;
        try{
             getOneReport.setString(1,account_no);
             getOneReport.setString(2,account_no);
             gRt=getOneReport.executeQuery();
             while(gRt.next()){
                 oneCustomer=new Customer(gRt.getString("firstname"),gRt.getString("middlename"),
                                  gRt.getString("surname"),gRt.getString("date"),
                                  gRt.getString("gender"),gRt.getString("account_name"),
                                  gRt.getString("account_no"),gRt.getString("card_no"));
                customer.add(oneCustomer);
             }
        }catch(SQLException ex){
                
        }
        return customer;
        
    }
}   
