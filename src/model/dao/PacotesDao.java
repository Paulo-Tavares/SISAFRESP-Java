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
import javax.swing.JOptionPane;
import model.bean.Pacotes;
import model.bean.Respiracao;

/**
 *
 * @author Nando Tavares
 */
public class PacotesDao {
    
    public boolean save (Pacotes p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO pacotes (enderecoSensor) VALUES (?)");
            stmt.setString(1, p.getEnderecoSensor());
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            
            System.err.println("Erro ao salvar" + ex);
            
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public int qntdRegistros(String p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int quantidade = 0;
        
        try {
            
            stmt = con.prepareStatement("SELECT COUNT(id) from pacotes where enderecoSensor = " + p);
            rs = stmt.executeQuery();
            //int quantidade = Integer.parseInt(rs.toString());
            
            while (rs.next()) {
                quantidade = rs.getInt("COUNT(id)");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir número de registros: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return quantidade;
    } 
}
