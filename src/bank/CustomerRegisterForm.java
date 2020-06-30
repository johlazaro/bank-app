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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author John T Lazaro
 */
public class CustomerRegisterForm extends javax.swing.JFrame {
    private Sql sql;
    private String date;
    private File spicture, cpicture,cScannedForm;
    private FileInputStream sponsorPicture,customerPicture,customerForm;
    private String sponsorPicturePath,customerPicturePath,formPath;
    private String[] customerInput;
    private FileInputStream[] image;


    /**
     * Creates new form CustomerRegisterForm
     */
    public CustomerRegisterForm() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        new Clock().start();
        personalDetailsButton.setEnabled(false);
    }
    //clock thread
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
    //--------get customer input from user----------
    public String[] getCustomerInput(){
        return customerInput;
    }
    //---------get customer images-----------------
    public FileInputStream[] getCustomerImage(){
        return image;
    }
    public void resetCustomerField(){
        firstnameField.setText("");
        middlenameField.setText("");
        lastnameField.setText("");
        dateofbirth.setDate(null);
        buttonGroup1.clearSelection();
        buttonGroup4.clearSelection();
        emailAddressField.setText("");
        scannedFormField.setText("");
        sponsorpicture.setIcon(null);
        personPicture.setIcon(null);
        buttonGroup2.clearSelection();
        sponsornamesfield.setText("");
        sponsorLocation.setText("");
        idnumber.setText("");
        firstnameMessage.setText("");
        middlenameMessage.setText("");
        surnameMessage.setText("");
        maritalMessage.setText("");
        dateMessage.setText("");
        genderMessage.setText("");
        scannedformMessage.setText("");
        addressMessage.setText("");
        namesMessage.setText("");
        locationMessage.setText("");
        idTypeMessage.setText("");
        idnumberMessage.setText("");
        personPicture.setText("           photo");
        sponsorpicture.setText("           photo");
    }
    /**
     * send button definition
     */
    public void sendButton(){
                // TODO add your handling code here:
        sql=new Sql();
        customerInput=new String[11];
        image=new FileInputStream[3];
        String gender;
        String mStatus;
        String idType;
        boolean done=true;
        //--------------firstname--------------
        String fname=firstnameField.getText();
        customerInput[0]=fname;
        if(fname.isEmpty()){
            firstnameMessage.setText("*fill");
            done=false;
        }else
            firstnameMessage.setText("");
        //--------------middlename--------------
        String mname=middlenameField.getText();
        customerInput[1]=mname;
        if(mname.isEmpty()){
            middlenameMessage.setText("*fill");
            done=false;
        }else
            middlenameMessage.setText("");
        //--------------last name--------------    
        String lname=lastnameField.getText();
        customerInput[2]=lname;
        if(lname.isEmpty()){
            surnameMessage.setText("*fill");
            done=false;
        }else
            surnameMessage.setText("");
            
        //--------------date from --------------
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try{
            date=df.format(dateofbirth.getDate());
            customerInput[3]=date;
            if(dateofbirth.getDate() == null){
                dateMessage.setText("*choose");
                done=false;
            }
             else
                dateMessage.setText("");
        }catch(Exception e){
            dateMessage.setText("*choose");
            done=false;
        }
       
        //------------------marital status check box----------------
        {
            if(singleCheckButton.isSelected()){
                mStatus="Single";
                customerInput[4]=mStatus;
            }
            else if(marriedCheckButton.isSelected()){
                mStatus="Married";
                customerInput[4]=mStatus;
            }
            else if(divocedCheckButton.isSelected()){
                mStatus="Divoced";
                customerInput[4]=mStatus;
            }
        }
        if(!(singleCheckButton.isSelected()||marriedCheckButton.isSelected()||divocedCheckButton.isSelected())){
            maritalMessage.setText("*select");
            done=false;
        }
        else
            maritalMessage.setText("");
       
        //---------------capture radio button values-----------------
        {
            if(maleButton.isSelected()){
                gender="Male";
                customerInput[5]=gender;
            }
            else if(femaleButton.isSelected()){
                gender="Female";
                customerInput[5]=gender;
            }
        }
        if(!(maleButton.isSelected()||femaleButton.isSelected())){
           genderMessage.setText("*select");
            done=false;
        }else
            genderMessage.setText("");
            
        //----------------- customer email-----------------------
        String email=emailAddressField.getText();
        customerInput[6]=email;
        if(email.isEmpty()){
            addressMessage.setText("*fill");
            done=false;
        }else
            addressMessage.setText("");
        String filename=scannedFormField.getText();
        if(filename.isEmpty()){
            scannedformMessage.setText("*choose");
            done=false;
        }else
            scannedformMessage.setText("");
        //-----------------sponsor names----------------------
        String sponsorNames=sponsornamesfield.getText();
        customerInput[7]=sponsorNames;
        if(sponsorNames.isEmpty()){
            namesMessage.setText("*fill");
            done=false;
        }
        else
            namesMessage.setText("");
        String sLocation=sponsorLocation.getText();
        //----------------- customer location address---------
        customerInput[8]=sLocation;
        if(sLocation.isEmpty()){
            locationMessage.setText("*fill");
            done=false;
        }
        else
            locationMessage.setText("");
        //----------------sponsor  ids ---------------------
        {
            if(voterid.isSelected()){
                idType="Voter";
                customerInput[9]=idType;
                }
            else if(nationalid.isSelected()){
                idType="National";
                customerInput[9]=idType;
            }
            else if(passportid.isSelected()){
                idType="Passport";
                customerInput[9]=idType;
            }
            else if(lisenceid.isSelected()){
                idType="Lisence";
                customerInput[9]=idType;
            }
         }
        if(!(voterid.isSelected()||nationalid.isSelected()||passportid.isSelected()||lisenceid.isSelected())){
            idTypeMessage.setText(" *choose");
            done=false;
        }
        else
            idTypeMessage.setText("");
        //-------------------sponsor id number-----------------------
        String idNumber=idnumber.getText();
        customerInput[10]=idNumber;
        if(idNumber.isEmpty()){
            idnumberMessage.setText("*fill");
            done=false;
        }else
             idnumberMessage.setText("");
        //--------------------taking sponsor picture ---------------
        try{
         spicture=new File(sponsorPicturePath);
         sponsorPicture=new FileInputStream(spicture);
         image[0]=sponsorPicture;
        }catch(Exception ex){
            personPicture.setText("  *Select Picture");
            done=false;
        }
          
        //------------------taking customer picture -----------------
        try{
         cpicture=new File(customerPicturePath);
         customerPicture=new FileInputStream(cpicture);
         image[1]=customerPicture;
        }catch(Exception ex){
            sponsorpicture.setText("   *Select Picture");
            done=false;
        }
        //-----------taking scanned form-------------
        try{
            cScannedForm=new File(formPath);
            customerForm=new FileInputStream(cScannedForm);
            image[2]=customerForm;
        }catch(Exception ex){
            genderMessage.setText("*select");
            done=false;
        }
        //-----------------sending to the database--------------------
        if(done){
            int option=JOptionPane.showConfirmDialog(null,"Send Customer Information","Send Info",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(option==JOptionPane.YES_OPTION){
                try{
                    splitpanel.remove(personalInformationPanel);
                    splitpanel.setRightComponent(new bankInformation(customerInput,image));
                    new bankInformation().getJtextField().requestFocus();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Customer Already Registered");
                    JOptionPane.showMessageDialog(null,e);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        homeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        splitpanel = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        personalDetailsButton = new javax.swing.JButton();
        CustomerTabPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bankName = new javax.swing.JTextField();
        bankCountry = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        accountNumber = new javax.swing.JTextField();
        accountName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        bankResetButton = new javax.swing.JButton();
        bankSendButton = new javax.swing.JButton();
        bankStreet = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        branchName = new javax.swing.JTextField();
        bankPostal = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        bankNumber = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        cardNumber = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        initialAmount = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        personalInformationPanel = new javax.swing.JPanel();
        firstname = new javax.swing.JLabel();
        middlename = new javax.swing.JLabel();
        surname = new javax.swing.JLabel();
        middlenameField = new javax.swing.JTextField();
        firstnameField = new javax.swing.JTextField();
        lastnameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        dateofbirth = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        singleCheckButton = new javax.swing.JCheckBox();
        marriedCheckButton = new javax.swing.JCheckBox();
        divocedCheckButton = new javax.swing.JCheckBox();
        personPicturePanel = new javax.swing.JPanel();
        personPicture = new javax.swing.JLabel();
        personChoosePictureButton = new javax.swing.JButton();
        sponserInformationPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sponsorChoosePictureButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        resetButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        sponsornamesfield = new javax.swing.JTextField();
        sponsorLocation = new javax.swing.JTextField();
        idnumber = new javax.swing.JTextField();
        voterid = new javax.swing.JCheckBox();
        lisenceid = new javax.swing.JCheckBox();
        nationalid = new javax.swing.JCheckBox();
        passportid = new javax.swing.JCheckBox();
        namesMessage = new javax.swing.JLabel();
        idTypeMessage = new javax.swing.JLabel();
        locationMessage = new javax.swing.JLabel();
        idnumberMessage = new javax.swing.JLabel();
        sponsorpicture = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        emailAddressField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        scannedFormField = new javax.swing.JTextField();
        scannedForm = new javax.swing.JButton();
        maleButton = new javax.swing.JRadioButton();
        femaleButton = new javax.swing.JRadioButton();
        firstnameMessage = new javax.swing.JLabel();
        surnameMessage = new javax.swing.JLabel();
        middlenameMessage = new javax.swing.JLabel();
        dateMessage = new javax.swing.JLabel();
        maritalMessage = new javax.swing.JLabel();
        addressMessage = new javax.swing.JLabel();
        scannedformMessage = new javax.swing.JLabel();
        genderMessage = new javax.swing.JLabel();
        clock = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/undo.png"))); // NOI18N
        backButton.setFocusable(false);
        backButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(backButton);

        splitpanel.setDividerLocation(170);

        leftPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        personalDetailsButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        personalDetailsButton.setText("Personal Details");
        personalDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personalDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(personalDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        splitpanel.setLeftComponent(leftPanel);

        CustomerTabPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CustomerTabPane.setEnabled(false);
        CustomerTabPane.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Bank Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Address");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("Branch Name:");

        bankName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankNameActionPerformed(evt);
            }
        });

        bankCountry.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bankCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankCountryActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Account Number");

        accountNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        accountName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setText("Account Name");

        bankResetButton.setText("Reset");

        bankSendButton.setText("Send");

        bankStreet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bankStreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankStreetActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setText("Country");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel17.setText("Street");

        branchName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        bankPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setText("Postal");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel19.setText("Number");

        bankNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel20.setText("Card Number");

        cardNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Enter Initial Amount for Account to be active");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Amount");

        initialAmount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        initialAmount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBox1.setMaximumRowCount(2);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TSHS", "USD" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bankStreet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(bankCountry, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(bankName, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(branchName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bankNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bankPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bankResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(bankSendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel15))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(accountName, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(accountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(initialAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(bankName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bankCountry)
                    .addComponent(bankPostal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(bankNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(accountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(accountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(initialAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankResetButton)
                    .addComponent(bankSendButton))
                .addGap(22, 22, 22))
        );

        CustomerTabPane.addTab("Bank Information", jPanel2);

        splitpanel.setRightComponent(CustomerTabPane);

        personalInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        personalInformationPanel.setName(""); // NOI18N

        firstname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        firstname.setText("Firstname");

        middlename.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        middlename.setText("Middlename");

        surname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        surname.setText("Surname");

        middlenameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        middlenameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middlenameFieldActionPerformed(evt);
            }
        });

        firstnameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        firstnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameFieldActionPerformed(evt);
            }
        });
        firstnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                firstnameFieldKeyPressed(evt);
            }
        });

        lastnameField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lastnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameFieldActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel1.setText("Date of Birth");

        dateofbirth.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Marital Status");

        buttonGroup1.add(singleCheckButton);
        singleCheckButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        singleCheckButton.setText("single");

        buttonGroup1.add(marriedCheckButton);
        marriedCheckButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        marriedCheckButton.setText("Married");

        buttonGroup1.add(divocedCheckButton);
        divocedCheckButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        divocedCheckButton.setText("divoced");

        personPicturePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        personPicture.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        personPicture.setText("            photo");

        javax.swing.GroupLayout personPicturePanelLayout = new javax.swing.GroupLayout(personPicturePanel);
        personPicturePanel.setLayout(personPicturePanelLayout);
        personPicturePanelLayout.setHorizontalGroup(
            personPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        personPicturePanelLayout.setVerticalGroup(
            personPicturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personPicture, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );

        personChoosePictureButton.setText("Choose");
        personChoosePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personChoosePictureButtonActionPerformed(evt);
            }
        });

        sponserInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sponser Information", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Names");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Location");

        sponsorChoosePictureButton.setText("Choose");
        sponsorChoosePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sponsorChoosePictureButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Sponer Id Type");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Id number");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        sponsornamesfield.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sponsornamesfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sponsornamesfieldActionPerformed(evt);
            }
        });

        sponsorLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sponsorLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sponsorLocationActionPerformed(evt);
            }
        });

        idnumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        idnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idnumberActionPerformed(evt);
            }
        });

        buttonGroup2.add(voterid);
        voterid.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        voterid.setText("Voter");

        buttonGroup2.add(lisenceid);
        lisenceid.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lisenceid.setText("lisence");

        buttonGroup2.add(nationalid);
        nationalid.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        nationalid.setText("national");

        buttonGroup2.add(passportid);
        passportid.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        passportid.setText("passport");

        namesMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        idTypeMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        locationMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        idnumberMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        sponsorpicture.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sponsorpicture.setText("           photo");
        sponsorpicture.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout sponserInformationPanelLayout = new javax.swing.GroupLayout(sponserInformationPanel);
        sponserInformationPanel.setLayout(sponserInformationPanelLayout);
        sponserInformationPanelLayout.setHorizontalGroup(
            sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sponsornamesfield, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(sponsorLocation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namesMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addComponent(idnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idnumberMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addComponent(voterid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nationalid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passportid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lisenceid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idTypeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sponsorChoosePictureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sponsorpicture, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        sponserInformationPanelLayout.setVerticalGroup(
            sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sponsornamesfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(namesMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sponsorLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(locationMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sponserInformationPanelLayout.createSequentialGroup()
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(voterid)
                                .addComponent(nationalid)
                                .addComponent(passportid)
                                .addComponent(lisenceid)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(idTypeMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(idnumberMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 68, Short.MAX_VALUE))
            .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sponserInformationPanelLayout.createSequentialGroup()
                        .addComponent(sponsorpicture, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sponsorChoosePictureButton)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sponserInformationPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(sponserInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setText("Address");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setText("gender");

        emailAddressField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailAddressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAddressFieldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setText("Scanned Form");

        scannedFormField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        scannedForm.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        scannedForm.setText("select ");
        scannedForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scannedFormActionPerformed(evt);
            }
        });

        buttonGroup4.add(maleButton);
        maleButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        maleButton.setText("Male");

        buttonGroup4.add(femaleButton);
        femaleButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        femaleButton.setText("female");

        firstnameMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        surnameMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        middlenameMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        dateMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        maritalMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        addressMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        scannedformMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        genderMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout personalInformationPanelLayout = new javax.swing.GroupLayout(personalInformationPanel);
        personalInformationPanel.setLayout(personalInformationPanelLayout);
        personalInformationPanelLayout.setHorizontalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sponserInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(90, 90, 90)
                                .addComponent(maleButton)
                                .addGap(18, 18, 18)
                                .addComponent(femaleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(genderMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                    .addComponent(middlename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(surname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstnameField)
                                    .addComponent(middlenameField)
                                    .addComponent(lastnameField)
                                    .addComponent(dateofbirth, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(surnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(middlenameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(personPicturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(personChoosePictureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailAddressField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                                .addComponent(scannedFormField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scannedForm)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scannedformMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(581, 581, 581))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(singleCheckButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(marriedCheckButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(divocedCheckButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maritalMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        personalInformationPanelLayout.setVerticalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                                        .addComponent(firstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(firstnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)))
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(middlenameMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                        .addComponent(middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(middlenameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(surname, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(surnameMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)))
                            .addComponent(dateofbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                .addComponent(genderMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInformationPanelLayout.createSequentialGroup()
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel12))
                                    .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(maleButton)
                                        .addComponent(femaleButton)))
                                .addGap(8, 8, 8)))
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(maritalMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(singleCheckButton)
                                    .addComponent(marriedCheckButton)
                                    .addComponent(divocedCheckButton))))
                        .addGap(6, 6, 6))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(personPicturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personChoosePictureButton)
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scannedformMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(scannedFormField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(scannedForm, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sponserInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        splitpanel.setRightComponent(personalInformationPanel);

        clock.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(splitpanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clock, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        new BankHome().setVisible(true);
        dispose();
    }//GEN-LAST:event_homeButtonActionPerformed

    private void emailAddressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAddressFieldActionPerformed
        // TODO add your handling code here:
        sponsornamesfield.requestFocus();
    }//GEN-LAST:event_emailAddressFieldActionPerformed

    private void personChoosePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personChoosePictureButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser=new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int choice=filechooser.showOpenDialog(this);
        if(choice==JFileChooser.APPROVE_OPTION){
            File picturePath=filechooser.getSelectedFile();
            customerPicturePath=picturePath.getAbsolutePath();
            ImageIcon icon=new ImageIcon(new ImageIcon(customerPicturePath).getImage().getScaledInstance(personPicture.getWidth(),personPicture.getHeight(),Image.SCALE_SMOOTH));
            personPicture.setText("");
            personPicture.setIcon(icon);
           
        }
            
    }//GEN-LAST:event_personChoosePictureButtonActionPerformed

    private void sponsorChoosePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsorChoosePictureButtonActionPerformed
        // TODO add your handling code here:
         JFileChooser filechooser=new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int choice=filechooser.showOpenDialog(this);
        if(choice==JFileChooser.APPROVE_OPTION){
            File picturePath=filechooser.getSelectedFile();
            sponsorPicturePath=picturePath.getAbsolutePath();
            ImageIcon sponserpicture=new ImageIcon(new ImageIcon(sponsorPicturePath).getImage().getScaledInstance(sponsorpicture.getWidth(),sponsorpicture.getHeight(),Image.SCALE_SMOOTH));
            sponsorpicture.setText("");
            sponsorpicture.setIcon(sponserpicture);
        }
    }//GEN-LAST:event_sponsorChoosePictureButtonActionPerformed

    private void scannedFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scannedFormActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int option=file.showOpenDialog(this);
        if(option==JFileChooser.APPROVE_OPTION){
            File form=file.getSelectedFile();
            formPath=form.getAbsolutePath();
            String formname=form.getName();
            scannedFormField.setText(formname);
        }
    }//GEN-LAST:event_scannedFormActionPerformed

    private void bankStreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankStreetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bankStreetActionPerformed

    private void bankCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankCountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bankCountryActionPerformed

    private void bankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bankNameActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
       int option=JOptionPane.showConfirmDialog(null,"Are you sure you need to reset","Reset Info",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       if(option==JOptionPane.YES_OPTION)
           resetCustomerField();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void firstnameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstnameFieldKeyPressed

    }//GEN-LAST:event_firstnameFieldKeyPressed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        this.sendButton();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void idnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idnumberActionPerformed
        // TODO add your handling code here:
        this.sendButton();
    }//GEN-LAST:event_idnumberActionPerformed

    private void personalDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personalDetailsButtonActionPerformed
        // TODO add your handling code here:
        splitpanel.remove(new bankInformation());
        splitpanel.setRightComponent(personalInformationPanel);
    }//GEN-LAST:event_personalDetailsButtonActionPerformed

    private void firstnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameFieldActionPerformed
        // TODO add your handling code here:
        middlenameField.requestFocus();
    }//GEN-LAST:event_firstnameFieldActionPerformed

    private void middlenameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middlenameFieldActionPerformed
        // TODO add your handling code here:
        lastnameField.requestFocus();
    }//GEN-LAST:event_middlenameFieldActionPerformed

    private void lastnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameFieldActionPerformed
        // TODO add your handling code here:
        emailAddressField.requestFocus();
    }//GEN-LAST:event_lastnameFieldActionPerformed

    private void sponsornamesfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsornamesfieldActionPerformed
        // TODO add your handling code here:
        sponsorLocation.requestFocus();
    }//GEN-LAST:event_sponsornamesfieldActionPerformed

    private void sponsorLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sponsorLocationActionPerformed
        // TODO add your handling code here:
        idnumber.requestFocus();
    }//GEN-LAST:event_sponsorLocationActionPerformed

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
       }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
           //ignore error
       }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegisterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane CustomerTabPane;
    private javax.swing.JTextField accountName;
    private javax.swing.JTextField accountNumber;
    private javax.swing.JLabel addressMessage;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bankCountry;
    private javax.swing.JTextField bankName;
    private javax.swing.JTextField bankNumber;
    private javax.swing.JTextField bankPostal;
    private javax.swing.JButton bankResetButton;
    private javax.swing.JButton bankSendButton;
    private javax.swing.JTextField bankStreet;
    private javax.swing.JTextField branchName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JTextField cardNumber;
    private javax.swing.JLabel clock;
    private javax.swing.JLabel dateMessage;
    private com.toedter.calendar.JDateChooser dateofbirth;
    private javax.swing.JCheckBox divocedCheckButton;
    private javax.swing.JTextField emailAddressField;
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JLabel firstname;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel firstnameMessage;
    private javax.swing.JLabel genderMessage;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel idTypeMessage;
    private javax.swing.JTextField idnumber;
    private javax.swing.JLabel idnumberMessage;
    private javax.swing.JTextField initialAmount;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JCheckBox lisenceid;
    private javax.swing.JLabel locationMessage;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JLabel maritalMessage;
    private javax.swing.JCheckBox marriedCheckButton;
    private javax.swing.JLabel middlename;
    private javax.swing.JTextField middlenameField;
    private javax.swing.JLabel middlenameMessage;
    private javax.swing.JLabel namesMessage;
    private javax.swing.JCheckBox nationalid;
    private javax.swing.JCheckBox passportid;
    private javax.swing.JButton personChoosePictureButton;
    private javax.swing.JLabel personPicture;
    private javax.swing.JPanel personPicturePanel;
    private javax.swing.JButton personalDetailsButton;
    private javax.swing.JPanel personalInformationPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton scannedForm;
    private javax.swing.JTextField scannedFormField;
    private javax.swing.JLabel scannedformMessage;
    private javax.swing.JButton sendButton;
    private javax.swing.JCheckBox singleCheckButton;
    private javax.swing.JSplitPane splitpanel;
    private javax.swing.JPanel sponserInformationPanel;
    private javax.swing.JButton sponsorChoosePictureButton;
    private javax.swing.JTextField sponsorLocation;
    private javax.swing.JTextField sponsornamesfield;
    private javax.swing.JLabel sponsorpicture;
    private javax.swing.JLabel surname;
    private javax.swing.JLabel surnameMessage;
    private javax.swing.JCheckBox voterid;
    // End of variables declaration//GEN-END:variables
}
