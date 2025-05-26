/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class CommandsGit {

    final static int WINDOWS = 0;
    final static int LINUX = 1;
    String diretorio;
    int sistemaOperacional;

    public CommandsGit(String diretorio, int sistemaOperacional) {
        this.diretorio = diretorio;
        this.sistemaOperacional = sistemaOperacional;
    }

    public List cmdWindows(String cmds) {
        String[] cmds1 = {
                diretorio.substring(0, 2),
                "cd " + diretorio,
                cmds
            };
        Commands commands = new Commands();
        return commands.processaCmd(cmds1);

    }

    public List cmdLinux(String comando) {
            List<String> resultado = null;
            LocalShell shell = new LocalShell();
            try {
                // Vários comandos no mesmo shell
                resultado = shell.executeCommand(comando, this.diretorio, 10);

//                System.out.println("===== SAÍDA DO COMANDO =====");
//                for (String linha : resultado) {
//                    System.out.println(linha);
//                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return resultado;
    }

    public List gitConfigList() {
//            "git init",
//            "git config --global user.name  \"" + jTxtNick.getText() + "\"",
//            "git config --global user.email \"" + jTxtEmail.getText() + "\"",
//            "git branch -M main",
//            "git remote add origin \"" + jTxtRepositorio.getText() + "\"",}; 

       if (this.sistemaOperacional == WINDOWS) {
            return cmdWindows("");
        } else {            
            return cmdLinux("");
        }
    }

    public List gitAddCommit(String mensagem) {
//                "git add *",
//                "git commit -m \"" + jTextArea2.getText() + "\""        
       if (this.sistemaOperacional == WINDOWS) {
           List lista = cmdWindows("git add *");
           lista = cmdWindows("git commit -m \"" + mensagem + "\"");
            return lista;
        } else {            
            return cmdLinux("git add *;git commit -m \"" + mensagem + "\"");
        }
    }

    public List gitPush() {
        //"git push -u origin main"
       if (this.sistemaOperacional == WINDOWS) {
            return cmdWindows("git push -u origin main");
        } else {            
            return cmdLinux("git push -u origin main");
        }
    }

    public List gitAddCommitPush(String mensagem) {
       if (this.sistemaOperacional == WINDOWS) {
            List lista = cmdWindows("git add *");
           lista = cmdWindows("git commit -m \"" + mensagem + "\"");
           lista = cmdWindows("git push -u origin main");
            return lista;
        } else {            
            return cmdLinux("git add *;git commit -m \"" + mensagem + "\"; git push -u origin main");
        }
    }

    public List gitConfig() {
       if (this.sistemaOperacional == WINDOWS) {
            return cmdWindows("git log");
        } else {            
            return cmdLinux("git log;");
        }
    }

    public List gitLog() {
        if (this.sistemaOperacional == WINDOWS) {
            return cmdWindows("git log");
        } else {            
            return cmdLinux("git log;");
        }
    }

    public boolean gitConfigurado() {
        if (this.sistemaOperacional == WINDOWS) {
            List lista = cmdWindows("git status");
            return !((String) lista.get(0)).contains("fatal: not a git repository");
        } else {            
            List lista = cmdLinux("git status");
            return !((String) lista.get(0)).contains("fatal: not a git repository");
        }
    }
}
