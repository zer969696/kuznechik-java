package ru.vsu.utilities;

import ru.vsu.types.BYTE;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.toBinaryString;

/**
 * Created by Evgeniy Evzerov on 02.02.17.
 * VIstar
 */
public class ByteBlock {

    private BYTE[] blocks;
    private int amountOfBytes;

    /**
     * @return текущий массив байтов
     */
    public BYTE[] getBlocks() {
        return blocks;
    }

    /**
     * @param blocks - инициализирует текущий массив байтов - посылаемым массивом байтов
     */
    public void setBlocks(BYTE[] blocks) {
        this.blocks = blocks;
    }

    /**
     * @return размер текущего массива байтов
     */
    public int getAmountOfBytes() {
        return amountOfBytes;
    }

    /**
     * @param amountOfBytes - задает размер текущего массива байтов
     */
    public void setAmountOfBytes(int amountOfBytes) {
        this.amountOfBytes = amountOfBytes;
    }

    /**
     * Конструктор, который создает блок байтов длинной 'size' и
     * заполняет этот блок байтов инициирующим значением 'initValue'
     *
     * @param size - размер блока байтов
     * @param initValue - значение, которым заполняется каждый элемент блок байтов
     */
    public ByteBlock(int size, BYTE initValue) {
        this.amountOfBytes = size;

        if (size < 1) {
            this.blocks = null;
        } else {
            this.blocks = new BYTE[size];

            for (int i = 0; i < size; i++) {
                blocks[i] = new BYTE(initValue);
            }
        }
    }

    /**
     * Конструктор, который создает блок байтов следующим образом:
     * 1. берет 'size' элементов из 'blocks', 'blocks' - массив байтов, который посылается
     * в конструктор
     * 2. создает блок байтов размером 'size', и копирует элементы из пункта 1.
     *
     * Массив байтов остается нетронутым
     *
     * @param blocks - массив байтов
     * @param size - размер создаваемого блока байтов и размер количества копируемых эл-тов
     */
    public ByteBlock(BYTE[] blocks, int size) {
        this.amountOfBytes = size;

        this.blocks = new BYTE[size];
        for (int i = 0; i < size; i++) {
            this.blocks[i] = new BYTE(blocks[i]);
        }
    }

    /**
     * Конструктор перемещения. Посылаемый объект для перемещения 'blockToCopyAndDelete'
     * обнуляется и удаляется.
     *
     * @param blockToCopyAndDelete - посылаемый блок байтов,
     *                             который мы полностью копируем и затем удаляем
     */
    public ByteBlock(ByteBlock blockToCopyAndDelete) {
        this.amountOfBytes = blockToCopyAndDelete.getAmountOfBytes();
        this.blocks = new BYTE[blockToCopyAndDelete.getAmountOfBytes()];

        for (int i = 0; i < this.amountOfBytes; i++) {
            this.blocks[i] = new BYTE(blockToCopyAndDelete.getBlocks()[i]);
        }

        blockToCopyAndDelete.setAmountOfBytes(0);
        blockToCopyAndDelete.setBlocks(null);
    }

    /**
     * Функция присваивания с перемещением. Объект перемещения обнуляется и удаляется (= null)
     *
     * @param block - посылаемый блок байтов с которого берем данные,
     *              присваиваем их текущему блоку байтов
     *              и далее обнуляем блок байтов block
     */
    public void assignMove(ByteBlock block) {
        if (this == block) { return; }

        if (this.blocks != null) {
            this.blocks = null;
        }

        this.amountOfBytes = block.getAmountOfBytes();
        this.blocks = new BYTE[this.amountOfBytes];

        for (int i = 0; i < this.amountOfBytes; i++) {
            this.blocks[i] = new BYTE(block.getBlocks()[i]);
        }

        block.blocks = null;
        block.amountOfBytes = 0;
    }

    /**
     * Проверяем, равен ли посылаемый блок байтов текущему блоку байтов.
     * Проверка производится путем сравнения текущего массива байтов и
     * посылаемого массива байтов
     *
     * @param block - посылаемый блок байтов
     * @return 'true' если текущий массив байтов == массиву байтов посылаемого блока байтов,
     * иначе 'false'
     */
    public boolean isEquals(ByteBlock block) {
        return this.blocks == block.getBlocks();
    }

    /**
     * Проверяем на неравенство текущего блока байтов и посылаемого.
     * Проверка производится путем непосредственного сравнения
     *
     * @param block - посылаемый блок байтов
     * @return 'true' если текущий блок байтов == посылаемому блоку байтов
     * иначе 'false'
     */
    public boolean isNonEquals(ByteBlock block) {
        return !(this == block);
    }

    /**
     * Заменяем текущий массив байтов - посылаемым массивом байтов
     * Старые значения текущего блока байтов будут обнулены, затем удалены
     * Новые значения массива байтов будут скопированы в текущий блок байтов,
     * посылаемый массив байтов останется нетронутым
     *
     * @param blocks - посылаемый массив байтов
     * @param size - размер посылаемого массива байтов
     */
    public void reset(BYTE[] blocks, int size) {
        if (this.blocks != null) {
            this.blocks = null;
        }

        if (size > 0 && blocks != null) {
            this.blocks = new BYTE[size];

            for (int i = 0; i < this.amountOfBytes; i++) {
                this.blocks[i] = new BYTE(blocks[i]);
            }
            amountOfBytes = size;
        } else {
            this.blocks = null;
            amountOfBytes = 0;
        }
    }

