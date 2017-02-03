package ru.vsu;

import ru.vsu.types.BYTE;
import ru.vsu.utilities.ByteBlock;
import ru.vsu.utilities.ExecuteShellCommand;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ByteBlock b1 = new ByteBlock(5, new BYTE('0'));
        ByteBlock b2 = new ByteBlock(5, new BYTE('1'));
        ByteBlock b5 = new ByteBlock(
                new BYTE[] {
                        new BYTE('a'),
                        new BYTE('b'),
                        new BYTE('c'),
                        new BYTE('d'),
                        new BYTE('f')
                }, 5);

        ExecuteShellCommand com = new ExecuteShellCommand();
        try {
            com.executeCommands();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
