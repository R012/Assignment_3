/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_interface;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author AIMX
 */
public class Interface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     */
    private DefaultTableModel model;
    private String usrName, passwrd;
    private Connection conn = null;
    
    public Interface() {
        initComponents();
        this.model = (DefaultTableModel) this.queryTable.getModel();
        this.emptyTheTable();   //Empties the table contents
        this.connectToDB();
    }
    
    public void connectToDB(){
        JLabel jUserName = new JLabel("User Name");
        JTextField userName = new JTextField();
        JLabel jPassword = new JLabel("Password");
        JTextField password = new JPasswordField();
        
        //Prepares and shows the dialog asking for username and password.
        Object[] ob = {jUserName, userName, jPassword, password};
        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
        

        if((userName.getText() != null) && (userName.getText().length() > 0)) {
            if((password.getText() != null) && (password.getText().length() > 0)){
                try {
                    conn = getConnection(userName.getText(), password.getText());
                    if(conn == null)
                    {
                        throw new NullPointerException();
                    }
                    this.stateLabel.setText("Connected");   //Change the label of the connection state.
                    this.stateLabel.setForeground(Color.green);
                } catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }else{
            result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
        }
    }
    
    public void emptyTheTable(){    //Function to empty the table.
        this.model.setColumnCount(0);
        this.model.setRowCount(0);
    }
    
    private static Connection getConnection(String userName, String password) //Function to connect to the DB.
            throws SQLException, ClassNotFoundException
    {
        String driver = "org.postgresql.Driver";
        Class.forName(driver);
        System.out.println(DriverManager.getDrivers().nextElement().toString());
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", userName, password);
        return conn;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        queryTable = new javax.swing.JTable();
        textInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        queryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(queryTable);

        textInput.setBackground(new java.awt.Color(226, 255, 179));

        jButton1.setBackground(new java.awt.Color(84, 191, 233));
        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("State:");

        stateLabel.setForeground(new java.awt.Color(255, 0, 0));
        stateLabel.setText("Disconnected");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(0, 53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(stateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String statement = this.textInput.getText();  //Gets the user input.
        if(this.conn!=null){
            try{
                this.emptyTheTable();
                conn.setAutoCommit(false);
                System.out.println("Query: " + statement);
                 if(statement.toLowerCase().equals("exit"))
                    conn.close();
                Statement st = conn.createStatement();
                //st.clearBatch();
                ResultSet rs = st.executeQuery(statement);
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                TableColumn column = new TableColumn();
                int aux = 0;
                while(rs.next())
                {
                    ArrayList<Object> arrayList = new ArrayList<>();
                    for(int i=1; i<=count; i++){
                        if(rs.isFirst()){
                            this.model.addColumn(rsmd.getColumnName(i));
                            this.queryTable.setModel(this.model);
                        }
                        
                            arrayList.add(rs.getObject(i));
                        
                    }
                    //Collections.reverse(arrayList);
                    this.model.insertRow(aux, arrayList.toArray());
                    aux++;
                }
            }catch(java.sql.SQLException se)
            {
                System.out.println(se.getCause()+" -> "+
                        se.getErrorCode()+": "+se.getMessage()+" / "+se.getSQLState());
                try {
                    this.conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                this.connectToDB();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable queryTable;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JTextField textInput;
    // End of variables declaration//GEN-END:variables
}