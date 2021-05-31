package helpers;

public class NumberHelpers {
    /**
     * Checks whether the given number is a prime or not.
     * @return boolean if it is a prime, false otherwise
     */
    public static boolean isPrime(long number) {
        long currentDivision = 2;

        // prime could not be larger than number/2, because any division on a number larger than this will result
        // in a number smaller than 2. This decreases time complexity from n to n/2.
        long divisionUpperBound = (number/2) + number % 2;

        while (currentDivision <= divisionUpperBound) {
            if(number % currentDivision == 0)
                return false;
            currentDivision++;
        }
        return true;
    }

    public static double sqrt(long number) {
        long temp;
        long sqrt = number / 2;

        do {
            temp = sqrt;
            sqrt = (temp + number/temp) / 2;
        } while((temp - sqrt) != 0);

        return sqrt;
    }

    public static long floor(double number) {
        return (long) number;
    }

    public static long power(long number, int power) {
        long ret = number;
        for(int i = 1; i < power; i++) ret*= number;
        return ret;
    }
}
