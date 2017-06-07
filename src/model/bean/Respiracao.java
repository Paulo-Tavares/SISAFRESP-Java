/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Timestamp;



/**
 *
 * @author Nando Tavares
 */
public class Respiracao {
    private int id;
    private int qntdRespiracoes;
    private Timestamp data;
    private String endSensor;

    public String getEndSensor() {
        return endSensor;
    }

    public void setEndSensor(String endSensor) {
        this.endSensor = endSensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQntdRespiracoes() {
        return qntdRespiracoes;
    }

    public void setQntdRespiracoes(int qntdRespiracoes) {
        this.qntdRespiracoes = qntdRespiracoes;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    
    
}
