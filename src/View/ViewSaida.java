package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewSaida extends JFrame{
	
	JPanel painelSaida;
	
	//LABELS
	JLabel lblWt = new JLabel("<html>W<sub>t</sub></html>");
	JLabel lblWn = new JLabel("<html>W<sub>n</sub></html>");
	JLabel lblWb = new JLabel("<html>W<sub>b</sub></html>");
	JLabel lblQevp = new JLabel("<html>Q<sub>evp</sub></html>");
	JLabel lblQcon = new JLabel("<html>Q<sub>con</sub></html>");
	JLabel lblQreg = new JLabel("<html>Q<sub>reg</sub></html>");
	JLabel lblATevp = new JLabel("<html>A<sub>evp</sub></html>");
	JLabel lblATcon = new JLabel("<html>A<sub>con</sub></html>");
	JLabel lblATreg = new JLabel("<html>A<sub>reg</sub></html>");
	JLabel lblAt = new JLabel("AT");
	
	//FIELDS
	JLabel txtWt = new JLabel("0 kW");
	JLabel txtWn = new JLabel("0 kW");
	JLabel txtWb = new JLabel("0 kW");
	JLabel txtQevp = new JLabel("0 kJ/s");
	JLabel txtQcon = new JLabel("0 kJ/s");
	JLabel txtQreg = new JLabel("0 kJ/s");
	JLabel txtATevp = new JLabel("0 m²");
	JLabel txtATcon = new JLabel("0 m²");
	JLabel txtATreg = new JLabel("0 m²");
	JLabel txtAt = new JLabel("0 m²");
	
	public ViewSaida() {
		
        painelSaida = new JPanel(new GridBagLayout());
        painelSaida.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray), "Saída", 1, 2, new Font("Times New Roman", 1, 12), Color.darkGray));

        GridBagConstraints g = new GridBagConstraints();
		
        //PAINEL SAIDA
        g.anchor = GridBagConstraints.LINE_START;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWt,g);
        
        g.gridx = 1;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWt,g);
        
        g.gridx = 0;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWn,g);
        
        g.gridx = 1;
        g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWn,g);
        
        g.gridx = 0;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblWb,g);
        
        g.gridx = 1;
        g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtWb,g);
        
        g.gridx = 0;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQevp,g);
        
        g.gridx = 1;
        g.gridy = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQevp,g);
        
        g.gridx = 0;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQcon,g);
        
        g.gridx = 1;
        g.gridy = 4;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQcon,g);
        
        g.gridx = 0;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblAt,g);
        
        g.gridx = 1;
        g.gridy = 5;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtAt,g);
        
        g.gridx = 0;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblQreg,g);
        
        g.gridx = 1;
        g.gridy = 6;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtQreg,g);
        
        g.gridx = 0;
        g.gridy = 7;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATevp,g);
        
        g.gridx = 1;
        g.gridy = 7;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATevp,g);
        
        g.gridx = 0;
        g.gridy = 8;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATcon,g);
        
        g.gridx = 1;
        g.gridy = 8;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATcon,g);
        
        g.gridx = 0;
        g.gridy = 9;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(lblATreg,g);
        
        g.gridx = 1;
        g.gridy = 9;
        g.fill = GridBagConstraints.HORIZONTAL;
        painelSaida.add(txtATreg,g);
		
        this.add(painelSaida);
        this.setBounds(200, 200, 300, 250);
        this.setTitle("Dados de Saída");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JLabel getTxtWt() {
        return txtWt;
    }

    public void setTxtWt(JLabel txtWt) {
        this.txtWt = txtWt;
    }

    public JLabel getTxtWn() {
        return txtWn;
    }

    public void setTxtWn(JLabel txtWn) {
        this.txtWn = txtWn;
    }

    public JLabel getTxtWb() {
        return txtWb;
    }

    public void setTxtWb(JLabel txtWb) {
        this.txtWb = txtWb;
    }

    public JLabel getTxtQevp() {
        return txtQevp;
    }

    public void setTxtQevp(JLabel txtQevp) {
        this.txtQevp = txtQevp;
    }

    public JLabel getTxtQcon() {
        return txtQcon;
    }

    public void setTxtQcon(JLabel txtQcon) {
        this.txtQcon = txtQcon;
    }

    public JLabel getTxtAt() {
        return txtAt;
    }

    public void setTxtAt(JLabel txtAt) {
        this.txtAt = txtAt;
    }

    public JLabel getTxtQreg() {
        return txtQreg;
    }

    public void setTxtQreg(JLabel txtQreg) {
        this.txtQreg = txtQreg;
    }
    
    public JLabel getTxtATevp() {
        return txtATevp;
    }

    public void setTxtATevp(JLabel txtATevp) {
        this.txtATevp = txtATevp;
    }

    public JLabel getTxtATcon() {
        return txtATcon;
    }

    public void setTxtATcon(JLabel txtATcon) {
        this.txtATcon = txtATcon;
    }

    public JLabel getTxtATreg() {
        return txtATreg;
    }

    public void setTxtATreg(JLabel txtATreg) {
        this.txtATreg = txtATreg;
    }
}
