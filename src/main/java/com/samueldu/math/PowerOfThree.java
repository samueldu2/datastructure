package com.samueldu.math;

public class PowerOfThree {

        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }

}
