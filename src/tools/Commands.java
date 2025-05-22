package tools;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//https://pt.stackoverflow.com/questions/57073/executar-comandos-do-cmd-pelo-java
public class Commands {

    static final Runtime run = Runtime.getRuntime();
    static Process pro;
    static BufferedReader read;

    public List processaCmd(String[] cmds) {
        ArrayList lista = new ArrayList();
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                    String.join("& ", cmds));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                
                if (line == null) {
                    break;
                }
                lista.add(line);
//                System.out.println(line);

            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        return lista;
    }

    public List processaCmd(String cmd) {
        ArrayList lista = new ArrayList();
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                    String.join("& ", cmd));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                
                if (line == null) {
                    break;
                }
                lista.add(line);
//                System.out.println(line);

            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
        return lista;
    }

    public static void main(String[] args) {
        String[] cmds = {
            "f:",
            "cd F:\\Drivec_Atual\\Documents\\NetBeansProjects\\CriarBDAlunosIFMS",
            "git config --list"
        };
        Commands commands = new Commands();
        List lista = commands.processaCmd(cmds);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(  lista.get(i) );            
        }
        
    }
}
