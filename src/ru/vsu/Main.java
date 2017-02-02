package ru.vsu;

import ru.vsu.types.BYTE;
import ru.vsu.utilities.ByteBlock;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ByteBlock b1 = new ByteBlock(5, new BYTE('0'));
        ByteBlock b2 = new ByteBlock(5, new BYTE('1'));

//        ByteBlock b3 = new ByteBlock(b2.getBlocks(), 3);
//
//        ByteBlock b4 = new ByteBlock(b2);
//
        ByteBlock b5 = new ByteBlock(
                new BYTE[] {
                        new BYTE('1'),
                        new BYTE('2'),
                        new BYTE('3'),
                        new BYTE('4'),
                        new BYTE('5')
                }, 5);

//        b1.assignMove(b5);
//
//        b1.reset(b2.getBlocks(), b2.getAmountOfBytes());

//        ByteBlock b7 = b5.slice(3, 2);

//

        List e = new LinkedList();
        e = ByteBlock.splitBlocks(b5, 2);

        ByteBlock be = ByteBlock.joinBlocks(e);

        ByteBlock sa = new ByteBlock(0, new BYTE('0'));
        ByteBlock.xorBlocks(sa, b1, b2);
        System.out.println("w");
    }
}
