package edu.purdue.symmetria.crypto.cipher;

import edu.purdue.symmetria.utils.MathUtils;


public abstract class SymCipher {
     long value;

    public enum CipherType {
        // Store ids using array as a backbone
        ARRAY,

        // Store ids using an inner class to compact ranges
        RANGE
    }

    public long getValue() {
        return value;
    }

    public void setValue(long v) {
        value = v;
    }

    public void addValue(long v, long modulo) {
        value = MathUtils.modAdd(value, v, modulo);
    }

    public void subValue(long v, long modulo) {
        value = MathUtils.modSubtract(value, v, modulo);
    }

    public void multiplyValue(long v, long modulo) {
        value = MathUtils.modMul(value, v, modulo);
    }

    void raiseValue(long v, long modulo) {
        value = MathUtils.modPow(value, v, modulo);
    }


    /**
     * Return the number of ids currently stored.
     */
    public abstract int getSize();

    /**
     * Return a 2 long arrays (2-d array). The first is the ids and the second their corresponding
     * cardinalities. Arrays return might be longer than the actual items, so getSize() should be
     * used to find out how many items of the array are valid.
     */
    public abstract long[][] getIds();

    /**
     * Add the given cipher to this object: this = this + other. Both this and the other cipher must
     * have been generated by an ADDITIVE scheme.
     */
    public abstract void add(SymCipher other, long modulo);

    /**
     * Subtract the given cipher to this object: this = this + other. Both this and the other cipher
     * must have been generated by an ADDITIVE scheme.
     */
    public abstract void sub(SymCipher other, long modulo);

    /**
     * Multiply the given cipher by the given plaintext value: this = this * m. this cipher must
     * have been generated by an ADDITIVE scheme.
     */
    public abstract void multiply(long m, long modulo);

    /**
     * Multiply this object by the given other object: this = this * other. Both this and the other
     * cipher must have been generated by an MULTIPLICATIVE scheme.
     */
    public abstract void multiply(SymCipher other, long modulo);

    /**
     * Raise the given cipher to the power of the given plaintext value: this = this ^ m. this
     * cipher must have been generated by a MULTIPLICATIVE scheme.
     */
    public abstract void pow(long m, long modulo);

}
