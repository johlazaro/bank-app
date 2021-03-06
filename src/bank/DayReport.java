/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John T Lazaro
 */
public class DayReport extends javax.swing.JPanel {

    /**
     * Creates new form DayReport
     */
    private Sql sql;
    private DefaultTableModel model;
    private ArrayList<Customer> customerlist;
    private Object[] row;
    public DayReport() {
        initComponents();
        this.printDetails();
        
    }
    
    public void printDetails(){
        customerlist=new ArrayList<>();
        sql=new Sql();
        customerlist=sql.getAllCustomerRegistered();
        model=(DefaultTableModel)dayReportTable.getModel();
        model.setRowCount(0);
        row=new Object[8];
        for(int i=0;i<customerlist.size();i++){
           row[0]=customerlist.get(i).getFname();
           row[1]=customerlist.get(i).getMname();
           row[2]=customerlist.get(i).getSurname();
           row[3]=customerlist.get(i).getDateOfBirth();
           row[4]=customerlist.get(i).getGander();
           row[5]=customerlist.get(i).getAccountName();
           row[6]=customerlist.get(i).getAccountNumber();
           row[7]=customerlist.get(i).getCardNumber();
           model.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        dayReportTable = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(966, 553));

        dayReportTable.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        dayReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "FirstName", "MiddlName", "SurName", "gender", "Date Of Birth", "AccountName", "AccountNumber", "CardNumber", "Date Registered", "Status"
            }
        ));
        jScrollPane1.setViewportView(dayReportTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dayReportTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
