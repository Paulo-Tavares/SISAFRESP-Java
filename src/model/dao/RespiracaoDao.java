/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Respiracao;

/**
 *
 * @author Nando Tavares
 */
public class RespiracaoDao {
    
    public boolean save (Respiracao r){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO respiracao (qntdRespiracoes, sensorAddress) VALUES (?,?)");
            stmt.setInt(1, r.getQntdRespiracoes());
            stmt.setString(2, r.getEndSensor());
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            
            System.err.println("Erro ao salvar" + ex);
            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    public void delete (Respiracao r){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM respiracao WHERE idresp = ?");
            stmt.setInt(1, r.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    } 
    
    public List<Respiracao> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Respiracao> respiracoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM respiracao ORDER BY idresp DESC");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Respiracao respiracao = new Respiracao();
                respiracao.setId(rs.getInt("idresp"));
                respiracao.setQntdRespiracoes(rs.getInt("qntdRespiracoes"));
                respiracao.setData(rs.getTimestamp("datatemp"));
                respiracao.setEndSensor(rs.getString("sensorAddress"));
                respiracoes.add(respiracao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RespiracaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return respiracoes;
    }
    
    public int qntdRegistros(String r){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int quantidade = 0;
        
        try {
            
            stmt = con.prepareStatement("SELECT COUNT(idresp) from respiracao where sensorAddress = " + r);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                quantidade = rs.getInt("COUNT(idresp)");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir número de registros: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return quantidade;
    } 
    
   /* public static void main(String[] args) {
        
        Respiracao resp = new Respiracao();
        resp.setQntdRespiracoes(60);
        
        RespiracaoDao dao = new RespiracaoDao();
        dao.save(resp);
        if(dao.save(resp)){
            System.out.println("Salvo com sucesso!");
        }
        else{
            System.err.println("Erro ao salvar!");
        } 
    } */
}
