/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Practica1_8;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Administrator
 */

public class VentanaVerificar extends javax.swing.JFrame {
    
    private String textoUsuario;
    private String textoContraseña;
    /**
     * Creates new form VentanaVerificar
     */
    public VentanaVerificar() {
        initComponents();
    }
    
    protected Connection getConnection() throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/gestionusuarios";
        String username = "root";
        String password = "";
        Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/gestionusuarios", "root", "");
        return con;
    }
    
    
    public void enviar(String instruccion) throws SQLException {
        Statement st = null;
        try {
            Connection con = getConnection();
            String query = instruccion;
            st = (Statement) con.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            st.close();
        }
    }
    
    public void crearUsuario() throws SQLException{
        textoUsuario = usuario.getText();
        
        if(comprobarUsuarioExistente(devolverListaUsuarios(), textoUsuario)){
            String passString = new String(contraseña.getPassword());
            String instruccion = "insert into usuarios values(null, \""+textoUsuario+"\",\""+passString+"\")";
            enviar(instruccion);
            JOptionPane.showMessageDialog(rootPane, "Usuario: " + textoUsuario + "\ncreado con éxito");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Usuario ya existente:" + textoUsuario);
        }
    }
    
    public void cambiarContraseña() throws SQLException, Exception{
        textoUsuario = usuario.getText();

        if(comprobarUsuarioExistente(devolverListaUsuarios(), textoUsuario)){
            JOptionPane.showMessageDialog(rootPane, "Usuario :" + textoUsuario+"\nNo Existente");
        }else if(comprobarUsuario()==1){
            String primer="";
            String segundo="";
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Contraseña:");
            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"OK", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, panel, "Elija su contraseña",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if(option == 0) // pressing OK button
            {
                char[] password = pass.getPassword();
                primer = new String(password);
            }
            pass.setText("");
            option = JOptionPane.showOptionDialog(null, panel, "Elija su contraseña",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if(option == 0) // pressing OK button
            {
                char[] password = pass.getPassword();
                segundo = new String(password);
            }
            if(primer.equals(segundo) && (!primer.equals("") || !segundo.equals(""))){
                textoUsuario = "\"" + usuario.getText() + "\"";
                String instruccion = "update usuarios set contraseña=\""+primer+"\" where nombre="+textoUsuario;
                enviar(instruccion);
                JOptionPane.showMessageDialog(rootPane, "Contraseña cambiada correctamente:" + textoUsuario);
                Reiniciar();
            }else{
                JOptionPane.showMessageDialog(rootPane, "Contraseña no coincide");
                Reiniciar();
            }
        }
    }
    
    public int comprobarUsuario() throws Exception{
        textoUsuario = "\"" + usuario.getText() + "\"";
        String passString = new String(contraseña.getPassword());
        String contraseña2 = conectar("select contraseña from usuarios where nombre="+textoUsuario);
        if(contraseña2.equals(passString)){
           JOptionPane.showMessageDialog(rootPane, "Bienvenido: " + textoUsuario);
           return 1;
        }else{
            JOptionPane.showMessageDialog(rootPane, "Usuario o contraseña equivocadas");
            Reiniciar();
            return -1;
        }
    }
    
    public void Reiniciar(){
        usuario.setText("");
        contraseña.setText("");
        reiniciarBotones();
        usuario.requestFocus();
    }
    
    public String conectar(String query) {
        String cadena = "";
        try {
            Connection conexion = getConnection();
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cadena += rs.getString(1);
                rs.next();
            }
        } catch (Exception e) {
        }
        return cadena;
    }
    
    public void validarContraseña(){
        textoContraseña = new String(contraseña.getPassword());
        if(textoContraseña.matches("[0-9a-zA-Z]+")){
            JOptionPane.showMessageDialog(rootPane, "Contraseña No cumple: XXX");
        }
    }
    
    public LinkedList devolverListaUsuarios() {
        String cadena = "";
        String query = "select nombre from usuarios";
        LinkedList<String> lista = new LinkedList<>();
        try {
            Connection conexion = getConnection();
            Statement st = (Statement) conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cadena += rs.getString(1);
                lista.add(cadena);
                rs.next();
            }
        } catch (Exception e) {
        }
        return lista;
    }
    
    public boolean comprobarUsuarioExistente(LinkedList<String> lista, String usuario){
        System.out.println(usuario);
        if(lista.indexOf(usuario) == -1){
            return false;
        }
        return true;
    }
    
    public void reiniciarBotones(){
        btnIngresar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnCambioContraseña.setEnabled(false);
        btnNuevoUsuario.setEnabled(false);
    }
    
    public void verificarCamposVacios(){
        if(usuario.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "El campo usuario no debe estar vacio");
            reiniciarBotones();
        }
        if(contraseña.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "El campo contraseña no debe estar vacio");
            reiniciarBotones();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        contraseña = new javax.swing.JPasswordField();
        btnCancelar = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        btnCambioContraseña = new javax.swing.JButton();
        btnNuevoUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Control de Acceso");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Usuario: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Contraseña");

        contraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contraseñaFocusLost(evt);
            }
        });
        contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contraseñaKeyPressed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnIngresar.setText("Ingresar");
        btnIngresar.setEnabled(false);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCambioContraseña.setText("Cambiar Contraseña");
        btnCambioContraseña.setEnabled(false);
        btnCambioContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioContraseñaActionPerformed(evt);
            }
        });

        btnNuevoUsuario.setText("Nuevo Usuario");
        btnNuevoUsuario.setEnabled(false);
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCambioContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usuario)
                                    .addComponent(contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCambioContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            comprobarUsuario();
        } catch (Exception ex) {
            Logger.getLogger(VentanaVerificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Reiniciar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        try {
            crearUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaVerificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void btnCambioContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioContraseñaActionPerformed
        try {
            cambiarContraseña();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaVerificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VentanaVerificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCambioContraseñaActionPerformed

    private void contraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseñaKeyPressed

        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnIngresar.setEnabled(true);
            btnCancelar.setEnabled(true);
            btnCambioContraseña.setEnabled(true);
            btnNuevoUsuario.setEnabled(true);
            verificarCamposVacios();
        }
    }//GEN-LAST:event_contraseñaKeyPressed

    private void contraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFocusLost
        //validarContraseña();
    }//GEN-LAST:event_contraseñaFocusLost
    
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
            java.util.logging.Logger.getLogger(VentanaVerificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVerificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVerificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVerificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaVerificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambioContraseña;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JPasswordField contraseña;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
