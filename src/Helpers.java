public class Helpers {

    /**
     * Checks whether the given number is a prime or not.
     * @return boolean if it is a prime, false otherwise
     */
    public static boolean isPrime(long number) {
        long currentDivision = 2;

        // prime could not be larger than number/2, because any division on a number larger than this will result
        // in a number smaller than 2. This decreases time complexity from n to n/2.
        long divisionUpperBound = (number/2) + number % 2;

        while (currentDivision < divisionUpperBound) {
            if(number % currentDivision == 0)
                return false;
            currentDivision++;
        }
        return true;
    }

    public static long[] addToArray(long[] array, long element) {
        long[] newArr = new long[array.length +1];
        int i = 0;
        while(i < array.length) {
            newArr[i] = array[i];
            i++;
        }
        newArr[i] = element;

        return newArr;
    }
}
