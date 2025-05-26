package tools;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Marcos
 */
//https://www.vivaolinux.com.br/dica/Executar-comandos-do-terminal-Linux-em-Java
//https://www.devmedia.com.br/executando-shell-scripts-utilizando-java/26494
public class LocalShell {

    private static final Logger log = Logger.getLogger(LocalShell.class.getName());

    public List<String> executeCommand(String command, String workingDir, long timeoutSeconds)
            throws IOException, InterruptedException {

        List<String> output = new ArrayList<>();
        List<String> errorOutput = new ArrayList<>();
        List<String> commands = Arrays.asList("/bin/bash", "-c", command);

        ProcessBuilder builder = new ProcessBuilder(commands);
        if (workingDir != null) {
            builder.directory(new File(workingDir));
        }

        Process process = builder.start();

        StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), output);
        StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), errorOutput);

        outputGobbler.start();
        errorGobbler.start();

        boolean finished = process.waitFor(timeoutSeconds, TimeUnit.SECONDS);
        if (!finished) {
            process.destroyForcibly();
            throw new IOException("Comando excedeu o tempo limite de " + timeoutSeconds + " segundos.");
        }

        outputGobbler.join();
        errorGobbler.join();

        if (!errorOutput.isEmpty()) {
            log.warning("Saída de erro:\n" + String.join("\n", errorOutput));
        }

        return output;
    }

    private void secureClose(final Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
            log.severe("Erro = " + ex.getMessage());
        }
    }

    private static class StreamGobbler extends Thread {

        private final InputStream inputStream;
        private final List<String> output;

        public StreamGobbler(InputStream inputStream, List<String> output) {
            this.inputStream = inputStream;
            this.output = output;
        }

        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.add(line);
                }
            } catch (IOException e) {
                output.add("Erro ao ler stream: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        LocalShell shell = new LocalShell();
        String dir = "/home/marcos/NetBeansProjects/ConfigurarGitHub/";  // Altere se necessário

        try {
            // Vários comandos no mesmo shell
            String comando = "pwd; git status;";
            List<String> resultado = shell.executeCommand(comando, dir, 10);

            System.out.println("===== SAÍDA DO COMANDO =====");
            for (String linha : resultado) {
                System.out.println(linha);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
