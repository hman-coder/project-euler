package one_to_ten.p04;

import helpers.NumberHelpers;
import helpers.PalindromeHelpers;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 * 1) Get the largest number you can get from multiplying two 3-digit numbers (999*999). Store in x.
 * 2) Loop:
 * 2a) Floor x to the nearest palindromic number.
 * 2b) Get the sqrt of x (rounded or floored). Store in y.
 * 2c) Loop for binary search: (i = j = y; i > 100 & j < 999)
 * 2c1) Store i * j in z.
 * 2c2) if z is the palindrome, return i and j.
 * 2c3) if z > x => i-- and continue
 * 2c5) if z < x => j++ and continue
 *
 */

public class Problem4 {
    /**
     * @return an array with 3 values representing the two numbers that produce the palindrome for the first two indices
     * and the palindrome itself on index 2
     */
    public long[] runAlgorithm(int numberOfDigits) {
        // 1)
        String maximumValueObtainedWithNumberOfDigitsAsString = "9".repeat(numberOfDigits);
        long maxValue = Long.parseLong(maximumValueObtainedWithNumberOfDigitsAsString);
        long currentPalindrome = maxValue * maxValue;

        // 2a) done before entering the loop to make sure we start with the first palindrome available
        String currentPalindromeAsString = Long.toString(currentPalindrome);
        if(! PalindromeHelpers.isPalindrome(currentPalindromeAsString))
            currentPalindromeAsString = PalindromeHelpers.nextPalindrome(currentPalindromeAsString);

        // PalindromeHelpers.nextPalindrome returns null if no palindromes were found. in this case, just return zeros.
        if (currentPalindromeAsString == null) return new long[]{0,0,0};

        do {
            // 2b)
            currentPalindrome = Long.parseLong(currentPalindromeAsString);
            long sqrt = (long) NumberHelpers.sqrt(currentPalindrome);

            // 2c -> preparing bounds for loop
            long jUpperBound = NumberHelpers.power(10L, numberOfDigits-1);
            long iLowerBound = NumberHelpers.power(10L, numberOfDigits-2);

            // 2c) the loop itself
            for(long i = sqrt, j = sqrt; i > iLowerBound && j < jUpperBound;) {
                // 2c1)
                long z = i * j;
                // 2c2)
                if(z == currentPalindrome) return new long[]{i, j, z};
                    // 2c3)
                else if(z > currentPalindrome) i--;
                    // 2c4)
                else j++;
            }
            // 2a)
        } while ((currentPalindromeAsString = PalindromeHelpers.nextPalindrome(currentPalindromeAsString)) != null);

        return null;
    }
}
