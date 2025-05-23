/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jmbvi
 */
public class Util {

    public static void habilitar(boolean valor, JComponent... vetComp) {//vetcomp e um varargs
        for (int i = 0; i < vetComp.length; i++) {
            vetComp[i].setEnabled(valor);
        }
    }

    public static void limparCampos(JComponent... vetComp) {//metodo estatico pode ser usado sem atribuir a um objeto
        for (int i = 0; i < vetComp.length; i++) {
            if (vetComp[i] instanceof JTextField jTextField) {
                jTextField.setText("");                
            } else if (vetComp[i] instanceof JTextArea) {
                ((JTextArea) vetComp[i]).setText("");                
            } else if (vetComp[i] instanceof JComboBox) {
                ((JComboBox) vetComp[i]).setSelectedIndex(-1);
            } else if (vetComp[i] instanceof JCheckBox) {
                ((JCheckBox) vetComp[i]).setSelected(false);
            }
        }
    }

    public static void mensagem(String texto) {
        JOptionPane.showMessageDialog(null, texto, "Alerta", 2);
    }

    public static boolean perguntar(String texto) {
        int resp = JOptionPane.showConfirmDialog(null, texto, "Confirmar", JOptionPane.YES_NO_OPTION);
        return resp == JOptionPane.YES_OPTION;
    }

    public static int strInt(String cad) {
        return Integer.parseInt(cad);
    }

    public static String intStr(int num) {
        return String.valueOf(num);
    }

    public static double strDouble(String cad) {
        return Double.parseDouble(cad);
    }

    public static String doubleStr(double num) {
        return String.valueOf(num);
    }

    public static Date strDate(String cad) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formato.parse(cad);
        } catch (ParseException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: " + ex.getMessage());
        }
        return data;
    }

    public static String dateStr(Date data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String cad = formato.format(data);

        return cad;

    }
}
