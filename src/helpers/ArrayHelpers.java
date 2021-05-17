package helpers;

public class ArrayHelpers {
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
