package one_to_ten.p05;

import helpers.NumberHelpers;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Problem5 {
    /**
     * Let s denote an optimal solution.
     * First, we can get rid of the lower half of the input n, because if a number in the higher half x is a factor
     * of s, then x/2 - which would be a number in the lower half - is also a factor of s.
     * Let s be the highest value. Loop in descending order. Let x be the 2nd highest value. If s and the x have a
     * common factor, divide both by that factor. When no factors remain, multiply s by the original (first) value of x.
     * Keep going until the array is depleted.
     */
    public long runAlgorithm(int n) {
        long sum = n;

        for(int x = n-1; x > n/2; x--) {
            int xCopy = x;

            for(int i = 2; i <= x/2; i++) {
                if(xCopy % i == 0 && sum % i == 0) {
                    sum /= i;
                    xCopy /= i;
                    i = 1;
                }
            }

            sum *= x;
        }

        return sum;
    }

    /**
     * This method uses prime factorials. For every prime below the input n, find out the highest power of that
     * prime that is smaller than n. That is, for every prime p < n, find the highest value of j where p^j < n.
     * Multiply all p^j you found and you'll get your answer.
     */
    public long runAlternativeAlgorithm(int n) {

        int curr = 2;
        int power = 1;
        long answer = 1;
        while (curr <= n) {
            if(!NumberHelpers.isPrime(curr)) {
                curr++;
                continue;
            }
            long po = NumberHelpers.power(curr, power);
            if(po > n) {
                answer *= NumberHelpers.power(curr, power-1);
                curr++;
                power = 1;

            } else {
                power++;
            }
        }

        return answer;
    }
}
