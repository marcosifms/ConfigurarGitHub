/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class GitBash {

    private String gitBashPath;
    private String projectPath;

    public GitBash() {
        gitBashPath = "C:\\Program Files\\Git\\bin\\bash.exe";  // Note que usamos bash.exe diretamente
        projectPath = "/f/DriveC_Atual/Documents/NetBeans18Projects/SistemaSMA";
    }

    public GitBash(String gitBashPath, String projectPath) {
        this.gitBashPath = gitBashPath;
        this.projectPath = projectPath;
    }

    public List configGit(String nick, String email, String repositorio) {
//        String gitBashPath = "C:\\Program Files\\Git\\bin\\bash.exe";  // Note que usamos bash.exe diretamente
//        String projectPath = "/f/DriveC_Atual/Documents/NetBeans18Projects/SistemaSMA";
        ArrayList list = new ArrayList();
        try {
//            ProcessBuilder pb = new ProcessBuilder(
//                    gitBashPath,
//                    "--login", // Carrega o ambiente como uma sessão interativa
//                    "-i", // Modo interativo
//                    "-c",
//                    "cd '" + projectPath + "' && pwd "
//            );
            ProcessBuilder pb = new ProcessBuilder(
                    gitBashPath,
                    "--noprofile", // Não carregar perfis desnecessários
                    "--norc", // Não carregar .bashrc
                    "-c",
                    "cd \"" + projectPath.replace("\\", "/") + "\" >/dev/null 2>&1 "
                    + "&& git status"
//                    + "&& git init"
                    + "&& git config --global user.name  \"" + nick + "\""
                    + "&& git config --global user.email \"" + email + "\""
//                    + "&& git branch -M main"
//                    + "&& git remote add origin \"" + repositorio + "\""
            );
            System.out.println("Comando: " + String.join(" ", pb.command()));

            pb.redirectErrorStream(true);
            Process p = pb.start();

            // Ler saída de forma não-bloqueante
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                System.out.println("> " + line);
            }

            int exitCode = p.waitFor();
            System.out.println("Código de saída: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List configStatus() {
        ArrayList list = new ArrayList();
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    gitBashPath,
                    "--noprofile", // Não carregar perfis desnecessários
                    "--norc", // Não carregar .bashrc
                    "-c",
                    "cd \"" + projectPath.replace("\\", "/") + "\" >/dev/null 2>&1 "
                    + "&& git status"
            );
            System.out.println("Comando: " + String.join(" ", pb.command()));

            pb.redirectErrorStream(true);
            Process p = pb.start();

            // Ler saída de forma não-bloqueante
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                System.out.println("> " + line);
            }

            int exitCode = p.waitFor();
            System.out.println("Código de saída: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        GitBash gitBash = new GitBash("C:\\Program Files\\Git\\bin\\bash.exe",
                "/f/DriveC_Atual/Documents/NetBeans18Projects/ConfigurarGitHub");
//                "/f/DriveC_Atual/Documents/NetBeans18Projects/SistemaSMA");
        gitBash.configGit("marcos.ifms", "marcos.vilhanueva@ifms.edu.br", 
                "https://github.com/marcosifms/ConfigurarGitHub.git");
    }
    /*
    public static void main(String[] args) {

//        ProcessBuilder processBuilder = new ProcessBuilder();
//
//            // Comando para executar no git-bash
////            processBuilder.command(gitBashPath, "--cd-to-home", "-c", "ls -la");
//            //Executando múltiplos comandos
//            processBuilder.command(gitBashPath, "-c", "cd /c/myproject && git status");
//            
//            //3. Trabalhando com diretórios específicos
//            processBuilder.command(gitBashPath, "--cd=C:\\myproject", "-c", "ls");
//            
//            // Redirecionar saída para o console do Java
//            processBuilder.redirectErrorStream(true);
//            processBuilder.inheritIO();
//
//            // Iniciar o processo
//            Process process = processBuilder.start();
//
//            // Esperar o processo terminar
//            int exitCode = process.waitFor();
//            System.out.println("\nProcesso terminou com código: " + exitCode);
         
        String gitBashPath = "C:\\Program Files\\Git\\bin\\bash.exe";  // Note que usamos bash.exe diretamente
        String projectPath = "/f/DriveC_Atual/Documents/NetBeans18Projects/SistemaSMA";

        try {
//            ProcessBuilder pb = new ProcessBuilder(
//                    gitBashPath,
//                    "--login", // Carrega o ambiente como uma sessão interativa
//                    "-i", // Modo interativo
//                    "-c",
//                    "cd '" + projectPath + "' && pwd "
//            );
            ProcessBuilder pb = new ProcessBuilder(
                    gitBashPath,
                    "--noprofile", // Não carregar perfis desnecessários
                    "--norc", // Não carregar .bashrc
                    "-c",
                    "cd \"" + projectPath.replace("\\", "/") + "\" >/dev/null 2>&1 && git status"
            );
            System.out.println("Comando: " + String.join(" ", pb.command()));

            pb.redirectErrorStream(true);
            Process p = pb.start();

            // Ler saída de forma não-bloqueante
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("> " + line);
            }

            int exitCode = p.waitFor();
            System.out.println("Código de saída: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */

}
