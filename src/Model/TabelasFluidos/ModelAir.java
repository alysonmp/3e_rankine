/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.TabelasFluidos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author leonardo
 */
@Entity
@Table(name = "AIR")
public class ModelAir implements Serializable{
    
    @Id
    @GeneratedValue
    private int ID;
    
    @Column
    private double pressao;
    
    @Column
    private double temperatura;
    
    @Column
    private double kv;
    
    @Column
    private double Cpv;
    
    @Column
    private double Prv;
    
    @Column
    private double Muv;

    @Column
    private double Vcv;
    
    @Column
    private double Dfv;

    public ModelAir(double pressao, double temperatura, double Cpv, double Prv, double kv, double Muv, double Vcv, double Dfv) {
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.kv = kv;
        this.Cpv = Cpv;
        this.Prv = Prv;
        this.Muv = Muv;
        this.Vcv = Vcv;
        this.Dfv = Dfv;
    }
    
    public ModelAir(double pressao, double temperatura) {
        this.pressao = pressao;
        this.temperatura = temperatura;
    }

    public ModelAir() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPRESSAO() {
        return pressao;
    }

    public void setPRESSAO(double pressao) {
        this.pressao = pressao;
    }

    public double getTEMPERATURA() {
        return temperatura;
    }

    public void setTEMPERATURA(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getCPV() {
        return Cpv;
    }

    public void setCPV(double Cpv) {
        this.Cpv = Cpv;
    }

    public double getPRV() {
        return Prv;
    }

    public void setPRV(double Prv) {
        this.Prv = Prv;
    }
    
    public double getKV() {
        return kv;
    }

    public void setKV(double kv) {
        this.kv = kv;
    }

    public double getMUV() {
        return Muv;
    }

    public void setMUV(double Muv) {
        this.Muv = Muv;
    }

    public double getVCV() {
        return Vcv;
    }

    public void setVCV(double Vcv) {
        this.Vcv = Vcv;
    }

    public double getDFV() {
        return Dfv;
    }

    public void setDFV(double Dfv) {
        this.Dfv = Dfv;
    }
}
