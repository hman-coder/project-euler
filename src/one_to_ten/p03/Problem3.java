package one_to_ten.p03;

import helpers.ArrayHelpers;
import helpers.NumberHelpers;

import java.util.Arrays;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 *
 * 1) Try to find the smallest prime factorial for the given number (currentRecursionInput).
 * 2) Add that to the primeFactorials array.
 * 3) Divide the currentRecursionInput on that factorial. Store it in a variable x.
 * 4) Pass x, with the array, to a recursive call.
 * 5) Break the recursion when the multiplier of all array numbers is equal to the input.
 *
 */
public class Problem3 {
    /**
     * @param originalInput the number for which we need to calculate the largest prime factorial
     * @param currentRecursionInput should be equal to @param originalInput
     * @param primeFactorials must be an empty array, otherwise might not return
     */
    public long[] runAlgorithm(long originalInput, long currentRecursionInput, long[] primeFactorials) {
        long[] newArr = primeFactorials;

        // Multiplies all elements in [primeFactorials] with each other. If the result is the same as originalInput
        // then the problem is solved.
        long primeFactorialsMultiplicationResults = 1;
        for(long l : primeFactorials) {
            primeFactorialsMultiplicationResults *= l;
        }

        boolean problemIsSolved = primeFactorialsMultiplicationResults == originalInput;

        if(! problemIsSolved) {
            long currentPrimeFactorial = 2;
            while (true) {
                if (NumberHelpers.isPrime(currentPrimeFactorial)) // Make sure current number is a prime
                    if(currentRecursionInput % currentPrimeFactorial == 0) { // Make sure input is divisible by that number
                        newArr = ArrayHelpers.addToArray(primeFactorials, currentPrimeFactorial);
                        newArr = runAlgorithm(originalInput, (long)currentRecursionInput/currentPrimeFactorial, newArr);
                        break;
                    }

                currentPrimeFactorial++;
            }
        }

        Arrays.sort(newArr);
        return newArr;
    }
}
