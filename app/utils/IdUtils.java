package utils;

import com.github.ddth.commons.utils.IdGenerator;

public class IdUtils {
    private final static IdGenerator idGen = IdGenerator.getInstance(IdGenerator.getMacAddr());

    public final static long nextId64() {
        return idGen.generateId64();
    }

    public final static String nextId64Hex() {
        return idGen.generateId64Hex().toLowerCase();
    }

    public final static String nextId64Ascii() {
        return idGen.generateId64Ascii().toLowerCase();
    }

    public final static long nextIdTiny() {
        return idGen.generateIdTiny();
    }

    public final static String nextIdTinyHex() {
        return idGen.generateIdTinyHex().toLowerCase();
    }

    public final static String nextIdTinyAscii() {
        return idGen.generateIdTinyAscii().toLowerCase();
    }
}
