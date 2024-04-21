/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Oni145
 */

public class Levelselect extends javax.swing.JFrame {

    /**
     * Creates new form Mainframe
     */
    public Levelselect() {
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG = new javax.swing.JPanel();
        beginner = new javax.swing.JToggleButton();
        intermediate = new javax.swing.JToggleButton();
        expert = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        beginner.setBackground(new java.awt.Color(255, 102, 0));
        beginner.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        beginner.setForeground(new java.awt.Color(0, 0, 0));
        beginner.setText("BEGINNER");
        beginner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginnerActionPerformed(evt);
            }
        });
        BG.add(beginner, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 560, 110));

        intermediate.setBackground(new java.awt.Color(255, 102, 0));
        intermediate.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        intermediate.setForeground(new java.awt.Color(0, 0, 0));
        intermediate.setText("EXPERT");
        intermediate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intermediateActionPerformed(evt);
            }
        });
        BG.add(intermediate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 560, 120));

        expert.setBackground(new java.awt.Color(255, 102, 0));
        expert.setFont(new java.awt.Font("Segoe UI Semibold", 0, 48)); // NOI18N
        expert.setForeground(new java.awt.Color(0, 0, 0));
        expert.setText("INTERMEDIATE");
        expert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expertActionPerformed(evt);
            }
        });
        BG.add(expert, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 560, 120));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 78)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PIXEL PUZZLER");
        BG.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, -70, 630, 300));

        jToggleButton1.setBackground(new java.awt.Color(255, 102, 0));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton1.setText("BACK");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        BG.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 200, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Oni\\Desktop\\Photoshops Edits\\BGJ.png")); // NOI18N
        jLabel1.setText("jLabel1");
        BG.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void beginnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginnerActionPerformed
        {
    PuzzleGame pi = new PuzzleGame ();
    dispose(); // Close the current window
    pi.setVisible(true); // Show the new window
};

    }//GEN-LAST:event_beginnerActionPerformed

    private void intermediateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intermediateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intermediateActionPerformed

    private void expertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expertActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
       Mainframe pi = new Mainframe ();
   dispose();
    pi.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG;
    private javax.swing.JToggleButton beginner;
    private javax.swing.JToggleButton expert;
    private javax.swing.JToggleButton intermediate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
