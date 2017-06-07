/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
//teste
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Pacotes;
import model.bean.Respiracao;
import model.dao.PacotesDao;
import model.dao.RespiracaoDao;
import util.ArduinoSerial;

/**
 *
 * @author Nando Tavares
 */
public class ViewRespiracao extends javax.swing.JFrame {

    ArduinoSerial arduino = new ArduinoSerial("COM7");
    int parado = 1;
    String pacote=null;
    
    public ViewRespiracao() {
        
        initComponents();
        
        DefaultTableModel modelo = (DefaultTableModel) jTableDados.getModel();
        jTableDados.setRowSorter(new TableRowSorter(modelo));
        
        readJTable();
   
        Thread t = new Thread(){
                @Override
                public void run() {

                    //arduino.initialize();
                    lbRespiração.setVisible(false);
                   
                    while (true) {                    

                        lbRespiração.setText(arduino.read());
                        
                        if(arduino.read() != null){

                            RespiracaoDao dao = new RespiracaoDao();
                            Respiracao resp = new Respiracao();
                            
                            PacotesDao pdao = new PacotesDao();
                            Pacotes pac = new Pacotes();
                            
                            pacote = arduino.read();
                            String dados[] = pacote.split(";");
                            
                            resp.setQntdRespiracoes(Integer.parseInt(dados[0]));
                            resp.setEndSensor(dados[1]);

                            if(parado == 0) {
                                if(dao.save(resp)){
                                    readJTable();
                                    
                                    pac.setEnderecoSensor(dados[1]);
                                    pdao.save(pac);
                                    
                                    System.out.println("Salvo!");
                                }else{
                                    
                                    pac.setEnderecoSensor(dados[1]);
                                    pdao.save(pac);
                                    
                                    System.out.println("Erro!");
                                }
                            }
                            
                            arduino.setInputLine(null);
    
                        }

                    }
                }
        };
        t.start(); 
    }/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDados = new javax.swing.JTable();
        jButtonFimTransmissao = new javax.swing.JButton();
        jButtonInicioTransmissao = new javax.swing.JButton();
        lbRespiração = new javax.swing.JLabel();
        jButtonExportar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonPerdaPacotes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Número de Respirações");

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Sistema Aferidor de Freqûencia RespiraTória (Sisafresp)");

        jTableDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Num. de Respiracoes", "Data e Hora", "End. Sensor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDados);

        jButtonFimTransmissao.setText("Parar Transmissão");
        jButtonFimTransmissao.setEnabled(false);
        jButtonFimTransmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFimTransmissaoActionPerformed(evt);
            }
        });

        jButtonInicioTransmissao.setText("Iniciar Trasmissão");
        jButtonInicioTransmissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioTransmissaoActionPerformed(evt);
            }
        });

        lbRespiração.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jButtonExportar.setText("Exportar");
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonPerdaPacotes.setText("Recebimento de Pacotes");
        jButtonPerdaPacotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPerdaPacotesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lbRespiração, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInicioTransmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFimTransmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButtonExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonPerdaPacotes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRespiração, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonInicioTransmissao)
                        .addComponent(jButtonFimTransmissao)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPerdaPacotes)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonExcluir)
                        .addComponent(jButtonExportar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioTransmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioTransmissaoActionPerformed
        jButtonInicioTransmissao.setEnabled(false);
        jButtonFimTransmissao.setEnabled(true);
        jButtonExportar.setEnabled(false);
        jButtonExcluir.setEnabled(false);
        jButtonPerdaPacotes.setEnabled(false);
        arduino.initialize();
        parado = 0;
    }//GEN-LAST:event_jButtonInicioTransmissaoActionPerformed

    private void jButtonFimTransmissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFimTransmissaoActionPerformed
        arduino.close();
        parado = 1;
        jButtonInicioTransmissao.setEnabled(true);
        jButtonFimTransmissao.setEnabled(false);
        jButtonExportar.setEnabled(true);
        jButtonExcluir.setEnabled(true);
        jButtonPerdaPacotes.setEnabled(true);
    }//GEN-LAST:event_jButtonFimTransmissaoActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(jTableDados);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            int len = filename.length();
            String ext = "";
            String file = "";
            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }
            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            } else {
                file = path + "\\" + filename + ".xls";
            }
            toExcel(jTableDados, new File(file));
        }       
    }//GEN-LAST:event_jButtonExportarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        int resposta = 0;
        if(jTableDados.getSelectedRow() != -1){
            Respiracao r = new Respiracao();
            RespiracaoDao dao = new RespiracaoDao();
            
            r.setId((int) jTableDados.getValueAt(jTableDados.getSelectedRow(), 0));
            
            resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente excluir?");
            if(resposta == JOptionPane.YES_OPTION){
            dao.delete(r);
            readJTable();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um dado para excluir.");
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonPerdaPacotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPerdaPacotesActionPerformed
        EnvioPacotes tela = new EnvioPacotes();
        tela.setVisible(true);
    }//GEN-LAST:event_jButtonPerdaPacotesActionPerformed

    public void readJTable(){
        DefaultTableModel modelo = (DefaultTableModel) jTableDados.getModel();
        modelo.setNumRows(0);
        RespiracaoDao rdao = new RespiracaoDao();
        
        for(Respiracao r: rdao.read()){
            modelo.addRow(new Object[]{
                r.getId(),
                r.getQntdRespiracoes(),
                r.getData(),
                r.getEndSensor()
            });
        }
    }
    
    public void toExcel(JTable table, File file){
        try{
            TableModel model = jTableDados.getModel();
            FileWriter excel = new FileWriter(file);

            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j).toString()+"\t");
                }
                excel.write("\n");
            }

            excel.close();

        }catch(IOException e){
            System.out.println(e); 
        }
    }
    
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
            java.util.logging.Logger.getLogger(ViewRespiracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRespiracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRespiracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRespiracao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRespiracao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JButton jButtonFimTransmissao;
    private javax.swing.JButton jButtonInicioTransmissao;
    private javax.swing.JButton jButtonPerdaPacotes;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDados;
    private javax.swing.JLabel lbRespiração;
    // End of variables declaration//GEN-END:variables
}
