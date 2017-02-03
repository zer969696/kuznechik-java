package ru.vsu.types;

/**
 * Created by Evgeniy Evzerov on 02.02.17.
 * VIstar
 */
public class BYTE {

    private char value;

    public BYTE(char value) {
        this.value = value;
    }

    public BYTE(int value) {
        this.value = (char) value;
    }

    public BYTE(BYTE byteValue) {
        this.value = byteValue.getValue();
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BYTE aBYTE = (BYTE) o;

        return value == aBYTE.value;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
