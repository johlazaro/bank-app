/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author John T Lazaro
 */
public class StaffRegisterForm extends javax.swing.JFrame {

    /**
     * Creates new form StaffRegisterForm
     */
    private Sql sql;
    private String staffpicturepath;
    private FileInputStream staff_picture;
    public StaffRegisterForm() {
        initComponents();
        setLocationRelativeTo(null);
        staffpictureMessage.setVisible(false);
        new Clock().start();
        
    }
    //reset method
    public void reset(){
        firstname.setText("");
        firstnameMessage.setText("");
        surname.setText("");
        surnameMessage.setText("");
        date.setDate(null);
        dateMessage.setText("");
        position.setText("");
        positionMessage.setText("");
        buttonGroup1.clearSelection();
        genderMessage.setText("");
        street.setText("");
        streetMessage.setText("");
        //country.setText("");
        countryMessage.setText("");
        email.setText("");
        emailMessage.setText("");
        staffnumber.setText("");
        staffPicture.setIcon(null);
        staffpictureMessage.setVisible(false);
        staffnumberMessage.setText("");
    }
    //sendMethod
    public void sendMethod(){
        boolean done=true;
        String dt=null;
        sql=new Sql();
        String gender=null;
         //------------firstname-------------
        String firstName=firstname.getText();
        if(firstName.isEmpty()){
            firstnameMessage.setText("fill");
            done=false;
        }else
            firstnameMessage.setText("");
         //------------surname-------------
        String surName=surname.getText();
       if(surName.isEmpty()){
            surnameMessage.setText("fill");
            done=false;
        }else
            surnameMessage.setText("");
        //------------Date-------------
       SimpleDateFormat df=new SimpleDateFormat("MM-dd-YYYY");
       try{
           dt=df.format(date.getDate());
           if(date.getDate()==null){
               dateMessage.setText("Select");
               done=false;
           }else
               dateMessage.setText("");
       }catch(Exception e){
           dateMessage.setText("Select");
           done=false;
       }
       //------------position-------------
       String pos=position.getText();
       if(pos.isEmpty()){
           positionMessage.setText("fill");
           done=false;
       }
       else
           positionMessage.setText("");
       //------------staff gender-------------
       {
           if(staffMaleButton.isSelected())
               gender="Male";
           else if(stafffemaleButton.isSelected())
               gender="Female";

           if(!(staffMaleButton.isSelected()||stafffemaleButton.isSelected())){
               genderMessage.setText("select");
               done=false;
           }else
               genderMessage.setText("");
       }
       //------------street-------------
       String st=street.getText();
       if(st.isEmpty()){
           streetMessage.setText("fill");
           done=false;
       }else
           streetMessage.setText("");
       //------------email-------------
       String em=email.getText();
       if(em.isEmpty()){
           emailMessage.setText("*Fill");
           done=false;
       }else{
           emailMessage.setText("");
           if(em.contains("@")){
              emailMessage.setText("");
           }else{
               emailMessage.setText("*bad Email Format");
               done=false;
           }
       }
       //------------staff number-------------
       String staffNumber=staffnumber.getText();
       if(staffNumber.isEmpty()){
           staffnumberMessage.setText("fill");
           done=false;
       }else
           staffnumberMessage.setText("");
       //------------county-------------
       String coun=country.getText();
       
       if(coun.isEmpty()){
           countryMessage.setText("fill");
           done=false;
       }else{
           countryMessage.setText("");
       }
       // //------------customer picture-------------
       {
           try{
               staffpictureMessage.setVisible(false);
               staff_picture=new FileInputStream(new File(staffpicturepath));
               }
           catch(Exception ex){
               staffpictureMessage.setVisible(true);
               done=false; 
           }
        }
        //staff password
        String password = Base64.getEncoder().encodeToString("john1997".getBytes());

       if(done){
           int option=JOptionPane.showConfirmDialog(null,"Register New Staff","Register Staff Info",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
           if(option==JOptionPane.YES_OPTION){
                try{
                    sql.insertIntoStaffDetails(firstName, surName,dt, pos, gender,password, staff_picture, staffNumber);
                    sql.insertIntoStaffAddress(staffNumber, coun, st, em);
                    JOptionPane.showMessageDialog(null,"New Staff registered");
                    this.reset();
                }catch(Exception ex){
                     JOptionPane.showMessageDialog(null,"Staff Already Registered"+ex);
                }
       }    
       }
    }  
    /**
     * inner class Clock
     * 
     */
    //clock Thread
     class Clock extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    LocalTime time=LocalTime.now();
                    String h=Integer.toString(time.getHour());
                    String m=Integer.toString(time.getMinute());
                    String s=Integer.toString(time.getSecond());
                    clock.setText(h+":"+m+":"+s);
                    sleep(1000);

                }catch(InterruptedException e){
                    //ignore erro
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        homeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Firstaname = new javax.swing.JLabel();
        Firstaname1 = new javax.swing.JLabel();
        Firstaname2 = new javax.swing.JLabel();
        Firstaname3 = new javax.swing.JLabel();
        firstname = new javax.swing.JTextField();
        surname = new javax.swing.JTextField();
        position = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        staffPicture = new javax.swing.JLabel();
        staffPictureButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        street = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        country = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        staffnumber = new javax.swing.JTextField();
        staffResetButton = new javax.swing.JButton();
        staffSendButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        date = new com.toedter.calendar.JDateChooser();
        staffMaleButton = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        stafffemaleButton = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        firstnameMessage = new javax.swing.JLabel();
        dateMessage = new javax.swing.JLabel();
        positionMessage = new javax.swing.JLabel();
        surnameMessage = new javax.swing.JLabel();
        genderMessage = new javax.swing.JLabel();
        countryMessage = new javax.swing.JLabel();
        streetMessage = new javax.swing.JLabel();
        emailMessage = new javax.swing.JLabel();
        staffnumberMessage = new javax.swing.JLabel();
        staffpictureMessage = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1132, 591));
        setResizable(false);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/home.png"))); // NOI18N
        homeButton.setFocusable(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(homeButton);

        Firstaname.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        Firstaname.setText("Firstname");

        Firstaname1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        Firstaname1.setText("Surname");

        Firstaname2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        Firstaname2.setText("Date Of Birth");

        Firstaname3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        Firstaname3.setText("Position");

        firstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        surname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        position.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("STAFF REGISTRATION");

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        staffPicture.setText("             photo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(staffPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(staffPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        staffPictureButton.setText("Choose");
        staffPictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffPictureButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Address");

        street.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Street :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Country");

        country.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        country.setText("Tanzania");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Email :");

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Registered Staff Number");

        staffnumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staffnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffnumberActionPerformed(evt);
            }
        });

        staffResetButton.setText("Reset");
        staffResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffResetButtonActionPerformed(evt);
            }
        });

        staffSendButton.setText("Send");
        staffSendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffSendButtonActionPerformed(evt);
            }
        });

        date.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        buttonGroup1.add(staffMaleButton);
        staffMaleButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        staffMaleButton.setText("Male");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Gender");

        buttonGroup1.add(stafffemaleButton);
        stafffemaleButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        stafffemaleButton.setText("female");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        firstnameMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        firstnameMessage.setToolTipText("");

        dateMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dateMessage.setToolTipText("");

        positionMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionMessage.setToolTipText("");

        surnameMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        surnameMessage.setToolTipText("");

        genderMessage.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        genderMessage.setToolTipText("");

        countryMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        countryMessage.setToolTipText("");

        streetMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        streetMessage.setToolTipText("");

        emailMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        emailMessage.setToolTipText("");

        staffnumberMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        staffnumberMessage.setToolTipText("");

        staffpictureMessage.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        staffpictureMessage.setText("Select picture -->");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Firstaname3, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                .addGap(864, 864, 864))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Firstaname1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Firstaname, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(firstname)
                                                .addComponent(surname)
                                                .addComponent(position)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(staffMaleButton)
                                                    .addGap(57, 57, 57)
                                                    .addComponent(stafffemaleButton)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(genderMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(street, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                                        .addComponent(country)
                                                        .addComponent(staffnumber))
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(Firstaname2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(positionMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(surnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(firstnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(staffpictureMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(countryMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(staffnumberMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(streetMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(staffPictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(staffResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(staffSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Firstaname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(staffpictureMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(surname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Firstaname1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(surnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Firstaname2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(dateMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffPictureButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Firstaname3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(position, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(positionMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stafffemaleButton)
                            .addComponent(staffMaleButton)
                            .addComponent(genderMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(streetMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(emailMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addComponent(countryMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(staffnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(staffnumberMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(staffResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(staffSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        clock.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        new BankHome().setVisible(true);
        dispose();
    }//GEN-LAST:event_homeButtonActionPerformed

    private void staffPictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffPictureButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser= new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
        int option=filechooser.showOpenDialog(this);
        if(option==JFileChooser.APPROVE_OPTION){
            File file=filechooser.getSelectedFile();
            staffpicturepath=file.getAbsolutePath();
            ImageIcon image=new ImageIcon(new ImageIcon(staffpicturepath).getImage().getScaledInstance(staffPicture.getWidth(),staffPicture.getHeight(),Image.SCALE_SMOOTH));
            staffPicture.setText("");
            staffPicture.setIcon(image);
            
        }
    }//GEN-LAST:event_staffPictureButtonActionPerformed

    private void staffResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffResetButtonActionPerformed
        // TODO add your handling code here:
        this.reset();
    }//GEN-LAST:event_staffResetButtonActionPerformed

    private void staffSendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffSendButtonActionPerformed
        // TODO add your handling code here:
        this.sendMethod();
 
    }//GEN-LAST:event_staffSendButtonActionPerformed

    private void staffnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffnumberActionPerformed
        // TODO add your handling code here:
        this.sendMethod();
    }//GEN-LAST:event_staffnumberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex){
            //ignore error
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffRegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Firstaname;
    private javax.swing.JLabel Firstaname1;
    private javax.swing.JLabel Firstaname2;
    private javax.swing.JLabel Firstaname3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel clock;
    private javax.swing.JTextField country;
    private javax.swing.JLabel countryMessage;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JLabel dateMessage;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailMessage;
    private javax.swing.JTextField firstname;
    private javax.swing.JLabel firstnameMessage;
    private javax.swing.JLabel genderMessage;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField position;
    private javax.swing.JLabel positionMessage;
    private javax.swing.JRadioButton staffMaleButton;
    private javax.swing.JLabel staffPicture;
    private javax.swing.JButton staffPictureButton;
    private javax.swing.JButton staffResetButton;
    private javax.swing.JButton staffSendButton;
    private javax.swing.JRadioButton stafffemaleButton;
    private javax.swing.JTextField staffnumber;
    private javax.swing.JLabel staffnumberMessage;
    private javax.swing.JLabel staffpictureMessage;
    private javax.swing.JTextField street;
    private javax.swing.JLabel streetMessage;
    private javax.swing.JTextField surname;
    private javax.swing.JLabel surnameMessage;
    // End of variables declaration//GEN-END:variables
}
