package com.mohkamfer.icgrtoolkit.util;

public class ChaosUtil {
    public enum Genome {
        T(-1, 1), A(1, 1), C(-1, -1), G(1, -1);

        public final int x;
        public final int y;

        Genome(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final char toChar() {
            return this.toString().charAt(0);
        }
    }

    public static boolean validSequence(String sequence) {
        return sequence.toUpperCase().matches("^([TACG]+)$");
    }

    public static double nextCGREncode(double current, int next) {
        return (current + next) * 0.5;
    }
}