    /**
     * Метод, который вовзращает копию блока байтов, которая расположена
     * в другом участке памяти чтобы избежать конфликтов перезаписи
     *
     * @return глубокую копию блока байтов, расположенную в другом участке памяти
     */
    public ByteBlock deepCopy() {
        return new ByteBlock(this.blocks, this.amountOfBytes);
    }

    /**
     * Метод возращает блок байтов, который получается путем "среза" массива байтов
     * с позиции 'start' длины 'length'
     *
     * @param start - начальный индекс среза массива
     * @param length - длина среза массива
     * @return блок байтов
     */
    public ByteBlock slice(int start, int length) {
        ByteBlock tmpByteBlock = new ByteBlock(length, new BYTE('0'));

        for (int i = 0; i < length; i++) {
            tmpByteBlock.getBlocks()[i] = this.blocks[start + i];
        }

        return tmpByteBlock;
    }

    /**
     * Метод меняет два блока байтов между собой
     *
     * @param block - блок байтов для свапа
     */
    public void swap(ByteBlock block) {
        int tempSize = block.amountOfBytes;
        BYTE[] tempBlocks = new BYTE[tempSize];
        for (int i = 0; i < tempSize; i++) {
            tempBlocks[i] = new BYTE(block.getBlocks()[i].getValue());
        }

        block.amountOfBytes = this.getAmountOfBytes();
        for (int i = 0; i < tempSize; i++) {
            block.blocks[i].setValue(this.getBlocks()[i].getValue());
        }

        this.amountOfBytes = tempSize;
        for (int i = 0; i < tempSize; i++) {
            this.blocks[i].setValue(tempBlocks[i].getValue());
        }
    }

    /**
     * Метод делит блок байтов на части, длина части определяется параметром 'length'
     * Дели целочисленно, то есть, если массив байтов состоит из 5 элементов и
     * 'length' == 4, то получится 2 блока байтов, один из 4 элементов, другой из 1
     *
     * @param sourceBlock - блок байтов, который будем делить на части
     * @param length - длина каждой части
     * @return список блоков байтов, поделенных на равные части
     */
    public static List<ByteBlock> splitBlocks(ByteBlock sourceBlock, int length) {
        List<ByteBlock> tmp = new LinkedList<>();

        int amount = sourceBlock.getAmountOfBytes() / length;
        int tail = sourceBlock.getAmountOfBytes() % length;

        for (int i = 0; i < amount; i++) {
            tmp.add(sourceBlock.slice(i * length, length));
        }

        if (tail != 0) {
            tmp.add(sourceBlock.slice(amount * length, tail));
        }

        return tmp;
    }

    /**
     * Метод, который соединяет список блоков байтов в один блок байтов
     *
     * @param byteBlocksList - список блоков байтов
     * @return соединенный блок байтов
     */
    public static ByteBlock joinBlocks(List<ByteBlock> byteBlocksList) {
        if (byteBlocksList.isEmpty()) { return new ByteBlock(0, new BYTE('0')); }

        int listSize = byteBlocksList.size();
        int blockSize = byteBlocksList.get(0).getAmountOfBytes();
        int lastSize = byteBlocksList.get(listSize - 1).getAmountOfBytes();
        int byteBlockSize = (listSize - 1) * blockSize + lastSize;

        ByteBlock tmp = new ByteBlock(byteBlockSize, new BYTE('0'));
        int k = 0;
        for (int i = 0; i < listSize - 1; i++) {
            for (int j = 0; j < blockSize; j++) {
                tmp.getBlocks()[k++]
                        .setValue(byteBlocksList.get(i)
                                .getBlocks()[j]
                                .getValue());
            }
        }

        for (int i = 0; i < lastSize; i++) {
            tmp.getBlocks()[k++]
                    .setValue(byteBlocksList
                            .get(listSize - 1)
                            .getBlocks()[i]
                            .getValue());
        }

        return tmp;
    }

    public static void xorBlocks(ByteBlock toAssign, ByteBlock leftBlock, ByteBlock rightBlock) {
        int resultSize = leftBlock.getAmountOfBytes() > rightBlock.getAmountOfBytes()
                ? rightBlock.getAmountOfBytes()
                : leftBlock.getAmountOfBytes();

        ByteBlock tmp = new ByteBlock(resultSize, new BYTE('0'));

        for (int i = 0; i < resultSize; i++) {
            tmp.getBlocks()[i].setValue(
                    (char) (leftBlock.getBlocks()[i].getValue() ^ rightBlock.getBlocks()[i].getValue()));
        }

        byte a = '5';
        byte b = '3';

        //ToDo tut
        byte c = (byte) (a ^ b);

        toAssign = new ByteBlock(tmp);
    }
}
