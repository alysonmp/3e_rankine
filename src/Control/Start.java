package Control;

import Control.Ciclo2.ControlAreas;
import Control.Ciclo2.ControlBalanco;
import Control.Ciclo2.ControlBomba;
import Control.Ciclo2.ControlConeff;
import Control.Ciclo2.ControlEvpeff;
import Control.Ciclo2.ControlMassa;
import Control.Ciclo2.ControlMassa1;
import Control.Ciclo2.ControlParametros;
import Control.Ciclo2.ControlRegeff;
import Control.Ciclo2.ControlRegenerador;
import Control.Ciclo2.ControlSF;
import Control.Ciclo2.ControlTurbina;
import Control.Conversao.ControlConverte;
import View.ViewSaida;

import javax.swing.JOptionPane;
import org.hibernate.Session;


public class Start {
    
    private Session session;
    
    //public Start(int compressor, int flu, double Tf, double Pf, double SUP, double PINCH, double Tconop, double eff, double Ve, Session session, ControlPrincipal ctrlPrincipal){
    //public void iniciaCalculos(int compressor, int flu, double Tf, double Pf, double mf, double SUP, double PINCH, double Tconop, double eff, double km, int FON, double Beff, double Teff, double T1, double P1){
    public Start(Session session, ControlPrincipal ctrlPrincipal){
    
        ControlConverte converte = new ControlConverte();
        
        double eff = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldRegEff().getText());
        double Beff = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtBeff().getText());
        double Teff = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtTeff().getText());
        
        int compressor = ctrlPrincipal.getViewPrincipal().getFonteCalor().getComp();
        
        double Tf;
        if(!ctrlPrincipal.getViewPrincipal().getFonteCalor().getComboTf().getSelectedItem().toString().equals("K")){
            Tf = converte.converte(ctrlPrincipal.getViewPrincipal().getFonteCalor().getComboTf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrlPrincipal.getViewPrincipal().getFonteCalor().getTxtTf().getText()));
        }else{
            Tf = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getFonteCalor().getTxtTf().getText());
        }
        
        double Pf;
        if(!ctrlPrincipal.getViewPrincipal().getFonteCalor().getComboPf().getSelectedItem().toString().equals("K")){
            Pf = converte.converte(ctrlPrincipal.getViewPrincipal().getFonteCalor().getComboPf().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrlPrincipal.getViewPrincipal().getFonteCalor().getTxtPf().getText()));
        }else{
            Pf = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getFonteCalor().getTxtPf().getText());
        }
        
        double mf = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getFonteCalor().getTxtMf().getText());
        
        double P1;
        if(!ctrlPrincipal.getViewPrincipal().getDadosOp().getComboP1().getSelectedItem().toString().equals("kPa")){
            P1 = converte.converte(ctrlPrincipal.getViewPrincipal().getDadosOp().getComboP1().getSelectedItem().toString(), "kPa", Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtP1().getText()));
        }else{
            P1 = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtP1().getText());
        }
        
        double PINCH = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtPINCH().getText());   
        double SUP = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtSUP().getText());
        
        double T1;
        if(!ctrlPrincipal.getViewPrincipal().getDadosOp().getComboT1().getSelectedItem().toString().equals("K")){
            T1 = converte.converte(ctrlPrincipal.getViewPrincipal().getDadosOp().getComboT1().getSelectedItem().toString(), "K", ctrlPrincipal.getViewPrincipal().getDadosOp().getT1());
        }else{
            T1 = ctrlPrincipal.getViewPrincipal().getDadosOp().getT1();
        }
        
        	T1 += SUP;
        
        
        int flu = ctrlPrincipal.getViewPrincipal().getDadosOp().getFlu();
        int FON = ctrlPrincipal.getViewPrincipal().getFonteCalor().getFON();
        
        int core1 = (int)Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldCondCore().getText());
        if(core1 < 1 || core1 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do condensador fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        int core2 = (int)Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldEvapCore().getText());
        if(core2 < 1 || core2 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do evaporador fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        int core3 = (int)Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldRegCore().getText());
        if(core3 < 1 || core3 > 15){
            JOptionPane.showMessageDialog(null, "Valor do core do regenerador fora da faixa de valores\nEntre com valores na faixa de 0 a 15", "Error", 0);
            return;
        }
        
        ControlParametros parametros = new ControlParametros(Tf, flu, session);
        if(!parametros.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,parametros.getMensagem(),"Error",0);
            return;
        }
        
        double Pe = parametros.getPe();
        double Te = parametros.getTe();
        double Tconop = parametros.getTconop();
        double Pconop = parametros.getPconop();
        double Pref = parametros.getPref();
        double Tref = parametros.getTref();
        double Tcri = parametros.getTcri();
        double Pcri = parametros.getPcri();

        if(!ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtTcon().getText().equals("")){
            Tconop = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getDadosOp().getTxtTcon().getText());
        }
        
        double G = 1;
        
        int ii = flu;
        
        //ControlT_Ref TRef = new ControlT_Ref(P1, ii, session);
        //double Tee = TRef.getTref();
        //T1 = Tee+SUP;
        double P2 = Pconop; //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Pcon È variavel
        double P4 = P2;
        double T4 = Tconop;
        double P5 = P1;
        double P3 = P2;
        double P6 = P1;
        
        double Tcontrol = Tf-T1;
        if(Tcontrol < 5){
            JOptionPane.showMessageDialog(null,"Pressão ou temperatura de vaporização elevadas \nou temperatura de superaquecimento elevada.","Error",0);
            return;
        }

        /*Tcontrol2 = Tcri-T1;
        if(Tcontrol2 <= 2){ 
           //jkljkljkljkljkl*jkljkljkjkl
        }*/
        
        if(P1 < 200){
            JOptionPane.showMessageDialog(null,"Baixa pressão de vaporização.","Error",0);
            return;
        }
        
        ControlTurbina turbina = new ControlTurbina(Teff, P1, T1, P2, Pref, Tref, ii, session);
        if(!turbina.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,turbina.getMensagem(),"Error",0);
            return;
        }
        double H1 = turbina.getH1();
        double H2 = turbina.getIsoTurbina().getH2();
        double S1 = turbina.getS1();
        double S2 = turbina.getIsoTurbina().getS2();
        double T2 = turbina.getIsoTurbina().getT2();
        double H2s = turbina.getIsoTurbina().getH2s();

        
        //QUANDO FOR AJUSTAR INTERFACE GRÁFICA, CHAMAR DIRETAMENTE A INTERPOLAÇÃO DE VAPOR OU LIQUIDO, TRECHOS 1, 2 E 3, SEMPRE VAPOR, TRECHOS 4, 5 E 6, SEMPRE LIQUIDO
        /*ControlInterpolacao interpolacao = new ControlInterpolacao(ii, Pref, Tref, session);
        double Cpv_g = interpolacao.getCpv_g();
        double Cpv_l = interpolacao.getCpv_l();
        double kv_g = interpolacao.getKv_g();
        double kv_l = interpolacao.getKv_l();
        double Muv_g = interpolacao.getMuv_g();
        double Muv_l = interpolacao.getMuv_l();
        double Prv_g = interpolacao.getPrv_g();
        double Prv_l = interpolacao.getPrv_l();
        double Vcv_g = interpolacao.getVcv_g();
        double Vcv_l = interpolacao.getVcv_l();*/
        
        double DH2s = H1-H2s;
        
        /*ControlSF sf = new ControlSF(T2, P2, ii, m, DH2s, session);
        sp = sf.getSp();
        v2 = sf.getV2();
        DHT = sf.getDHT();
        
        ControlDiamTH17 diamTH17 = new ControlDiamTH17(v2, DHT, session);
        Dr = diamTH17.getDr();
        Teff = diamTH17.getTeff();
                
        if(Teff < 0.8){
            turbina = new ControlTurbina(Teff, P1, T1, P2, Pref, Tref, ii, session);
            H1 = turbina.getH1();
            H2 = turbina.getIsoTurbina().getH2();
            S1 = turbina.getS1();
            S2 = turbina.getIsoTurbina().getS2();
            T2 = turbina.getIsoTurbina().getT2();
            H2s = turbina.getIsoTurbina().getH2s();
            
            DH2s=H1-H2s;
            
            sf = new ControlSF(T2, P2, ii, m, DH2s, session);
            sp = sf.getSp();
            v2 = sf.getV2();
            DHT = sf.getDHT();
            
            diamTH17 = new ControlDiamTH17(v2, DHT, session);
            Dr = diamTH17.getDr();
            Teff = diamTH17.getTeff();
        }*/
                    
        ControlBomba bomba = new ControlBomba(Beff, P1, T1, P4, T4, Pref, Tref, ii, session);
        double S4 = bomba.getS4();
        double H4 = bomba.getH4();
        double S5 = bomba.getS5();
        double H5 = bomba.getH5();
        double T5 = bomba.getT5();

        ControlRegenerador regenerador = new ControlRegenerador(G, H2, H5, S2, S5, P2, T2, P5, T5, P1, Pconop, Tconop, Pref, Tref, ii, eff, session);
        if(!regenerador.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,regenerador.getMensagem(),"Error",0);
            return;
        }
        double S3 = regenerador.getS3();
        double H3 = regenerador.getH3();
        double S6 = regenerador.getS6();
        double H6 = regenerador.getH6();
        double T3 = regenerador.getT3();
        double T6 = regenerador.getT6();
        double IHR = regenerador.getIHR();
        eff = IHR;

        double m;
        double Q;
        double Tfout;
        double Hlat;
        double Hsen;
        double Hsup;
        double T1s;
        double PP;
        
        if(FON == 1 || FON == 2) {
            ControlMassa1 massa = new ControlMassa1(H4, H1, H6, P1, ii, Pref, Tref, T1, T6, SUP, FON, PINCH, mf, Tf, Pf, session);

            if(!massa.getMensagem().equals("")){
                JOptionPane.showMessageDialog(null,massa.getMensagem(),"Error",0);
                return;
            }

            m = massa.getM();
            Q = massa.getQ();
            Tfout = massa.getTfout();
            Hlat = massa.getHlat();
            Hsen = massa.getHsen();
            Hsup = massa.getHsup();
            T1s = massa.getT1s();
            PP = massa.getPINCH();
        }else {
	        ControlMassa massa = new ControlMassa(H4, H1, H6, P1, ii, Pref, Tref, T1, T6, SUP, PINCH, mf, Tf, Pf, compressor, session);
	        if(!massa.getMensagem().equals("")){
	            JOptionPane.showMessageDialog(null,massa.getMensagem(),"Error",0);
	            return;
	        }
	        
	        m = massa.getM();
	        Q = massa.getQ();
	        Tfout = massa.getTfout();
	        Hlat = massa.getHlat();
	        Hsen = massa.getHsen();
	        Hsup = massa.getHsup();
	        T1s = massa.getT1s();
	        PP = massa.getPINCH();
        }
        
        
        ControlSF sf = new ControlSF(T2, P2, ii, m, H2s, session);
        double sp = sf.getSp();
        double v2 = sf.getV2();
        double DHT = sf.getDHT();

        //ControlDiamTH17 diamTH17 = new ControlDiamTH17(v2, DHT, session);
        //Dr = diamTH17.getDr();
        //Teff = diamTH17.getTeff();
        
        ControlBalanco balanco = new ControlBalanco(T1, H1, H2, H3, H4, H5, H6, S1, S2, S3, S4, S5, S6, m, Pref, Tref);
        double Wt = balanco.getWt();
        double Wb = balanco.getWb();
        double Qevp = balanco.getQevp();
        double Qcon = balanco.getQcon();
        double ec = balanco.getEc();
        double Qreg = balanco.getQreg();
        double Qreg1 = balanco.getQreg1();
        double Wn = balanco.getWn();

        ControlAreas areas = new ControlAreas(T1, T2, T3, T4, T5, T6, Tf, Tfout, Qevp, Qcon, Qreg, eff, Hlat, Hsen, Hsup, T1s, PP, SUP, m, Pref, Tref, P4, ii, H3, compressor, FON, mf, Pf , session);
        if(!areas.getMensagem().equals("")){
            JOptionPane.showMessageDialog(null,areas.getMensagem(),"Error",0);
            return;
        }
        
        double Ten = areas.getTen();
        double Pen = areas.getPen();
        double mH2O = areas.getmH2O();
        double Ten1 = areas.getTen1();
        double UACONs = areas.getUACONs();
        double UACONl = areas.getUACONl();
        double UAREG = areas.getUAREG();
        double Ts = areas.getTs();
        double Acon = areas.getAcon();
        double Aevp = areas.getAevp();
        double Areg = areas.getAreg();
        double UASUP = areas.getUASUP();
        double UASEN = areas.getUASEN();
        double UALAT = areas.getUALAT();
        double Tf1 = areas.getTf1();
        
        double ecg = ec*100;
        
        double k = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldEvapK().getText());
        
        ControlEvpeff evpeff = new ControlEvpeff(P1, P6, T1, T6, ii, SUP, Hlat, Hsen, Hsup, compressor, m, mf, T1s, Tf, Tfout, Pf, FON, PINCH, UASUP, UALAT, UASEN, k, core1, Tf1, session);
        double ATevp = evpeff.getAT();
        double Ahoevp = evpeff.getAho();
        double Acoevp = evpeff.getAco();
        double Vevp = evpeff.getVhx();
        double Lhevp = evpeff.getLh();
        double Lcevp = evpeff.getLc();
        double L3evp = evpeff.getL3();
        double DPhevp = evpeff.getDPh();
        double DPcevp = evpeff.getDPc();

        k = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldCondK().getText());
        
        ControlConeff coneff = new ControlConeff(P4, P3, m, mH2O, ii, Ten, Ten1, Ts, T3, T4, UACONs, UACONl, Pen, k, core2, SUP, session);
        double ATcon = coneff.getAT();
        double Ahocon = coneff.getAho();
        double Acocon = coneff.getAco();
        double Vcon = coneff.getVhx();
        double Lhcon = coneff.getLh();
        double Lccon = coneff.getLc();
        double L3con = coneff.getL3();
        double DPhcon = coneff.getDph();
        double DPccon = coneff.getDpc();
                
        k = Double.parseDouble(ctrlPrincipal.getViewPrincipal().getTrocadores().getFieldRegK().getText());
        
        ControlRegeff regeff = new ControlRegeff(ii, UAREG, T2, T3, T5, T6, m, eff, P5, P6, P2, P3, k, core3, session);
        double ATreg = regeff.getAT();
        double Ahoreg = regeff.getAho();
        double Acoreg = regeff.getAco();
        double Vreg = regeff.getVhx();
        double Lhreg = regeff.getLh();
        double Lcreg = regeff.getLc();
        double L3reg = regeff.getL3();
        double DPhreg = regeff.getDPh();
        double DPcreg = regeff.getDPc();
        
        ViewSaida saida = new ViewSaida();
        double AT = ATcon+ATevp+ATreg;
        
        saida.getTxtWt().setText(round(Wt, 3)+" kW");
        saida.getTxtWb().setText(round(Wb, 3)+" kW");
        saida.getTxtWn().setText(round(Wn, 3)+" kW");
        saida.getTxtQevp().setText(round(Qevp, 3)+" kJ/s");
        saida.getTxtQcon().setText(round(Qcon, 3)+" kJ/s");
        saida.getTxtQreg().setText(round(Qreg, 3)+" kJ/s");
        saida.getTxtAt().setText(round(AT, 3)+" m²");
        saida.getTxtATevp().setText(round(ATevp, 3)+" m²");
        saida.getTxtATcon().setText(round(ATcon, 3)+" m²");
        saida.getTxtATreg().setText(round(ATreg, 3)+" m²");
    }   
    
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}