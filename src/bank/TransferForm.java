/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author John T Lazaro
 */
public class TransferForm extends javax.swing.JFrame {

    /**
     * Creates new form TransferForm
     */
    private Date date;
    private Calendar calendar;
    private File image,receiverImage;
    private Sql sql;
    private ArrayList<String> accountsno,accountsno1;
    public TransferForm() {
        initComponents();
        setLocationRelativeTo(null);
        okTick.setVisible(false);
        accountNumber.requestFocus();
        okTickLabel1.setVisible(false);
        amountField.setEditable(false);
        accountNumber1.setEditable(false);
        receiverCheckButton.setEnabled(false);
    }
    /*
    *   check button
    */
    public void senderCheckButton(){
        sql=new Sql();
        String accountnumber=accountNumber.getText();
        accountsno=new ArrayList<>();
        accountsno=sql.selectAllCustomer();
        try{
            if(accountsno.contains(accountnumber)){
                senderReset();
                receiverReset();
                accountMessage.setText("");
                image=sql.selectCustomerImage(accountnumber);
                String accountname=sql.selectAllAccount(accountnumber);
                double accountBalance=sql.selectAccountBalance(accountnumber);
                okTick.setVisible(true);
                senderAccountName.setText(accountname);
                senderAccountBalance.setText(Double.toString(accountBalance));
                customerSenderPicture.setIcon(new ImageIcon(new ImageIcon(image.getAbsolutePath()).getImage().getScaledInstance(customerSenderPicture.getWidth(),customerSenderPicture.getHeight(),Image.SCALE_SMOOTH)));
                accountNumber1.setEditable(true);
                accountNumber1.requestFocus();
                receiverCheckButton.setEnabled(true);
                receiverMessage.setText("Please Enter Account Number");
            }else{
                senderReset();
                receiverReset();
                accountMessage.setText("Account doesn't Exist");
                accountNumber.setText("");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public void senderReset(){
        customerSenderPicture.setIcon(null);
        senderAccountBalance.setText("");
        amountField.setText("");
        accountMessage.setText("");
        senderAccountName.setText("");
        okTick.setVisible(false);
        receiverCheckButton.setEnabled(false);
        receiverMessage.setText("");
        amountField.setEditable(false);
        referenceNumberDisplay.setText("");
        time.setText("-------null--------");
        referenceNumberDisplay1.setText("");
        accountNumber1.setEditable(false);
        amountLabel.setText("");
        accountNumber.requestFocus();
    }
     public void receiverReset(){
        customerReceiverPicture.setIcon(null);
        receiverAccountBalance.setText("");
        recieverAccountName.setText("");
        accountNumber.requestFocus();
        accountMessage1.setText("");
        accountNumber1.setText("");
    }
      /**
     * clock 
     */
    public class TransactionTime extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    calendar = Calendar.getInstance(); 
                    date = calendar.getTime();
                    time.setText(date.toString());
                    sleep(1000);
                }catch(InterruptedException e){

                }
            }
        }

     }
    public void receiverCheckButton(){
        sql=new Sql();
        String accountnumber=accountNumber1.getText();
        accountsno1=new ArrayList<>();
        accountsno1=sql.selectAllCustomer();
        try{
            if(accountsno1.contains(accountnumber)){
                accountMessage1.setText("");
                receiverImage=sql.selectCustomerImage(accountnumber);
                String accountname=sql.selectAllAccount(accountnumber);
                double accountBalance=sql.selectAccountBalance(accountnumber);
                okTick.setVisible(true);
                recieverAccountName.setText(accountname);
                receiverAccountBalance.setText(Double.toString(accountBalance));
                customerReceiverPicture.setIcon(new ImageIcon(new ImageIcon(receiverImage.getAbsolutePath()).getImage().getScaledInstance(customerReceiverPicture.getWidth(),customerReceiverPicture.getHeight(),Image.SCALE_SMOOTH)));
                String refNumber=DepositForm.referenceNumberRandomGenerator();
                referenceNumberDisplay1.setText(refNumber);
                referenceNumberDisplay.setText(refNumber);
                amountField.requestFocus();
                amountField.setEditable(true);
                receiverMessage.setText("");
                new TransactionTime().start();
                
            }else{
                this.receiverReset();
                accountNumber1.setText("");
                referenceNumberDisplay1.setText("");
                referenceNumberDisplay.setText("");
                amountField.setEditable(false);
                receiverMessage.setText("Please Enter Receiver Account Number");
                accountMessage1.setText("Account doesn't Exist");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
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

        jToolBar1 = new javax.swing.JToolBar();
        homeButton = new javax.swing.JButton();
        topSeparator = new javax.swing.JSeparator();
        topSeparator1 = new javax.swing.JSeparator();
        exhangeLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accountNumberPanel = new javax.swing.JPanel();
        accountNumber = new javax.swing.JTextField();
        okTick = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ckeckButton = new javax.swing.JButton();
        accountMessage = new javax.swing.JLabel();
        customerSenderPicture = new javax.swing.JLabel();
        referrenceNumber = new javax.swing.JPanel();
        referenceNumberDisplay1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        senderAccountBalance = new javax.swing.JLabel();
        accountNumberPanel1 = new javax.swing.JPanel();
        accountNumber1 = new javax.swing.JTextField();
        okTickLabel1 = new javax.swing.JLabel();
        accountMessage1 = new javax.swing.JLabel();
        receiverCheckButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        recieverAccountName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        referrenceNumber1 = new javax.swing.JPanel();
        referenceNumberDisplay = new javax.swing.JLabel();
        customerReceiverPicture = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        resetButton = new javax.swing.JButton();
        transferButton = new javax.swing.JButton();
        dateAndTime = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        horizontalglue = new javax.swing.JLabel();
        accountBalanceLabel = new javax.swing.JLabel();
        senderAccountName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        receiverAccountBalance = new javax.swing.JLabel();
        accountBalanceLabel1 = new javax.swing.JLabel();
        receiverMessage = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1212, 641));

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

        topSeparator.setForeground(new java.awt.Color(0, 0, 0));

        exhangeLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        exhangeLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\John T Lazaro\\icon\\exchange.png")); // NOI18N
        exhangeLabel.setText("Money Transfer");
        exhangeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exhangeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exhangeLabelMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("From :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("To.");

        accountNumberPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        accountNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        accountNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        accountNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNumberActionPerformed(evt);
            }
        });

        okTick.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        okTick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/ok.png"))); // NOI18N

        javax.swing.GroupLayout accountNumberPanelLayout = new javax.swing.GroupLayout(accountNumberPanel);
        accountNumberPanel.setLayout(accountNumberPanelLayout);
        accountNumberPanelLayout.setHorizontalGroup(
            accountNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountNumberPanelLayout.createSequentialGroup()
                .addComponent(accountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okTick)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        accountNumberPanelLayout.setVerticalGroup(
            accountNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(accountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(okTick)
        );

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ckeckButton.setText("Check");
        ckeckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckeckButtonActionPerformed(evt);
            }
        });

        accountMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        referrenceNumber.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Referrence Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        referenceNumberDisplay1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout referrenceNumberLayout = new javax.swing.GroupLayout(referrenceNumber);
        referrenceNumber.setLayout(referrenceNumberLayout);
        referrenceNumberLayout.setHorizontalGroup(
            referrenceNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, referrenceNumberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(referenceNumberDisplay1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );
        referrenceNumberLayout.setVerticalGroup(
            referrenceNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referenceNumberDisplay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ref  No :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Account  Name:");

        senderAccountBalance.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        senderAccountBalance.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        accountNumberPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        accountNumber1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        accountNumber1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        accountNumber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountNumber1ActionPerformed(evt);
            }
        });

        okTickLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        okTickLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/ok.png"))); // NOI18N

        javax.swing.GroupLayout accountNumberPanel1Layout = new javax.swing.GroupLayout(accountNumberPanel1);
        accountNumberPanel1.setLayout(accountNumberPanel1Layout);
        accountNumberPanel1Layout.setHorizontalGroup(
            accountNumberPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountNumberPanel1Layout.createSequentialGroup()
                .addComponent(accountNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okTickLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        accountNumberPanel1Layout.setVerticalGroup(
            accountNumberPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(accountNumber1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(okTickLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        accountMessage1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        receiverCheckButton.setText("Check");
        receiverCheckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiverCheckButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Account  Name:");

        recieverAccountName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        recieverAccountName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Ref  No :");

        referrenceNumber1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Referrence Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        referenceNumberDisplay.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout referrenceNumber1Layout = new javax.swing.GroupLayout(referrenceNumber1);
        referrenceNumber1.setLayout(referrenceNumber1Layout);
        referrenceNumber1Layout.setHorizontalGroup(
            referrenceNumber1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(referrenceNumber1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(referenceNumberDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
        );
        referrenceNumber1Layout.setVerticalGroup(
            referrenceNumber1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(referenceNumberDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        amountField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        amountField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        resetButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        transferButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        transferButton.setText("Transfer");
        transferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferButtonActionPerformed(evt);
            }
        });

        dateAndTime.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Day Of Transaction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        time.setText("------null--------");

        javax.swing.GroupLayout dateAndTimeLayout = new javax.swing.GroupLayout(dateAndTime);
        dateAndTime.setLayout(dateAndTimeLayout);
        dateAndTimeLayout.setHorizontalGroup(
            dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
        );
        dateAndTimeLayout.setVerticalGroup(
            dateAndTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        horizontalglue.setText("@scitonics.com");

        accountBalanceLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        accountBalanceLabel.setText("Account Balance");

        senderAccountName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        senderAccountName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Amount");

        amountLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        receiverAccountBalance.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        receiverAccountBalance.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        accountBalanceLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        accountBalanceLabel1.setText("Account Balance");

        receiverMessage.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TSHS" }));
        jComboBox1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topSeparator)
            .addComponent(topSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(accountBalanceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(senderAccountBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(referrenceNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amountField))
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(senderAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(accountNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ckeckButton)
                                            .addComponent(accountMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(transferButton)
                            .addComponent(customerSenderPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(exhangeLabel))
                    .addComponent(horizontalglue, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recieverAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(accountNumberPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(receiverCheckButton)
                                    .addComponent(accountMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))))
                        .addGap(125, 125, 125))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(referrenceNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(accountBalanceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(receiverAccountBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dateAndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(receiverMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(customerReceiverPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(topSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(receiverMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(accountMessage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(receiverCheckButton))
                                    .addComponent(accountNumberPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(recieverAccountName, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(customerReceiverPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(receiverAccountBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(accountBalanceLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(referrenceNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(dateAndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exhangeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(accountMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ckeckButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(22, 22, 22)
                                    .addComponent(accountNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(customerSenderPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(senderAccountName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(accountBalanceLabel)
                            .addComponent(senderAccountBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(referrenceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(transferButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(horizontalglue)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exhangeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exhangeLabelMouseExited
        // TODO add your handling code here:
        exhangeLabel.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_exhangeLabelMouseExited

    private void exhangeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exhangeLabelMouseEntered
        // TODO add your handling code here:
        exhangeLabel.setForeground(new Color(0,153,51));
    }//GEN-LAST:event_exhangeLabelMouseEntered

    private void ckeckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckeckButtonActionPerformed
        // TODO add your handling code here:
        this.senderCheckButton();
    }//GEN-LAST:event_ckeckButtonActionPerformed

    private void accountNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNumberActionPerformed
        // TODO add your handling code here:
        this.senderCheckButton();
    }//GEN-LAST:event_accountNumberActionPerformed

    private void accountNumber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountNumber1ActionPerformed
        // TODO add your handling code here:
        this.receiverCheckButton();
    }//GEN-LAST:event_accountNumber1ActionPerformed

    private void receiverCheckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiverCheckButtonActionPerformed
        // TODO add your handling code here:
        this.receiverCheckButton();
    }//GEN-LAST:event_receiverCheckButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        int option=JOptionPane.showConfirmDialog(null,"Do you Need to Reset","Reset info",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(option==JOptionPane.YES_OPTION){
            receiverReset();
            senderReset();
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void transferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferButtonActionPerformed
        // TODO add your handling code here:
        double amount=0.0;
        boolean done=true;
        String sender_account=accountNumber.getText();
        String receiver_account=accountNumber1.getText();
        String reference_number=referenceNumberDisplay.getText();
        String tm=time.getText();
        if(amountField.getText().isEmpty()){
            amountLabel.setText("*Fill this field");
        }else{
            amountLabel.setText("");
            try{
                amount=Double.parseDouble(amountField.getText());
            }catch(HeadlessException | NumberFormatException ex){
                amountLabel.setText("Bad Format");
                done=false;
                amountField.setText("");
            }
            try{
                //for sender
                double accountBalance=sql.selectAccountBalance(sender_account);
                double newSenderBalance=accountBalance-amount;
                //For receiver
                double accBalance=sql.selectAccountBalance(receiver_account);
                double newReceiverBalance=accBalance+amount;
                //check if there is 
                if(newSenderBalance<0.0){
                    amountLabel.setText("No enough Money");
                    amountField.setText("");
                }
                else{
                    if(done){
                        int option=JOptionPane.showConfirmDialog(null,"Confirm Money Transfer","Transfer info",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(option==JOptionPane.YES_OPTION){
                            amountLabel.setText("");
                            if(sender_account.equals(receiver_account)){
                                sql.insertIntoTransfer(reference_number, sender_account, receiver_account,tm, amount);
                                sql.updateBalance(accountBalance,sender_account);
                                JOptionPane.showMessageDialog(null,"Transfer Success","Transfer info",JOptionPane.INFORMATION_MESSAGE);
                                senderReset();
                                accountNumber.setText("");
                                receiverReset();   
                            }else{
                                sql.insertIntoTransfer(reference_number, sender_account, receiver_account,tm, amount);
                                sql.updateBalance(newSenderBalance,sender_account);
                                sql.updateBalance(newReceiverBalance,receiver_account);
                                JOptionPane.showMessageDialog(null,"Transfer Success","Transfer info",JOptionPane.INFORMATION_MESSAGE);
                                senderReset();
                                accountNumber.setText("");
                                receiverReset();
                            }
                        }
                    }
                }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
            
        }
        
    }//GEN-LAST:event_transferButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        new BankHome().setVisible(true);
        dispose();
    }//GEN-LAST:event_homeButtonActionPerformed

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
                new TransferForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountBalanceLabel;
    private javax.swing.JLabel accountBalanceLabel1;
    private javax.swing.JLabel accountMessage;
    private javax.swing.JLabel accountMessage1;
    private javax.swing.JTextField accountNumber;
    private javax.swing.JTextField accountNumber1;
    private javax.swing.JPanel accountNumberPanel;
    private javax.swing.JPanel accountNumberPanel1;
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JButton ckeckButton;
    private javax.swing.JLabel customerReceiverPicture;
    private javax.swing.JLabel customerSenderPicture;
    private javax.swing.JPanel dateAndTime;
    private javax.swing.JLabel exhangeLabel;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel horizontalglue;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel okTick;
    private javax.swing.JLabel okTickLabel1;
    private javax.swing.JLabel receiverAccountBalance;
    private javax.swing.JButton receiverCheckButton;
    private javax.swing.JLabel receiverMessage;
    private javax.swing.JLabel recieverAccountName;
    private javax.swing.JLabel referenceNumberDisplay;
    private javax.swing.JLabel referenceNumberDisplay1;
    private javax.swing.JPanel referrenceNumber;
    private javax.swing.JPanel referrenceNumber1;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel senderAccountBalance;
    private javax.swing.JLabel senderAccountName;
    private javax.swing.JLabel time;
    private javax.swing.JSeparator topSeparator;
    private javax.swing.JSeparator topSeparator1;
    private javax.swing.JButton transferButton;
    // End of variables declaration//GEN-END:variables
}
