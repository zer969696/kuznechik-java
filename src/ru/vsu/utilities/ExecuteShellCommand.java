package ru.vsu.utilities;

import java.io.*;

/**
 * Created by Evgeniy Evzerov on 03.02.17.
 * VIstar
 */
public class ExecuteShellCommand {
    StringBuffer output = new StringBuffer();

    public void executeCommands() throws IOException {

        File tempScript = createTempScript();

        try {
            ProcessBuilder pb = new ProcessBuilder("bash", tempScript.toString());
            pb.inheritIO();
            Process process = pb.start();
            InputStream is = process.getInputStream();
            StreamGobbler pOut = new StreamGobbler(is, new PrintStream(System.out));
            pOut.start();

            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tempScript.delete();
        }
    }

    private File createTempScript() throws IOException {
        File tempScript = File.createTempFile("script", null);

        Writer streamWriter = new OutputStreamWriter(new FileOutputStream(
                tempScript));
        PrintWriter printWriter = new PrintWriter(streamWriter);

        printWriter.println("#!/bin/bash");
        printWriter.println("cd /home/benz/Encryptor-With-Kuznyechik-master/");
        printWriter.println("make all");
        printWriter.println("./program -P test.txt -D kek.txt -d");

        printWriter.close();

        return tempScript;
    }

}
