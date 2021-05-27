package one_to_ten.p01;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * Idea is simple: keep multiplying each number with a variable that increases by 1 each loop.
 * CAUTION: You should add multipliers of the number (like 3*5, 6*5, 6 * 10) ONLY ONCE.
 */
public class Problem1 {
    public long runAlgorithm(long small, long big, long upperBound) {
        if(small > big) {
            long placeholder = big;
            small = big;
            big = placeholder;
        }

        long s = 1, b = 1;         // s is multiplier of small number, b is multiplier of big number
        long returnedValue = 0;    // This upper bound is also a multiplier of the two digits

        while(s*small < upperBound) {
            returnedValue += s*small;
            s++;
        }

        while(b*big < upperBound) {
            if(b*big % small == 0) {    // This is the CAUTION at the top
                b++;
                continue;
            }

            returnedValue += b*big;
            b++;
        }

        return returnedValue;
    }
}
