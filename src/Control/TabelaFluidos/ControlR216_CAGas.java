/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.TabelaFluidos;

import Model.TabelasFluidos.ModelR216_CAGas;
import Model.TabelasFluidos.ModelR216_CAGas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 *
 * @author leonardo
 */
public class ControlR216_CAGas {
    private Session session;
    private double kv, Cpv, Prv, Muv, Vcv;
    private double kv1, kv2, Cpv1, Cpv2, Prv1, Prv2, Muv1, Muv2, Vcv1, Vcv2;

    private ModelR216_CAGas r216_ca_g1;
    private ModelR216_CAGas r216_ca_g2;
    private ModelR216_CAGas r216_ca_g3;
    private ModelR216_CAGas r216_ca_g4;
    
    public ControlR216_CAGas(Session session) {
        this.session = session;
    }
    
    public void criaTabelaR260_CAGas(){
        String csvFile = "/Csv/R216_ca_gas.csv";
        InputStream is = getClass().getResourceAsStream(csvFile);
        
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ";";
      
        try{
            Criteria cr = this.session.createCriteria(ModelR216_CAGas.class);
            List results = cr.list();
            
            if(results.isEmpty()){
                br = new BufferedReader(new InputStreamReader(is));
                line = br.readLine();
                while((line = br.readLine()) != null){
                    String[] r216_ca_g = line.split(csvSplitBy);
                    
                    this.session.save(new ModelR216_CAGas(Double.parseDouble(r216_ca_g[0]),Double.parseDouble(r216_ca_g[1]),Double.parseDouble(r216_ca_g[4]),Double.parseDouble(r216_ca_g[2]),Double.parseDouble(r216_ca_g[3]),Double.parseDouble(r216_ca_g[5]),Double.parseDouble(r216_ca_g[6])));   
                }
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void interpolacao(double pressao,double temperatura){
        Criteria cr = this.session.createCriteria(ModelR216_CAGas.class);
        
        temperatura -= 1;
        do{
            temperatura += 1;
            SQLQuery consulta = this.session.createSQLQuery("select * from r216_ca_gas where pressao <= " +pressao+ " and temperatura <= " +temperatura+ " ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelR216_CAGas.class));
            List<ModelR216_CAGas> r216_ca_g = consulta.list();
            if(!r216_ca_g.isEmpty())
                r216_ca_g1 = r216_ca_g.get(0);

            consulta = this.session.createSQLQuery("select * from r216_ca_gas where pressao <= "+pressao+" and temperatura >= "+temperatura+" ORDER BY PRESSAO DESC, TEMPERATURA ASC FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelR216_CAGas.class));
            r216_ca_g = consulta.list();
            if(!r216_ca_g.isEmpty())
                r216_ca_g2 = r216_ca_g.get(0);

            consulta = this.session.createSQLQuery("select * from r216_ca_gas where pressao >= "+pressao+" and temperatura <= "+temperatura+" ORDER BY PRESSAO ASC, TEMPERATURA DESC");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelR216_CAGas.class));
            r216_ca_g = consulta.list();
            if(!r216_ca_g.isEmpty())
                r216_ca_g3 = r216_ca_g.get(0);

            consulta = this.session.createSQLQuery("select * from r216_ca_gas where pressao >= " +pressao+ "and temperatura >= " +temperatura+ " FETCH FIRST 1 ROWS ONLY");
            consulta.setResultTransformer(Transformers.aliasToBean(ModelR216_CAGas.class));
            r216_ca_g = consulta.list();
            if(!r216_ca_g.isEmpty())
                r216_ca_g4 = r216_ca_g.get(0);
            
        }while(r216_ca_g1 != null || r216_ca_g2 != null || r216_ca_g3 != null || r216_ca_g4 != null);
        
        double p = ((pressao - r216_ca_g1.getPRESSAO())/(r216_ca_g3.getPRESSAO() - r216_ca_g1.getPRESSAO()));
        double t1 = ((temperatura - r216_ca_g1.getTEMPERATURA())/(r216_ca_g2.getTEMPERATURA() - r216_ca_g1.getTEMPERATURA()));
        double t2 = ((temperatura - r216_ca_g3.getTEMPERATURA())/(r216_ca_g4.getTEMPERATURA() - r216_ca_g3.getTEMPERATURA()));

        Cpv2 = r216_ca_g1.getCPV() + (r216_ca_g2.getCPV() - r216_ca_g1.getCPV()) * t1;
        Cpv2 = r216_ca_g3.getCPV() + (r216_ca_g4.getCPV() - r216_ca_g3.getCPV()) * t2;
        Cpv = Cpv1 + (Cpv2 - Cpv1) * p;
        
        Prv1 = r216_ca_g1.getPRV() + (r216_ca_g2.getPRV() - r216_ca_g1.getPRV()) * t1;
        Prv2 = r216_ca_g3.getPRV() + (r216_ca_g4.getPRV() - r216_ca_g3.getPRV()) * t2;
        Prv2 = Prv1 + (Prv2 - Prv1) * p;
        
        kv1 = r216_ca_g1.getKV() + (r216_ca_g2.getKV() - r216_ca_g1.getKV()) * t1;
        kv2 = r216_ca_g3.getKV() + (r216_ca_g4.getKV() - r216_ca_g3.getKV()) * t2;
        kv = kv1 + (kv2 - kv1) * p;
        
        Muv1 = r216_ca_g1.getMUV() + (r216_ca_g2.getMUV() - r216_ca_g1.getMUV()) * t1;
        Muv2 = r216_ca_g3.getMUV() + (r216_ca_g4.getMUV() - r216_ca_g3.getMUV()) * t2;
        Muv = Muv1 + (Muv2 - Muv1) * p;
        
        Vcv1 = r216_ca_g1.getVCV() + (r216_ca_g2.getVCV() - r216_ca_g1.getVCV()) * t1;
        Vcv2 = r216_ca_g3.getVCV() + (r216_ca_g4.getVCV() - r216_ca_g3.getVCV()) * t2;
        Vcv = Vcv1 + (Vcv2 - Vcv1) * p;
    }

    public double getKv() {
        return kv;
    }

    public void setKv(double kv) {
        this.kv = kv;
    }

    public double getCpv() {
        return Cpv;
    }

    public void setCpv(double Cpv) {
        this.Cpv = Cpv;
    }

    public double getPrv() {
        return Prv;
    }

    public void setPrv(double Prv) {
        this.Prv = Prv;
    }

    public double getMuv() {
        return Muv;
    }

    public void setMuv(double Muv) {
        this.Muv = Muv;
    }

    public double getVcv() {
        return Vcv;
    }

    public void setVcv(double Vcv) {
        this.Vcv = Vcv;
    }
}
