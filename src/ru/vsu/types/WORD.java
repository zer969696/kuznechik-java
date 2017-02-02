package ru.vsu.types;

/**
 * Created by Evgeniy Evzerov on 02.02.17.
 * VIstar
 */
public class WORD {

    public WORD(short value) {
        this.value = value;
    }

    private short value;

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WORD word = (WORD) o;

        return value == word.value;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
