package one_to_ten.p07;

import helpers.NumberHelpers;

public class Problem7 {
    public long runAlgorithm(long n) {
        long f = 1;
        long i = 1;

        while(f < n) {
            i+= 2;
            if(NumberHelpers.isPrime(i)) f++;
        }

        return i;
    }
}
