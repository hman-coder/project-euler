package one_to_ten.p06;

import helpers.NumberHelpers;

public class Problem6 {
    public long runAlgorithm(int n) {
        int i = 1;
        long sumOfSquares = 0;
        long squareOfSum = 0;
        while (i <= n) {
            sumOfSquares += NumberHelpers.power(i, 2);
            squareOfSum += i;
            i++;
        }

        squareOfSum = NumberHelpers.power(squareOfSum, 2);
        return squareOfSum - sumOfSquares;
    }
}
